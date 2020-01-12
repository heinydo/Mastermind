package de.htwg.se.mastermind

import de.htwg.se.mastermind.aview.Tui
import de.htwg.se.mastermind.model.Board

import scala.io.StdIn.readLine

object Mastermind {
  var board = new Board(10, 4)
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      println("Board : " + board.toString)
      input = readLine()
      board = tui.processInputLine(input, board)
    } while (input != "q")
  }
}
