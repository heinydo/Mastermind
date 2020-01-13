package de.htwg.se.mastermind.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.mastermind.util.Command

class SetCommand(roundIndex: Int, controller: Controller, colors: Int) extends Command {

  override def doStep(): Unit = controller.board = controller.board.set(roundIndex, colors)

  override def undoStep(): Unit = controller.board = controller.board.undoPeg(roundIndex)

  override def redoStep(): Unit = controller.board = controller.board.redoPeg(roundIndex, colors)
}