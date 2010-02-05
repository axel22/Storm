package name.brijest.storm.controller
package impl


import scala.collection._

import name.brijest.storm.model._


trait TurnBasedScheduler extends Scheduler {
  val queue = new mutable.PriorityQueue[(Long, Manager)]
  val listeners = new mutable.HashMap[EventMatcher, Manager]
  
  def onEvents(lst: List[Event])
  def targetCid: cid
  
  def startScheduling {
    var gameover = false
    while (!gameover && !stopCondition) {
      world.locateCharacter(targetCid) match {
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
    while (model.hasCharacter(targetCid) && !queue.isEmpty && !stopCondition) {
      val (time, manager) = queue.dequeue
      world.time = -time
      manager.timedAction(model) match {
        case Some((action, nextoffset)) =>
          action(adapter)
          queue.enqueue((time - nextoffset, manager))
        case None =>
      }
    }
  }
  
  def invokeAction(action: Action, modelview: ModelView, adapter: ModelAdapter) {
    val events = action(adapter)
    onEvents(events)
    for (a <- dispatchEvents(events, modelview)) invokeAction(a, modelview, adapter)
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















