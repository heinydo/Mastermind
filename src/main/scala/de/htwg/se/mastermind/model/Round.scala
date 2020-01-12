package de.htwg.se.mastermind.model

case class Round(pegs : Vector[Peg])
{
  def this(numOfPegs : Int) = this(Vector.fill(numOfPegs)(new Peg(0)))

  def replacePegs(pegVector : Vector[Peg]): Round = {
    var newPegs = Vector.empty[Peg]
    pegVector.foreach(peg => newPegs = newPegs :+ peg)
    this.copy(pegVector)
  }
}
