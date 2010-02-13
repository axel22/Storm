package name.brijest.storm.model
package impl
package managers


import name.brijest.storm.model._
import name.brijest.storm.model.impl.actions.NoAction



case class PlayerManager(val owner: PlayerOwner) extends TimeOnlyManager {
  private val actions = new collection.mutable.Queue[Action]
  
  def timedAction(modelview: ModelView): Option[Action] = synchronized {
    if (actions.length == 0) Some(NoAction)
    else Some(actions.dequeue)
  }
  
  def waitForTimedAction(modelview: ModelView): Option[Action] = synchronized {
    if (actions.length == 0) wait
    Some(actions.dequeue)
  }
  
  def push(a: Action) = synchronized {
    actions.enqueue(a)
    notify
  }
}



