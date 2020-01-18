package de.htwg.se.mastermind.model.gridComponent.gridBaseImpl

import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.Hint
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class HintSpec extends WordSpec with Matchers {
  "A Hint" when {
    "having a valid name" should {
      val rightCol = Hint("rightCol")
      "be in the list of available colors" in {
        rightCol.getAvailableHints should contain(rightCol.name)
      }
    }
    "is empty" should {
      "have zero as name" in {
        new Hint().name should be("0")
      }
    }
    "is valid color and position" should {
      val rightColAndPos = Hint("rightColAndPos")
      "be true" in {
        rightColAndPos.isValidHint(rightColAndPos.name)
      }
    }
    "is a valid color but wrong position" should {
      val rightCol = Hint("rightCol")
      "be displayed as as right color hint" in {
        rightCol.toString should be("o")
      }
    }
    "not valid" should {
      val notValidColor = Hint("notvalid")
      "not be valid" in {
        notValidColor.isValidHint(notValidColor.name) should be(false)
      }
      "return a whitespace as name when formatted as string" in {
        notValidColor.toString should be(" ")
      }
    }
  }
}