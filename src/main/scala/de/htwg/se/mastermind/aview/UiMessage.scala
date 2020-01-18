package de.htwg.se.mastermind.aview

import de.htwg.se.mastermind.controller.controllerComponent.ControllerInterface

object UiMessage {
  case class CreateGui(controller: ControllerInterface)
  case class CreateTui(controller: ControllerInterface)
}
