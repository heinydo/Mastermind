package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import scala.util.Random

case class Color(colorIndex: Int){

  def this() = this(0) //color is not set

  def getAvailableColorIndex: Seq[Int] =  Seq(1,2,3,4,5,6)

  def isValidColorIndex: Boolean = getAvailableColorIndex.contains(colorIndex)

  override def toString: String = colorIndex match {
    case 0 => " "
    case value => value.toString
  }

  def randomColorIndex(): Int = {
    val colors = this.getAvailableColorIndex
    val rand = new Random(System.currentTimeMillis())
    val random_index = rand.nextInt(colors.length)
    colors(random_index)
  }
}
