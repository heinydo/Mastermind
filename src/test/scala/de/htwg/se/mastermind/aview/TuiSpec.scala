package de.htwg.se.mastermind.aview

import de.htwg.se.mastermind.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.Board
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.mastermind.controller.controllerComponent.GameStatus._

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {
  "A Mastermind Tui" should {
    val board = new Board(4, 10)
    val controller = new Controller(board)
    val tui = new Tui(controller)
    "create and empty Mastermind on input 'n'" in {
      tui.processInputLine("n")
      controller.board.rows should be(board.rows)
      controller.gameStatus should be(NEW)
    }
    "set a turn on input '1'" in {
      tui.processInputLine("1")
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1,  ,  ,  )")
      controller.gameStatus should be(SET)
    }
    "undo a step back on input 'z'" in {
      tui.processInputLine("1")
      tui.processInputLine("1")
      tui.processInputLine("1")
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1, 1, 1)")
      controller.undo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1, 1,  )")
      controller.undo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1,  ,  )")
      controller.undo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1,  ,  ,  )")
      controller.undo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector( ,  ,  ,  )")
      controller.gameStatus should be(UNDO)
    }
    "redo a step back on input 'y'" in {
      controller.redo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1,  ,  ,  )")
      controller.redo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1,  ,  )")
      controller.redo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1, 1,  )")
      controller.redo()
      controller.board.rows(0).prediction.pegs.toString() should be("Vector(1, 1, 1, 1)")
      controller.gameStatus should be(REDO)
    }
    "for now do nothing with wrong console input or more than one char" in {
      tui.processInputLine("ss")
      controller.board.rows(2).prediction.pegs.toString() should be("Vector( ,  ,  ,  )")
      tui.processInputLine("sss")
      controller.board.rows(2).prediction.pegs.toString() should be("Vector( ,  ,  ,  )")
    }
    "have this params given" in {
      controller.numberOfRounds should be(10)
      controller.numberOfPegs should be(4)
    }
    "map a hint to a GUI hint" in {
      val hint = "rightColAndPos"
      controller.mapHintToGuiHint(hint).toString should be("java.awt.Color[r=0,g=0,b=0]")
    }
    "get a guess color" in {
      controller.guessColor(0, 0).toString should be("java.awt.Color[r=255,g=175,b=175]")
    }
    "change a board difficulty to easy on input '-'" in {
      tui.processInputLine("-")
      controller.numberOfRounds should be(12)
      controller.numberOfPegs should be(4)
    }
    "change a board difficulty to hard on input '+'" in {
      tui.processInputLine("+")
      controller.numberOfRounds should be(8)
      controller.numberOfPegs should be(6)
    }
    "change a board difficulty to normal on input '*'" in {
      tui.processInputLine("*")
      controller.numberOfRounds should be(10)
      controller.numberOfPegs should be(4)
    }
  }
}
