package de.htwg.se.mastermind.controller.controllerComponent.controllerBaseImpl

import java.awt.Color

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.mastermind.MastermindModule
import de.htwg.se.mastermind.model.boardComponent.BoardInterface
import de.htwg.se.mastermind.controller.controllerComponent.GameStatus._
import de.htwg.se.mastermind.controller.controllerComponent.{BoardSizeChanged, ControllerInterface, GameStatus, PegChanged}
import de.htwg.se.mastermind.util.UndoManager

import scala.swing.Publisher

class Controller @Inject()(var board: BoardInterface) extends ControllerInterface with Publisher {

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

  val availableColors: Vector[Int] = board.rows(0).prediction.pegs(0).color.getAvailableColorIndex.toVector
  val availableHints: Vector[String] = board.rows(0).predictionHint.pegs(0).color.getAvailableHints.toVector

  def boardToString: String = board.toString

  def getCurrentRoundIndex: Int = board.rows.indices.iterator.find(index => !board.rows(index).isSet).getOrElse(-1)

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

    if (idx != -1) foundColor = availableColors(idx).toInt

    foundColor
  }

  def mapToGuiColor(color: Int): java.awt.Color = {
    var foundColor: java.awt.Color = java.awt.Color.GRAY
    val idx = availableGUIColors.indices.toStream.find(i => availableColors(i).equals(color.toString)).getOrElse(-1)

    if (idx != -1) foundColor = availableGUIColors(idx)

    foundColor
  }

  def undo(): Unit = {
    undoManager.undoStep()
    gameStatus = UNDO
    publish(new PegChanged)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    gameStatus = REDO
    publish(new PegChanged)
  }

  def statusText: String = GameStatus.message(gameStatus)

  def save(): Unit = {
    gameStatus = SAVED
    publish(new PegChanged)
  }

  def load(): Unit = {
    gameStatus = LOADED
    publish(new PegChanged)
  }

  override def mapHintToGuiHint(hintColor: String): Color = ???

  override def guessColor(rowIndex: Int, columnIndex: Int): Color = ???

  override def hintColor(rowIndex: Int, columnIndex: Int): Color = ???

  override def solve(): Unit = ???

  override def availableGUIColors: Vector[Color] = ???
}