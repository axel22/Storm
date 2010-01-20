package name.brijest.storm.model.impl.managers


import name.brijest.storm.model._


case object PlayerManager extends TimeOnlyManager {
  var delegator: Manager = IdleManager
  def timedAction(modelview: ModelView) = delegator.timedAction(modelview)
}


