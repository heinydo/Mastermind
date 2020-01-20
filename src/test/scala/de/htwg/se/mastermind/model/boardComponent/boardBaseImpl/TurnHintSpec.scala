package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TurnHintSpec extends WordSpec with Matchers {

  "A TurHint " when {
    "empty " should {
      val emptyTurnHint = new PredictionHint(4)
      "have a vector with four empty pegs" in {
        emptyTurnHint.pegs.toString() should be("Vector( ,  ,  ,  )")
      }
    }
    "set to a valid peg color and position" should {
      val nonEmptyTurnHint = PredictionHint(Vector(Peg[Hint](Hint("rightColAndPos"))))
      "return that vector" in {
        nonEmptyTurnHint.pegs.toString() should be("Vector(+)")
      }
    }
    "set to a valid color with wrong position" should {
      val rightCol = PredictionHint(Vector(Peg[Hint](Hint("rightCol"))))
      "return a \"o\"" in {
        rightCol.pegs.toString should be("Vector(o)")
      }
    }
  }
}
