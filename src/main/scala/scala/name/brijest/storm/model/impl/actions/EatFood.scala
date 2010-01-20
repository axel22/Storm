package name.brijest.storm.model.impl.actions


import name.brijest.storm.model._
import name.brijest.storm.model.model._
import name.brijest.storm.model.impl.elements._


class EatFood(chrid: Long, what: Food) extends Action {
  def characterId = chrid
  def energycost = 0
  def timecost = what.eatingTime
  
  def apply(ad: ModelAdapter) = asGameCharacter(ad.character(chrid))(gc => {
    if (gc.stuff.remove(what)) {
      val energy = what.nutritionalValue
      val effect = what.deed
      gc.energypoints += energy
      gc.receiveDeed(effect)
    }
  })
}


















