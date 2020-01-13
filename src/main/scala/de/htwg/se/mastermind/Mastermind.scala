package de.htwg.se.mastermind

import com.google.inject.{Guice, Injector}
import com.typesafe.scalalogging.Logger
import de.htwg.se.mastermind.aview.Tui
import de.htwg.se.mastermind.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Mastermind {
  val injector: Injector = Guice.createInjector(new MastermindModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui: Tui = new Tui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
