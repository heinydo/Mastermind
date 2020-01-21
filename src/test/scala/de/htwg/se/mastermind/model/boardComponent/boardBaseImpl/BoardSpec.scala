package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class BoardSpec extends WordSpec with Matchers {
  "A Board is the playingfield of Mastermind. A Board" when {
    "newly created" should {
      val emptyBoard = new Board(10,4)
      "contain a solution" in {
        emptyBoard.solution.nonEmpty should be(true)
      }
      "have a solution of 4 random pegs" in {
        emptyBoard.solution.size should be(4)
      }
    }
    "called with a given solution" should {
      val solution = Vector[Color](Color(1), Color(2), Color(5), Color(6))
      val boardWithSolution = Board(Vector.fill(10)(new Row(4)), solution)
      val colVec = Vector[Peg[Color]](Peg(Color(2)), Peg(Color(2)), Peg(Color(2)), Peg(Color(2)))
      val newBoard = boardWithSolution.replaceRow(0, colVec)
      "have this solution" in {
        boardWithSolution.solution.toString() should be("Vector(1, 2, 5, 6)")
      }
      "give back these hints" in {
        newBoard.createHints(solution, colVec).toString() should be("Vector(+,  ,  ,  )")
      }
      "display the right hint if same colors are inputted more than once" in {
        val newInput = Vector[Peg[Color]](Peg(Color(1)), Peg(Color(1)), Peg(Color(1)), Peg(Color(1)))
        val newRound = boardWithSolution.replaceRow(1, newInput)
        newRound.rows(1).predictionHint.pegs.toString() should be("Vector(+,  ,  ,  )")
      }
    }
    "with default constructor" should {
      val round = new Row(4)
      val rounds = Vector(round, round, round, round)
      val solution = Vector[Color](Color(1), Color(2), Color(3), Color(4))
      val board = Board(rounds, solution)
      "have four rounds" in {
        board.rows.size should be(4)
      }
      "have a solution with four pegs" in {
        board.solution.size should be(4)
      }
    }
    "printed on console" should {
      val board = new Board(10, 4)
      "start with this output" in {
        board.toString.startsWith("\n+---------+---------+") should be(true)
      }
    }
    "tested for game logic" should {
      val solution = Vector[Color](Color(5), Color(6), Color(4), Color(8))
      val boardWithSolution = Board(Vector.fill(8)(new Row(4)), solution)
      "give back these hints when one color and position is guessed correctly" in {
        val colVec = Vector[Peg[Color]](Peg(Color(5)), Peg(Color(5)), Peg(Color(5)), Peg(Color(5)))
        val newBoard = boardWithSolution.replaceRow(0, colVec)
        newBoard.createHints(solution, colVec).toString() should be("Vector(+,  ,  ,  )")
      }
      "give back these hints when two colors and positions are guessed correctly" in {
        val colVec = Vector[Peg[Color]](Peg(Color(5)), Peg(Color(6)), Peg(Color(5)), Peg(Color(6)))
        val newBoard = boardWithSolution.replaceRow(0, colVec)
        newBoard.createHints(solution, colVec).toString() should be("Vector(+, +,  ,  )")
      }
      "give back these hints when two pegs have correct color and position and two pegs have correct color" in {
        val colVec = Vector[Peg[Color]](Peg(Color(8)), Peg(Color(6)), Peg(Color(4)), Peg(Color(5)))
        val newBoard = boardWithSolution.replaceRow(0, colVec)
        newBoard.createHints(solution, colVec).toString() should be("Vector(+, +, o, o)")
      }
      "give back these hints when one color and position are guessed correctly" in {
        val colVec = Vector[Peg[Color]](Peg(Color(2)), Peg(Color(2)), Peg(Color(4)), Peg(Color(4)))
        val newBoard = boardWithSolution.replaceRow(0, colVec)
        newBoard.createHints(solution, colVec).toString() should be("Vector(+,  ,  ,  )")
      }
    }
    "solved before last round" should {
      val solution = Vector[Color](Color(1), Color(2), Color(3), Color(4))
      val boardWithSolution = Board(Vector.fill(solution.size)(new Row(4)), solution)
      val colVec = Vector[Peg[Color]](Peg(Color(2)), Peg(Color(2)), Peg(Color(4)), Peg(Color(8)))
      val newBoard = boardWithSolution.replaceRow(0, colVec)
      "have this solution" in {
        boardWithSolution.solution.toString() should be("Vector(1, 2, 3, 4)")
      }
      "set pegs correctly" in {
        var board = boardWithSolution.set(0, 5)
        board.rows(0).prediction.pegs.toString should be("Vector(5,  ,  ,  )")
        board = board.set(0, 5)
        board.rows(0).prediction.pegs.toString should be("Vector(5, 5,  ,  )")
      }
      "return true if peg with given round and column is empty" in {
        val board = boardWithSolution.set(0, 0)
        board.rows(0).prediction.pegs(0).emptyColor should be(true)
      }
      "return false if peg with given round and column is empty" in {
        val board = boardWithSolution.set(0, 5)
        board.rows(0).prediction.pegs(0).emptyColor should be(false)
      }
      "do nothing if index is -1 (index is out of bounds)" in {
        var board = new Board(1, 1)
        board.rows(0).prediction.pegs.toString() should be("Vector( )")
        var testVal = ""
        try {
          board = boardWithSolution.set(-1, 5)
        } catch {
          case e: IndexOutOfBoundsException => testVal = "do nothing in this case"
        }
        testVal should be("do nothing in this case")
        board.rows(0).prediction.pegs.toString() should be("Vector( )")
      }
    }
  }
}
