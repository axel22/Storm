package name.brijest.storm.model
package impl.actions


import name.brijest.storm.model._
import name.brijest.storm.model.model._
import name.brijest.storm.model.impl.elements._


class EatOwnFood(chrid: cid, what: AbstractFood) extends Action with CharacterAction {
  def characterId = chrid
  def energycost = 0
  def timecost = what.eatingTime
  
  def performAction(ad: ModelAdapter) = asGameCharacter(ad.character(chrid)) { gc => 
    if (gc.stuff.remove(what)) {
      val energy = what.nutritionalValue
      gc.energypoints += energy
      val effect = what.deed
      gc.receiveDeed(effect)
    }
    Nil
  }.get
}


















