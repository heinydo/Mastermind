package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class PegSpec extends WordSpec with Matchers {

  "A Peg" when {
    "not set to any value " should {
      val emptyPeg = Peg(Color(0))
      "be an empty string" in {
        emptyPeg.color.toString should be(" ")
      }
      "not be set" in {
        emptyPeg.emptyColor should be(true)
      }
      "should contain a free space when using toString" in {
        emptyPeg.toString should be(" ")
      }
    }
    "set to a specific value" should {
      val nonEmptyPeg = Peg(Color(2))
      "return that value" in {
        nonEmptyPeg.color.toString should be("2")
      }
      "be set" in {
        nonEmptyPeg.emptyColor should be(false)
      }
      "updatd with a new color" in {
        val updatedPeg = nonEmptyPeg.updateColor(Color(7))
        updatedPeg.color.toString should be("7")
      }
      "has no color yet" should {
        val emptyPeg = Peg(Color(0))
        "be empty" in {
          emptyPeg.color.toString should be(" ")
          emptyPeg.emptyColor should be(true)
        }
      }
    }
  }

}
