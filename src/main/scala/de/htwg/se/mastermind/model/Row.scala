package de.htwg.se.mastermind.model

case class Row(prediction : Prediction, predictionHint : PredictionHint){

  def this(numberOfPegs: Int) = this(new Prediction(numberOfPegs), new PredictionHint(numberOfPegs))
  def turnHintSize: Int = predictionHint.size
  def turnSize: Int = prediction.size
  def replacePegs(colVec: Vector[Peg[Color]], hints: Vector[Hint]): Row = copy(prediction.updatePegs(colVec), null)
  def isSet: Boolean = prediction.containsEmptyColor
}
