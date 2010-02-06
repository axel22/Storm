package name.brijest.storm.model







trait WorldOps {
  def time_=(l: Long): Unit
  def newModelId: mid
  def newCharacterId: cid
  def newPlayerId: pid
}

trait WorldView {
  def players: collection.mutable.Map[pid, TimeOnlyManager]
  def time: Long
  def locateModel(modelId: mid): Option[ModelView]
  def locateCharacter(characterId: cid): Option[ModelView]
}

trait World extends WorldOps with WorldView {
  def locateModel(modelId: mid): Option[Model]
  def locateCharacter(characterId: cid): Option[Model]
}








