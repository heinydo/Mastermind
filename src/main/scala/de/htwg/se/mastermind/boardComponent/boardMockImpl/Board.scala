package de.htwg.se.mastermind.model.boardComponent.boardMockImpl

import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.{Color, Hint, Peg, Row}
import de.htwg.se.mastermind.model.boardComponent.BoardInterface

class Board(var rows: Vector[Row], var solution: Vector[Color]) extends BoardInterface {

  def replaceRow(roundIndex: Int, pegVector : Vector[Peg[Color]]): BoardInterface = this

  def set(roundIndex: Int, color: Int): BoardInterface = this

  //def undoPeg(roundIndex: Int): BoardInterface = this

  //def redoPeg(roundIndex: Int, color: Int): BoardInterface = this

  def createHints(solution: Vector[Color], colVec: Vector[Peg[Color]]): Vector[Peg[Hint]] =  Vector[Peg[Hint]](new Peg(new Hint))

  def createEmptyBoard(newNumberOfPegs: Int, newNumberOfRounds: Int): BoardInterface = this

  def boardToHtml: String = "Test String"

  def isSolved: Boolean = false

  def colorVecToPegVec(colVec: Vector[Color]) : Vector[Peg[Color]] = Vector[Peg[Color]](Peg(new Color()))
}
