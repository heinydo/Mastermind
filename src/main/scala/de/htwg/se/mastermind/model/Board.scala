package de.htwg.se.mastermind.model

case class Board(rounds: Vector[Round], solution: Vector[Peg])
{
  def this (numOfRounds: Int, numOfPegs: Int) = this(Vector.fill(numOfRounds)(new Round(numOfPegs)), Vector.fill(numOfPegs)(new Peg(0)))
  def numOfRounds: Int = rounds.size
  def numOfPegs: Int = solution.size

  def set(roundIndex: Int, newPeg: Peg): Board = {
    var newPegs = Vector.fill(this.numOfPegs)(Peg(0))
    val alreadySetPegs = rounds(roundIndex).pegs.filter(p => p.value != 0)
    alreadySetPegs.indices.foreach(i => newPegs = newPegs.updated(i, alreadySetPegs(i)))
    val i = newPegs.indexOf(Peg(0))
    newPegs = newPegs.updated(i, newPeg)
    replaceRound(roundIndex, newPegs)
  }

  def replaceRound(roundIndex: Int, pegVector : Vector[Peg]): Board = {
    copy(rounds.updated(roundIndex, rounds(roundIndex).replacePegs(pegVector)), solution)
  }

  def getCurrentRoundIndex: Int = rounds.indices.iterator.find(index => !rounds(index).allPegsAreSet).getOrElse(-1)
}
