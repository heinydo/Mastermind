package de.htwg.se.mastermind

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.mastermind.controller.controllerComponent.{ControllerInterface, _}
import de.htwg.se.mastermind.model.boardComponent.BoardInterface
import de.htwg.se.mastermind.model.boardComponent.boardAdvancedImpl.Board
import net.codingwell.scalaguice.ScalaModule


class MastermindModule extends AbstractModule with ScalaModule {

  val defaultNumberOfRounds: Int = 10
  val defaultNumberOfPegs: Int = 4

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultNumberOfRounds")).to(defaultNumberOfRounds)
    bindConstant().annotatedWith(Names.named("DefaultNumberOfPegs")).to(defaultNumberOfPegs)
    bind[BoardInterface].annotatedWithName("normal").toInstance(new Board(defaultNumberOfPegs, defaultNumberOfRounds))
    bind[BoardInterface].to[Board]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
  }
}
