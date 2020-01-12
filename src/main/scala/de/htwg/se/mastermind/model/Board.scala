package de.htwg.se.mastermind.model

case class Board(rows: Vector[Row], solution: Vector[Peg[Color]])
{
  def this (numberOfRows: Int, numOfPegs: Int) = this(Vector.fill(numberOfRows)(new Row(numOfPegs)), Vector.fill(numOfPegs)(new Peg(new Color())))
  def numOfRows: Int = rows.size
  def numOfPegs: Int = solution.size

  def set(roundIndex: Int, newColor: Int): Board = {
    var newPegs = Vector.fill(numOfPegs)(new Peg(new Color()))
    val alreadySetPegs = rows(roundIndex).prediction.pegs.filter(peg => !peg.emptyColor)
    alreadySetPegs.indices.foreach(i => newPegs = newPegs.updated(i, alreadySetPegs(i)))

    val i = newPegs.indexOf(Peg(Color(0)))
    newPegs = newPegs.updated(i, Peg(Color(newColor)))
    replaceRound(roundIndex, newPegs)
  }

  def getCurrentRoundIndex: Int = rows.indices.iterator.find(index => rows(index).isSet).getOrElse(-1)

  def replaceRound(roundIndex: Int, pegVector : Vector[Peg[Color]]): Board = {
    var hints = Vector.fill(numOfPegs)(new Hint())
    //if (!pegVector.contains(Peg(0))) hints = createHints(solution, pegVector)
    copy(rows.updated(roundIndex, rows(roundIndex).replacePegs(pegVector, hints)), solution)
  }
}
