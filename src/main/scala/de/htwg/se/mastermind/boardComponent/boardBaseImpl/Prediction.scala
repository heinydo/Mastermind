package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

case class Prediction(pegs : Vector[Peg[Color]])
{
  def this(numberOfPegs: Int) = this(Vector.fill(numberOfPegs)(new Peg[Color](new Color())))
  def size: Int = pegs.size

  def updatePegs(pegs: Vector[Peg[Color]]): Prediction = {
    var newPegs = Vector.empty[Peg[Color]]
    pegs.foreach(p => newPegs = newPegs :+ Peg(p.color))
    this.copy(newPegs)
  }
  def containsEmptyColor: Boolean = if (pegs.contains(Peg(new Color()))) true else false
}
