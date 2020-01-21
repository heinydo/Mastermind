package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ColorSpec extends WordSpec with Matchers {
  "A Color" when {
    "having a valid name" should {
      val colorOne = Color(1)
      "return that value" in {
        colorOne.getAvailableColorIndex should contain(1)
      }
    }
    "is valid" should {
      val colorOne = Color(1)
      "be true" in {
        colorOne.isValidColorIndex
      }
    }
    "getting a random color" should {
      val randomColor = Color(0).randomColorIndex()
      "not be empty anymore" in {
        randomColor should not be 0
      }
    }
    "being empty" should {
      val emptyColor = new Color()
      "contain a zero as value" in {
        emptyColor.colorIndex should be(0)
      }
    }
    "being initialized" should {
      val color = Color(8)
      "have a colorIndex" in {
        color.colorIndex should be(8)
      }
    }
  }
}