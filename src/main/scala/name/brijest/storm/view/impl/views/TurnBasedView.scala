package name.brijest.storm.view
package impl.views


import scala.annotation.unchecked.uncheckedStable

import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.controller.impl.commands._
import name.brijest.storm.view.impl.states.States


class TurnBasedView(ra: RenderAdapter, ia: InputAdapter, val player: PlayerOwner)
extends View(ra, ia) with States {
  var guistate: GuiState = new MainGuiState
  
  def init = {
    registerListeners
  }
  
  private def registerListeners {
    ctrl.addObserver(PlayerTurnEvents(player.index)) { e: Event =>
      val model = ctrl.world.locateModel(e.modelId).get
      render(model)
      handleInput(model)
    }
  }
  
  private def handleInput(modelview: ModelView) {
    // manage input action or change view
    val gs = guistate
    val commandcreator = ia.manageInput[gs.type](gs.commands.matcher)
    val command = commandcreator.create(BasicContext[gs.type](modelview, gs, player))
    guistate.clearMessages
    command match {
      case gs.commands.ActionCommand(action) =>
        ctrl.send(PlayerCommand(player.index, action))
      case gs.commands.GuiChangeActionCommand(action, modifier) =>
        guistate = modifier(gs)
        render(modelview)
        ctrl.send(PlayerCommand(player.index, action))
      case gs.commands.GuiChangeCommand(modifier) =>
        guistate = modifier(gs)
        render(modelview)
        handleInput(modelview)
    }
  }
  
  private def render(modelview: ModelView) = {
    val gs = guistate // obtain a stable reference
    gs.renderer.render(ra, gs.createContext(modelview, player))
    ra.flush
  }
  
}








