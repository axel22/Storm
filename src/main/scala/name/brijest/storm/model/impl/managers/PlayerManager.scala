package name.brijest.storm.model.impl.managers


import name.brijest.storm.model._



abstract class PlayerManager(val playerId: pid) extends TimeOnlyManager {
  def delegate: Manager
  
  def timedAction(modelview: ModelView) = {
    delegate.timedAction(modelview)
  }
}


