package name.brijest.storm.worlds


import name.brijest.storm.model._
import scala.collection._


class BasicWorld() extends World {
  private var models = new mutable.HashMap[Long, Model]
  private var plidc = 0
  private var modidc = 0L
  private var chridc = 0L
  
  var time = 0L
  def addModel(m: Model) = models.put(m.id, m)
  def locateCharacter(id: Long) = models.values.find(_.hasCharacter(id))
  def locateModel(id: Long) = models.get(id)
  def newPlayerId = {
    plidc += 1
    plidc
  }
  def newCharacterId = {
    chridc += 1
    chridc
  }
  def newModelId = {
    modidc += 1
    modidc
  }
}






