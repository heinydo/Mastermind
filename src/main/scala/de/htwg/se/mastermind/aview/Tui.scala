package de.htwg.se.mastermind.aview

import java.io.BufferedReader

import com.typesafe.scalalogging.{LazyLogging}
import de.htwg.se.mastermind.controller.controllerComponent.{BoardSizeChanged, ColorSelected, ControllerInterface, GameStatus, PegChanged}

import scala.swing.Reactor

class Tui(controller: ControllerInterface)extends Reactor with LazyLogging {

  listenTo(controller)

  var stopProcessingInput = false

  def processInput(input: BufferedReader) = {
    while (!stopProcessingInput) {
      if (input.ready()) {
        val line = input.readLine()
        processInputLine(line)
      } else {
        Thread.sleep(200) // don't waste cpu cycles if no input is given
      }
    }
  }



  def processInputLine(input: String):Unit = {
    input match {
      case "q" => System.exit(0)
      case "n" => controller.createEmptyBoard()
      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
       case color :: Nil => controller.set(controller.getCurrentRoundIndex, color)
          case _ =>
        }
      }
    }
  }

  reactions += {
    case event: PegChanged => printTui()
    case event: ColorSelected =>
    case event: BoardSizeChanged => printTui()
  }

  def printTui(): Unit = {
    logger.info(controller.boardToString)
    //logger.info(GameStatus.message(controller.gameStatus))
  }

}
