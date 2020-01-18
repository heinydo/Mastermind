package de.htwg.se.mastermind
import java.awt.GraphicsEnvironment
import java.io.BufferedReader

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.google.inject.{Guice, Injector}
import de.htwg.se.mastermind.aview.UiMessage.{Crash, CreateGui, CreateTui}
import de.htwg.se.mastermind.aview.{HttpServer, Tui, UiFactory}
import de.htwg.se.mastermind.controller.controllerComponent.ControllerInterface

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn.readLine

object Mastermind {
  implicit val timeout: Timeout = Timeout(29.seconds) // used for akka ask pattern

  val injector: Injector = Guice.createInjector(new MastermindModule)

  implicit val actorSystem = ActorSystem("actorSystem")
  val uiFactory = actorSystem.actorOf(Props[UiFactory])

  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tuiFuture = uiFactory ? CreateTui(controller)
  val tui = Await.result(tuiFuture.mapTo[Tui], Duration.Inf)

  if (!GraphicsEnvironment.isHeadless) {
    uiFactory ! CreateGui(controller)
  }

  val webserver = new HttpServer(controller)
  controller.createEmptyBoard()

  def main(args: Array[String]): Unit = {
    tui.processInput(new BufferedReader(Console.in))
    webserver.unbind()
  }
}
