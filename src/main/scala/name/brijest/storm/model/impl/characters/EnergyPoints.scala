package name.brijest.storm.model
package impl.characters


import name.brijest.storm.model.Character
import name.brijest.storm.model.CharacterAction


trait EnergyPointsView extends CharacterView {
  def energypoints: Long
}

trait EnergyPoints extends Character with EnergyPointsView {
  var energypoints: Long = _
  
  abstract override def onOwnAction(a: CharacterAction) {
    super.onOwnAction(a)
    energypoints -= a.energycost
  }
  
}



