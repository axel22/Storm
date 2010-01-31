package name.brijest.storm.model.impl.actions


import name.brijest.storm.model._


class MoveCharacter(chrid: cid, dest: (Int, Int))
extends DisplaceCharacter(chrid, dest) with CharacterAction {
  def timecost = 1000
  def energycost = 1000
  def characterId = chrid
}







