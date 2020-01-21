package de.htwg.se.mastermind.controller.controllerComponent.controllerBaseImpl

import java.awt.Color

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.mastermind.MastermindModule
import de.htwg.se.mastermind.model.boardComponent.BoardInterface
import de.htwg.se.mastermind.controller.controllerComponent.GameStatus._
import de.htwg.se.mastermind.controller.controllerComponent.{BoardSizeChanged, ControllerInterface, GameStatus, PegChanged}
import de.htwg.se.mastermind.util.UndoManager

class Controller @Inject()(var board: BoardInterface)  extends ControllerInterface with LazyLogging {
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager
  val injector: Injector = Guice.createInjector(new MastermindModule)

  def numberOfRounds: Int = board.rows.size
  def numberOfPegs: Int = board.solution.size

  def createEmptyBoard(): Unit = {
    board.rows.size match {
      case 12 => board = injector.instance[BoardInterface](Names.named("easy"))
      case 10 => board = injector.instance[BoardInterface](Names.named("normal"))
      case 8 => board = injector.instance[BoardInterface](Names.named("hard"))
      case _ =>
    }
    gameStatus = NEW
    publish(new PegChanged)
  }

  def resize(newNumberOfPegs: Int, newNumberOfRounds: Int): Unit = {
    newNumberOfRounds match {
      case 12 => board = injector.instance[BoardInterface](Names.named("easy"))
      case 10 => board = injector.instance[BoardInterface](Names.named("normal"))
      case 8 => board = injector.instance[BoardInterface](Names.named("hard"))
      case _ =>
    }
    gameStatus = RESIZE
    publish(BoardSizeChanged(numberOfPegs, numberOfRounds))
  }

  def boardToString: String = board.toString
  def boardToHtmlString: String = board.boardToHtml

  def getCurrentRoundIndex: Int = board.rows.indices.iterator.find(index => !board.rows(index).isSet).getOrElse(-1)

  val availableGUIColors = Vector(
    java.awt.Color.PINK,
    java.awt.Color.BLUE,
    java.awt.Color.CYAN,
    java.awt.Color.GREEN,
    java.awt.Color.YELLOW,
    java.awt.Color.ORANGE)

  val availableGUIHintColors = Vector(
    java.awt.Color.BLACK,
    java.awt.Color.WHITE
  )

  val availableColors: Vector[Int] = board.rows(0).prediction.pegs(0).color.getAvailableColorIndex.toVector
  val availableHints: Vector[String] = board.rows(0).predictionHint.pegs(0).color.getAvailableHints.toVector

  def set(roundIndex: Int, colors: Int): Unit = {
    if (roundIndex != -1) {
      undoManager.doStep(new SetCommand(roundIndex, this, colors))
      gameStatus = SET
      publish(new PegChanged)
    }
  }

  def mapFromGuiColor(color: java.awt.Color): Int = {
    var foundColor: Int = 0
    val idx = availableGUIColors.indices.toStream.find(i => availableGUIColors(i).equals(color)).getOrElse(-1)

    if (idx != -1) foundColor = availableColors(idx)
    foundColor
  }

  def mapToGuiColor(color: Int): java.awt.Color = {
    var foundColor: java.awt.Color = java.awt.Color.GRAY
    val idx = availableGUIColors.indices.toStream.find(i => availableColors(i).equals(color)).getOrElse(-1)

    if (idx != -1) foundColor = availableGUIColors(idx)

    foundColor
  }

  def undo(): Unit = {
    undoManager.undoStep()
    gameStatus = UNDO
  }

  def redo(): Unit = {
    undoManager.redoStep()
    gameStatus = REDO
  }

  def statusText: String = GameStatus.message(gameStatus)

  def save(): Unit = {
    gameStatus = SAVED
  }

  def load(): Unit = {
    gameStatus = LOADED
  }

  def mapHintToGuiHint(hintColor: String): java.awt.Color = {
    var foundHint: java.awt.Color = java.awt.Color.LIGHT_GRAY
    val idx = availableHints.indices.toStream.find(i => availableHints(i).equals(hintColor)).getOrElse(-1)

    if (idx != -1) foundHint = availableGUIHintColors(idx)

    foundHint
  }

  def guessColor(rowIndex: Int, columnIndex: Int): java.awt.Color = {
    var foundColor: java.awt.Color = java.awt.Color.GRAY

    if (!board.rows(rowIndex).prediction.pegs(columnIndex).emptyColor) {
      foundColor = mapToGuiColor(board.rows(rowIndex).prediction.pegs(columnIndex).color.toString.toInt)
    }
    foundColor
  }

  def hintColor(rowIndex: Int, columnIndex: Int): java.awt.Color = {
    var foundColor: java.awt.Color = java.awt.Color.LIGHT_GRAY

    if (!board.rows(rowIndex).prediction.containsEmptyColor) {
      foundColor = mapHintToGuiHint(board.rows(rowIndex).predictionHint.pegs(columnIndex).color.name)
    }
    foundColor
  }

  override def solve(): Unit = ???

  override def boardToHtml: String = board.boardToHtml
}