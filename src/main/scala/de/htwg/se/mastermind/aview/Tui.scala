package de.htwg.se.mastermind.aview

import com.typesafe.scalalogging.Logger
import de.htwg.se.mastermind.controller.controllerComponent.{BoardSizeChanged, ColorSelected, ControllerInterface, GameStatus, PegChanged}
import de.htwg.se.mastermind.model.{Board, Color, Peg}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)
  val logger = Logger("Mastermind TUI")
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
