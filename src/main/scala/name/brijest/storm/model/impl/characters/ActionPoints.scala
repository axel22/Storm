package name.brijest.storm.model
package impl.characters


import name.brijest.storm.model.Character
import name.brijest.storm.model.CharacterAction


trait ActionPointsView extends CharacterView {
  def actionpoints: Long
  def speed: Int
}

trait ActionPoints extends Character with ActionPointsView {
  var actionpoints: Long = _
  
  abstract override def onOwnAction(a: CharacterAction) {
    super.onOwnAction(a)
    val neededturns = a.turnsNeeded(this)
    actionpoints -= a.timecost
    if (actionpoints > 0) actionpoints = 0
    actionpoints += neededturns * speed
  }
  
}
