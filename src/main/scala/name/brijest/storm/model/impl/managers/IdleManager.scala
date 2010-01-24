package name.brijest.storm.model.impl.managers


import name.brijest.storm.model._


case object IdleManager extends TimeOnlyManager {
  def timedAction(mv: ModelView) = None
}
