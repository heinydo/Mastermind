package de.htwg.se.mastermind.aview

import de.htwg.se.mastermind.model.{Board, Peg}

class Tui {
  def processInputLine(input: String, board:Board):Board = {
    input match {
      case "q" => board
      //case "n"=> new Board(9)
      // "s" =>
      //val (success, solvedGrid) = new Solver(grid).solve;
      //if (success) println("Puzzle solved")else println("This puzzle could not be solved!")
      //solvedGrid
      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
       case color :: Nil => board.set(0,Peg(color))
          case _ => board
        }
      }
    }
  }
}
