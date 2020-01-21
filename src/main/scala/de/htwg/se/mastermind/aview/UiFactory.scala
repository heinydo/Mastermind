package de.htwg.se.mastermind.aview

import akka.actor.Actor
import de.htwg.se.mastermind.aview.UiMessage.{CreateGui, CreateTui}
import de.htwg.se.mastermind.aview.gui.SwingGui

case class UiFactory() extends Actor {
  override def receive: Receive = {
    case CreateGui(controller) => new SwingGui(controller)
    case CreateTui(controller) =>
      val tui = new Tui(controller)
      sender ! tui
  }
}
