package name.brijest.storm.model.impl.managers



import name.brijest.storm.model._



class DelegatingManager(val delegate: Manager) extends Manager {
  def timedAction(modelview: ModelView) = delegate.timedAction(modelview)
  def eventAction(e: Event, modelview: ModelView) = delegate.eventAction(e, modelview)
  def listens = delegate.listens
}
