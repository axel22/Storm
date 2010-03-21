package name.brijest.storm.model



import name.brijest.storm.model.impl.managers.PlayerManager



trait WorldOps {
  val players: collection.mutable.Map[pid, PlayerManager]
  def time_=(l: Long): Unit
  def newModelId: mid
  def newCharacterId: cid
  def newPlayerId: pid
}

trait WorldView {
  def time: Long
  def locateModel(modelId: mid): Option[ModelView]
  def locateCharacter(characterId: cid): Option[ModelView]
}

trait World extends WorldOps with WorldView {
self =>
  
  def locateModel(modelId: mid): Option[Model]
  def locateCharacter(characterId: cid): Option[Model]
  
  abstract class PlayerRPGCharacter(val playerId: pid) extends impl.characters.RPGCharacter {
    def playerColor: Color
    def manager: Manager = self.players(playerId)
    def representation = ('@', playerColor)
  }
}








