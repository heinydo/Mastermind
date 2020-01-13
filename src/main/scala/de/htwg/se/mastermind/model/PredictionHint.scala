package de.htwg.se.mastermind.model

case class PredictionHint (pegs: Vector[Peg[Hint]]){

  def this(numberOfPegs: Int) = this(Vector.fill(numberOfPegs)(new Peg[Hint](new Hint())))
  def size: Int = pegs.size

  def replacePegs(hintVec: Vector[Peg[Hint]]): PredictionHint = {
    var newPegs = Vector.empty[Peg[Hint]]
    hintVec.foreach(hint => newPegs = newPegs :+hint)
    copy(newPegs)
  }

  def hintVectorSolved: PredictionHint = {
    val solvedVec = Vector.fill(size)(Hint("rightColAndPos"))
    var newPegs = Vector.empty[Peg[Hint]]
    solvedVec.foreach(hint => newPegs = newPegs :+ Peg(hint))
    copy(newPegs)
  }
}
