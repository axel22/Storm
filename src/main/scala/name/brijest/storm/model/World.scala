package name.brijest.storm.model



trait ModelRepository {
  def locateModel(modelId: mid): Option[Model]
}

trait World extends ModelRepository {
  def time: Long
  def time_=(l: Long): Unit
  def locateCharacter(characterId: cid): Option[Model]
  def newModelId: mid
  def newCharacterId: cid
  def newPlayerId: pid
}


