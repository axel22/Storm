package name.brijest.storm.model.impl.managers


import name.brijest.storm.model._



abstract class PlayerManager(val playerId: pid) extends TimeOnlyManager {
  def world: World
  
  def timedAction(modelview: ModelView) = world.players(playerId).timedAction(modelview)
}


