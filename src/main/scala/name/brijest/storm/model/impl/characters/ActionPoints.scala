package name.brijest.storm.model.impl.characters


import name.brijest.storm.model.Character
import name.brijest.storm.model.CharacterAction


trait ActionPoints extends Character {
  var actionpoints: Long = _
  def speed: Int
  
  abstract override def onOwnAction(a: CharacterAction) {
    super.onOwnAction(a)
    val neededturns = a.turnsNeeded(this)
    actionpoints -= a.timecost
    if (actionpoints > 0) actionpoints = 0
    actionpoints += neededturns * speed
  }
  
}
