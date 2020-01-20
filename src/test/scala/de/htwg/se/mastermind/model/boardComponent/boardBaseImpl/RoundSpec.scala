package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class RoundSpec extends WordSpec with Matchers {

  "A Round " when {
    "empty " should {
      val emptyRound = Row(new Prediction(4), new PredictionHint(4))
      "have an empty turn" in {
        emptyRound.prediction.pegs.toString() should be("Vector( ,  ,  ,  )")
      }
      "have that turn size" in {
        emptyRound.prediction.pegs.size should be(4)
      }
      "have an empty Vector of turnHint" in {
        emptyRound.predictionHint.pegs.toString() should be("Vector( ,  ,  ,  )")
      }
      "have that turnHint size" in {
        emptyRound.predictionHintSize should be(4)
      }
    }
    "set to a valid peg color" should {
      val nonEmptyTurn = Prediction(Vector(Peg[Color](Color(2))))
      "return that vector" in {
        nonEmptyTurn.pegs.toString() should be("Vector(2)")
      }
    }
  }
}
