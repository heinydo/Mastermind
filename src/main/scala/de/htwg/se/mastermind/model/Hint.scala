package de.htwg.se.mastermind.model

case class Hint(name: String)
{
  def this() = this("0")
  def getAvailableHints: Seq[String] = Seq(
    "rightColAndPos", "rightCol"
  )

  override def toString: String = name match {
    case "rightColAndPos" => "+"
    case "rightCol" => "o"
    case _ => " "
  }
  def isValidHint(name: String): Boolean = getAvailableHints.contains(name)
}
