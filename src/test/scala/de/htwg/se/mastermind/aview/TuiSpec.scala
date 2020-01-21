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
    val board = new Board(10, 4)
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
  }
}
