package de.htwg.se.mastermind.model

class PredictionHint (pegs: Vector[Peg[Hint]]){

  def this(numberOfPegs: Int) = this(Vector.fill(numberOfPegs)(new Peg[Hint](new Hint())))
  def size: Int = pegs.size
}
