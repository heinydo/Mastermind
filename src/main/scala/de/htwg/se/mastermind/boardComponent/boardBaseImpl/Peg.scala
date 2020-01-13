package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

case class Peg[A](color: A){

  def emptyColor: Boolean = color.toString.equals(" ")
  def updateColor(color: Color): Peg[Color] = Peg(color)
  override def toString: String = color.toString.replace('0', ' ')
}
