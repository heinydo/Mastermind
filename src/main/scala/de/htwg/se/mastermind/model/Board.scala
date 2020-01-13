package de.htwg.se.mastermind.model

case class Board(rows: Vector[Row], solution: Vector[Color])
{
  def this (numberOfRows: Int, numOfPegs: Int) = this(Vector.fill(numberOfRows)(new Row(numOfPegs)),Board.randomSolution(numOfPegs))
  def numOfRows: Int = rows.size
  def numOfPegs: Int = solution.size

  def set(roundIndex: Int, newColor: Int): Board = {
    var newPegs = Vector.fill(numOfPegs)(new Peg(new Color()))
    val alreadySetPegs = rows(roundIndex).prediction.pegs.filter(peg => !peg.emptyColor)
    alreadySetPegs.indices.foreach(i => newPegs = newPegs.updated(i, alreadySetPegs(i)))

    val i = newPegs.indexOf(Peg(Color(0)))
    newPegs = newPegs.updated(i, Peg(Color(newColor)))
    replaceRow(roundIndex, newPegs)
  }

  def getCurrentRoundIndex: Int = rows.indices.iterator.find(index => rows(index).isSet).getOrElse(-1)

  def replaceRow(roundIndex: Int, pegVector : Vector[Peg[Color]]): Board = {
    var hints = Vector.fill(numOfPegs)(Peg(new Hint))
    if (!pegVector.contains(Peg(0))) hints = createHints(solution, pegVector)
    copy(rows.updated(roundIndex, rows(roundIndex).replaceRow(pegVector, hints)), solution)
  }


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
      if (!hintSet.contains(colVec(i).color.colorIndex) && solution.contains(colVec(i))) {
        hintSet = hintSet + colVec(i).color.colorIndex
        hints = hints :+ Peg(Hint("rightCol"))
      }
    }
    while (hints.size.<(numOfPegs)) {
      hints = hints :+ Peg(new Hint)
    }
    hints
  }
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