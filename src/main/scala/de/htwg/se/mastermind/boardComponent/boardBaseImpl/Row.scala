package de.htwg.se.mastermind.model.boardComponent.boardBaseImpl

case class Row(prediction : Prediction, predictionHint : PredictionHint){

  def this(numberOfPegs: Int) = this(new Prediction(numberOfPegs), new PredictionHint(numberOfPegs))
  def predictionHintSize: Int = predictionHint.size
  def predictionSize: Int = prediction.size
  def replaceRow(colVec: Vector[Peg[Color]], hints: Vector[Peg[Hint]]): Row = copy(prediction.updatePegs(colVec), predictionHint.replacePegs(hints))
  def isSet: Boolean = !prediction.containsEmptyColor
}
