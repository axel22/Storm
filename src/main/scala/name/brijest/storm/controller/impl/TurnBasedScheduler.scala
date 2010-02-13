package name.brijest.storm.controller
package impl


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.model.impl.managers.PlayerManager


trait TurnBasedScheduler extends Scheduler {
  val queue = new mutable.PriorityQueue[(Long, Manager)]
  val listeners = new mutable.HashMap[EventMatcher, Manager]
  
  def onEvents(lst: List[Event])
  def owner: PlayerOwner
  
  def startScheduling {
    var gameover = false
    while (!gameover && !stopCondition) {
      world.locateCharacter(owner.characterid) match {
        case Some(m) => startActiveModelGame(m)
        case None => gameover = true
      }
    }
  }
  
  def registerManagers(managers: Seq[Manager]) {
    for (mng <- managers) {
      queue.enqueue((0, mng))
      for (evmatcher <- mng.listens) listeners += ((evmatcher, mng))
    }
  }
  
  def startActiveModelGame(model: Model) {
    // search for all the managers in the model
    queue.clear
    listeners.clear
    
    // initialize managers - collect them all, put them on time list and into event map
    val managers = model.extractManagers 
    registerManagers(managers)
    
    // start the event loop
    val adapter = model.createModelAdapter
    while (model.hasCharacter(owner.characterid) && !queue.isEmpty && !stopCondition) {
      val (time, manager) = queue.dequeue
      world.time = -time
      getAction(manager, model) match {
        case Some(action) =>
          val nextoffset = action.turnsNeeded(model)
          invokeAction(action, adapter)
          queue.enqueue((time - nextoffset, manager))
        case None =>
      }
    }
  }
  
  def getAction(manager: Manager, model: Model): Option[Action] = manager match {
    case pm @ PlayerManager(o) =>
      onEvents(List(PlayerTurn(o.index, model.id)))
      pm.waitForTimedAction(model)
    case _ =>
      manager.timedAction(model)
  }
  
  def invokeAction(action: Action, adapter: ModelAdapter) {
    val events = action(adapter)
    onEvents(events)
    for (a <- dispatchEvents(events, adapter)) invokeAction(a, adapter)
  }
  
  def dispatchEvents(events: List[Event], modelview: ModelView) = {
    var actions: List[Action] = Nil
    var toremove: List[EventMatcher] = Nil
    for (ev <- events) {
      for ((matcher, manager) <- listeners; if matcher.matches(ev)) {
        manager.eventAction(ev, modelview) match {
          case Some(action) => actions ::= action
          case None => toremove ::= matcher
        }
      }
    }
    for (matcher <- toremove) listeners -= matcher
    actions
  }
  
}















