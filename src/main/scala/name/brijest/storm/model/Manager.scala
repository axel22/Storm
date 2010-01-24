package name.brijest.storm.model



trait Manager extends Ordered[Manager] {
  /**
   * Returns an action which is to be done, and an index saying
   * how many units of time must pass before the manager is invoked
   * again.
   */
  def timedAction(modelview: ModelView): Option[(Action, Long)]
  /**
   * Returns an action if the specified event occured.
   */
  def eventAction(e: Event, modelview: ModelView): Option[Action]
  def listens: List[EventMatcher]
  def compare(that: Manager) = 0
  override def hashCode = 5463
  override def equals(that: Any) = that match {
    case m: Manager => true
    case _ => false
  }
}

trait TimeOnlyManager extends Manager {
  def eventAction(e: Event, modelview: ModelView) = None
  def listens = Nil
}

trait EventOnlyManager extends Manager {
  def timedAction = None
}





