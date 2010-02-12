package name.brijest.storm.worlds


import name.brijest.storm.model._
import name.brijest.storm.model.impl.managers.PlayerManager

import scala.collection._


class BasicWorld() extends World {
  private var models = new mutable.HashMap[mid, Model]
  private var plidc = 0
  private var modidc = 0L
  private var chridc = 0L
  
  val players = new mutable.HashMap[pid, PlayerManager]
  
  var time = 0L
  
  def addModel(m: Model) = models.put(m.id, m)
  
  def locateCharacter(id: cid) = models.values.find(_.hasCharacter(id))
  
  def locateModel(id: mid) = models.get(id)
  
  def newPlayerId = {
    plidc += 1
    pid(plidc)
  }
  def newCharacterId = {
    chridc += 1
    cid(chridc)
  }
  def newModelId = {
    modidc += 1
    mid(modidc)
  }
}






