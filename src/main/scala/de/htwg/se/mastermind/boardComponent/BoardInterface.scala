package de.htwg.se.mastermind.model.boardComponent

import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.{Row, _}

trait BoardInterface {
  def redoPeg(roundIndex: Int, colors: Int): _root_.de.htwg.se.mastermind.model.boardComponent.BoardInterface = ???

  def undoPeg(roundIndex: Int): BoardInterface = ???

  def replaceRow(roundIndex: Int, pegVector: Vector[Peg[Color]]): BoardInterface

  def set(roundIndex: Int, color: Int): BoardInterface

  def createHints(solution: Vector[Color], colVec: Vector[Peg[Color]]): Vector[Peg[Hint]]

  def createEmptyBoard(newNumberOfPegs: Int, newNumberOfRounds: Int): BoardInterface

  def solution: Vector[Color]

  def rows: Vector[Row]

  def boardToHtml: String

  def isSolved: Boolean

  def colorVecToPegVec(colVec: Vector[Color]) : Vector[Peg[Color]]
}
