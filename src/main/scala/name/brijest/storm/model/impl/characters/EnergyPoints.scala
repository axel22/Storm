package name.brijest.storm.model.impl.characters


import name.brijest.storm.model.Character
import name.brijest.storm.model.CharacterAction


trait EnergyPoints extends Character {
  var energypoints: Long = _
  
  abstract override def onOwnAction(a: CharacterAction) {
    super.onOwnAction(a)
    energypoints -= a.energycost
  }
  
}



