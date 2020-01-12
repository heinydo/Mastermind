package de.htwg.se.mastermind.model

case class Board(rounds: Vector[Round], solution: Vector[Peg])
{
  def this (numOfRounds: Int, numOfPegs: Int) = this(Vector.fill(numOfRounds)(new Round(numOfPegs)), Vector.fill(numOfPegs)(new Peg(0)))
}
