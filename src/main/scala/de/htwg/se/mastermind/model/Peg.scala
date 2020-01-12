package de.htwg.se.mastermind.model

case class Peg(value: Int){
  def this() = this(0)
  def isSet: Boolean = value != 0
  override def toString: String = value.toString.replace('0', ' ')
}
