package name.brijest.storm.model.impl.actions


import name.brijest.storm.model.gcid
import name.brijest.storm.model.ModelAdapter


class MoveCharacter(chrid: gcid, dest: (Int, Int))
extends DisplaceCharacter(chrid, dest) with CharacterAction {
  def timecost = 1000
  def energycost = 1000
  def characterId = chrid
  
  def performAction(m: ModelAdapter) = super[DisplaceCharacter].apply(m)
}







