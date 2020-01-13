package de.htwg.se.mastermind.controller.controllerComponent.controllerMockImpl

import de.htwg.se.mastermind.controller.controllerComponent.{ControllerInterface, GameStatus}
import de.htwg.se.mastermind.controller.controllerComponent.GameStatus._
import de.htwg.se.mastermind.model.boardComponent.BoardInterface

class Controller(var board: BoardInterface) extends ControllerInterface {

  createEmptyBoard()
  override def createEmptyBoard(): Unit = {}
  override def boardToString: String = board.toString
  override def getCurrentRoundIndex: Int = 0
  override def set(roundIndex: Int, color: Int): Unit = {}
  override def mapFromGuiColor(color: java.awt.Color): Int = 1
  override def mapToGuiColor(color: Int): java.awt.Color = java.awt.Color.PINK
  override def mapHintToGuiHint(hintColor: String): java.awt.Color = java.awt.Color.BLACK
  override def guessColor(rowIndex: Int, columnIndex: Int): java.awt.Color = java.awt.Color.PINK
  override def hintColor(rowIndex: Int, columnIndex: Int): java.awt.Color = java.awt.Color.WHITE
  override def undo(): Unit = {}
  override def redo(): Unit = {}
  override def solve(): Unit = {}
  override def gameStatus: GameStatus = IDLE

  override def statusText: String = GameStatus.message(gameStatus)

  override def availableGUIColors: Vector[java.awt.Color] = Vector[java.awt.Color](java.awt.Color.PINK)

  override def numberOfPegs: Int = 1

  override def numberOfRounds: Int = 1

  override def resize(numberOfPegs: Int, numberOfRounds: Int): Unit = {}

  override def save(): Unit = {}

  override def load(): Unit = {}

}