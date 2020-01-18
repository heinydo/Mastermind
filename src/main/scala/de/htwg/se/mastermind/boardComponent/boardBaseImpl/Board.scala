package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl
import de.htwg.se.mastermind.model.boardComponent.BoardInterface

case class Board(rows: Vector[Row], solution: Vector[Color]) extends BoardInterface
{
  def this (numberOfRows: Int, numOfPegs: Int) = this(Vector.fill(numberOfRows)(new Row(numOfPegs)),Board.randomSolution(numOfPegs))
  def numOfRows: Int = rows.size
  def numOfPegs: Int = solution.size

  def set(roundIndex: Int, newColor: Int): Board = {
    val newPegs = rows(roundIndex).prediction.pegs
    val i = newPegs.indexOf(Peg(Color(0)))
    replaceRow(roundIndex, newPegs.updated(i, Peg(Color(newColor))))
  }

  def colorVecToPegVec(colVec: Vector[Color]) : Vector[Peg[Color]] = colVec.foldLeft(Vector[Peg[Color]]())((result, currentElem) => result :+ Peg(currentElem))

  def getCurrentRoundIndex: Int = rows.indices.iterator.find(index => rows(index).isSet).getOrElse(-1)

  def replaceRow(roundIndex: Int, pegVector : Vector[Peg[Color]]): Board = {
    if(!pegVector.contains(Peg(Color(0)))) copy(rows.updated (roundIndex, rows(roundIndex).replaceRow(pegVector, createHints(solution, pegVector))), solution)
    else copy(rows.updated (roundIndex, rows(roundIndex).replaceRow(pegVector, Vector.fill(numOfPegs)(Peg(new Hint())))), solution)
  }
  def isSolved: Boolean = rows.indices.exists(i => this.rows(i).predictionHint.equals(rows(i).predictionHint.hintVectorSolved))


  def createHints(solution: Vector[Color], colVec: Vector[Peg[Color]]): Vector[Peg[Hint]] = {

    var hints = Vector.empty[Peg[Hint]]
    var hintSet = Set.empty[Int]

    for {i <- colVec.indices} {
      if (solution(i).colorIndex == colVec(i).color.colorIndex) {
        hints = Peg(Hint("rightColAndPos")) +: hints
        hintSet = hintSet + colVec(i).color.colorIndex
      }
    }
    for {i <- colVec.indices} {
      if (!hintSet.contains(colVec(i).color.colorIndex) && solution.contains(colVec(i).color)) {
        hintSet = hintSet + colVec(i).color.colorIndex
        hints = hints :+ Peg(Hint("rightCol"))
      }
    }
    while (hints.size.<(numOfPegs)) {
      hints = hints :+ Peg(new Hint)
    }
    hints
  }

  override def toString: String = {
    val lineSeparator = ("+-" + ("--" * numOfPegs)) + "+-" + ("--" * numOfPegs) + "+\n"
    val line = ("| " + ("x " * numOfPegs)) + ("| " + ("x " * numOfPegs)) + "|\n"
    var box = "\n" + (lineSeparator + line) * numOfRows + lineSeparator

    for {
      printRow <- rows.indices
      printCol <- 0 until numOfPegs * 2
    } {
      if (printCol < numOfPegs) {
        box = box.replaceFirst("x", rows(printRow).prediction.pegs(printCol).color.toString)
      } else {
        box = box.replaceFirst("x", rows(printRow).predictionHint.pegs(printCol - numOfPegs).color.toString)
      }
    }
    box
  }
  override def boardToHtml: String = {
    val lineSeparator = ("+-" + ("--" * numOfPegs)) + "+-" + ("--" * numOfPegs) + "+<br>"
    val line = ("| " + ("x " * numOfPegs)) + ("| " + ("x " * numOfPegs)) + "|<br>"
    var box = "<br>" + (lineSeparator + line) * numOfRows + lineSeparator

    for {
      printRow <- rows.indices
      printCol <- 0 until numOfPegs * 2
    } {
      if (printCol < numOfPegs) {
        box = box.replaceFirst("x", rows(printRow).prediction.pegs(printCol).color.toString)
      } else {
        box = box.replaceFirst("x", rows(printRow).predictionHint.pegs(printCol - numOfPegs).color.toString)
      }
    }
    box
  }

  override def createEmptyBoard(newNumberOfPegs: Int, newNumberOfRounds: Int): BoardInterface = this
}


object Board {
  def randomSolution(numberOfPegs: Int): Vector[Color] = addColor(numberOfPegs)

  def addColor(numberOfPegs: Int): Vector[Color] = {
    val newSet = Set.empty[Color]
    addColorToSet(newSet, numberOfPegs).toVector
  }

  def addColorToSet(set: Set[Color], numberOfPegs: Int): Set[Color] = {
    var newSet = set
    val color = Color(new Color().randomColorIndex())

    if (newSet.size.equals(numberOfPegs)) {
      newSet
    } else {
      newSet += color
      addColorToSet(newSet, numberOfPegs)
    }
  }
}