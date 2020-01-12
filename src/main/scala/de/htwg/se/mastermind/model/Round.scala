package de.htwg.se.mastermind.model

case class Round(pegs : Vector[Peg])
{
  def this(numOfPegs : Int) = this(Vector.fill(numOfPegs)(new Peg(0)))
}
