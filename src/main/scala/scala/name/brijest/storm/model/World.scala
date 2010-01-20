package name.brijest.storm.model



trait ModelRepository {
  def locateModel(modelId: Long): Option[Model]
}

trait World extends ModelRepository {
  def time: Long
  def time_=(l: Long): Unit
  def locateCharacter(characterId: Long): Option[Model]
  def newModelId: Long
  def newCharacterId: Long
  def newPlayerId: Int
}


