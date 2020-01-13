package de.htwg.se.mastermind.model.boardComponent.boardAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.mastermind.model.boardComponent.BoardInterface
import de.htwg.se.mastermind.model.boardComponent.boardBaseImpl.{Row, Board => BaseBoard}

class Board @Inject()(@Named("DefaultNumberOfPegs") numberOfPegs: Int, @Named("DefaultNumberOfRounds") numberOfRows: Int) extends BaseBoard(numberOfRows,numberOfPegs) {
  def createEmptyBoard: BoardInterface = BaseBoard(Vector.fill(numberOfRows)(new Row(numberOfPegs)), BaseBoard.randomSolution(numberOfPegs))
}