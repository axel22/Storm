package name.brijest.storm.controller.impl.basic_controller


import name.brijest.storm.model._
import name.brijest.storm.view._
import name.brijest.storm.controller._
import name.brijest.storm.model.impl.managers.PlayerManager

import scala.collection._



/**
 * Basic controller for a one-player turn-based game.
 */
class BasicController(w: World, player: PlayerOwner, gs: GuiState) extends Controller(w, gs) {
  val queue = new mutable.PriorityQueue[(Long, Manager)]
  val listeners = new mutable.HashMap[EventMatcher, Manager]
  
  class BootstrapManager(owner: PlayerOwner) extends TimeOnlyManager {
    def timedAction(modelview: ModelView): Option[(Action, Long)] = {
      // first render
      render(modelview)
      
      // manage input action or change view
      val commandcreator = guistate.inputAdapter.manageInput(guistate.matcher)
      val command = commandcreator.create(BasicContext(modelview, guistate, owner))
      guistate.renderAdapter.clearMessages
      command match {
        case ActionCommand(action) =>
          Some((action, action.turnsNeeded(modelview.character(owner.characterid).get)))
        case gct: GuiChangeActionCommand =>
          guistate = gct.modify(guistate)
          render(modelview)
          Some((gct.action, gct.time))
        case gct: GuiChangeCommand =>
          guistate = gct.modify(guistate)
          render(modelview)
          timedAction(modelview)
      }
    }
    private def render(modelview: ModelView) = {
      guistate.render(BasicContext(modelview, guistate, player), (0, 0))
      guistate.renderAdapter.flush
    }
  }
  
  def start {
    var gameover = false
    while (!gameover) w.locateCharacter(player.characterid) match {
      case None => gameover = true
      case Some(model) => startActiveModelGame(model)
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
    
    // initialize player manager
    model.characters.find(_.owner.isInstanceOf[PlayerOwner]) match {
      case Some(chr) =>
        PlayerManager.delegator = new BootstrapManager(chr.owner.asInstanceOf[PlayerOwner])
      case None =>
    }
    
    // start the event loop
    val adapter = model.createModelAdapter
    while (model.hasCharacter(player.characterid) && !queue.isEmpty) {
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
    action(adapter)
    onEvents(action.events)
    for (a <- dispatchEvents(action.events, modelview)) invokeAction(a, modelview, adapter)
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

















