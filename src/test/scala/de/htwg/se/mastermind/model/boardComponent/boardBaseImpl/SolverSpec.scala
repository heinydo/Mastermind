package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class SolverSpec extends WordSpec with Matchers {

  "A Solver" when {
    "a board is empty" should {
      val emptyBoard = new Board(4, 10)
      "solve a board without any problems" in {
        emptyBoard.isSolved should be(false)
        val solvedBoard = new Solver(emptyBoard).solve
        solvedBoard.isSolved should be(true)
      }
    }
  }
  "board is not empty" should {
    val solution = Vector[Color](Color(1), Color(2), Color(5), Color(6))
    val boardWithSolution = Board(Vector.fill(solution.size)(new Row(4)), solution)
    val colVec = Vector[Peg[Color]](Peg(Color(2)), Peg(Color(2)), Peg(Color(2)), Peg(Color(2)))
    val newBoard = boardWithSolution.replaceRow(0, colVec)
    "solve a board without any problems" in {
      newBoard.isSolved should be(false)
      val solvedBoard = new Solver(newBoard).solve
      solvedBoard.isSolved should be(true)
    }
  }
}
