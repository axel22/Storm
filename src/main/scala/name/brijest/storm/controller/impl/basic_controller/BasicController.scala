package name.brijest.storm.controller
package impl
package basic_controller


import name.brijest.storm.model._
import name.brijest.storm.view._

import scala.collection._



/**
 * Basic controller for a one-player turn-based game.
 */
abstract class BasicController
extends Controller with TurnBasedScheduler {
  
  def start {
    startScheduling
  }
  
//  class BootstrapManager(owner: PlayerOwner) extends TimeOnlyManager {
//    def timedAction(modelview: ModelView): Option[(Action, Long)] = {
//      // first render
//      render(modelview)
//      
//      // manage input action or change view
//      val commandcreator = guistate.inputAdapter.manageInput(guistate.matcher)
//      val command = commandcreator.create(BasicContext(modelview, guistate, owner))
//      guistate.renderAdapter.clearMessages
//      command match {
//        case ActionCommand(action) =>
//          Some((action, action.turnsNeeded(modelview.character(owner.characterid).get)))
//        case gct: GuiChangeActionCommand =>
//          guistate = gct.modify(guistate)
//          render(modelview)
//          Some((gct.action, gct.time))
//        case gct: GuiChangeCommand =>
//          guistate = gct.modify(guistate)
//          render(modelview)
//          timedAction(modelview)
//      }
//    }
//    private def render(modelview: ModelView) = {
//      guistate.render(BasicContext(modelview, guistate, player), (0, 0))
//      guistate.renderAdapter.flush
//    }
//  }
  
}

















