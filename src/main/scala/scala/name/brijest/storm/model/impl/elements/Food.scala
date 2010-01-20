package name.brijest.storm.model.impl.elements



import name.brijest.storm.model._


abstract class Food extends Element {
  def color: Color
  def representation = ('=', color)
  
  def nutritionalValue: Long
  def eatingTime: Long
  def deed: Option[Deed]
}
