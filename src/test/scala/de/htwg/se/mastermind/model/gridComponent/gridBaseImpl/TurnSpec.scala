package de.htwg.se.mastermind.model.gridComponent.gridBaseImpl

import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.{Color, Peg, Prediction}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TurnSpec extends WordSpec with Matchers {

  "A Turn " when {
    "empty " should {
      val emptyTurn = new Prediction(4)
      "have a vector with four empty pegs" in {
        emptyTurn.pegs.toString() should be("Vector( ,  ,  ,  )")
      }
      "should contain an empty color" in {
        emptyTurn.containsEmptyColor should be(true)
      }
    }
    "replacing colors " should {
      val pegs = Vector(Peg(Color(8)), Peg(Color(4)), Peg(Color(6)), Peg(Color(5)))
      val turn = Prediction(pegs)
      "replace empty pegs" in {
        turn.updatePegs(Vector(Peg(Color(2)), Peg(Color(3)), Peg(Color(8)), Peg(Color(7))))
        turn.pegs.toString() should be("Vector(8, 4, 6, 5)")
      }
      "not contain an empty color" in {
        turn.containsEmptyColor should be(false)
      }
    }
  }
}
