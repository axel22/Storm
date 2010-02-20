package name.brijest.storm.view
package impl.views


import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.controller.impl.commands._


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
    val commandcreator = ia.manageInput(guistate.matcher)
    val command = commandcreator.create(BasicContext(modelview, guistate, player))
    guistate.clearMessages
    command match {
      case ActionCommand(action) =>
        ctrl.send(PlayerCommand(player.index, action))
      case gct: GuiChangeActionCommand =>
        guistate = gct.modify(guistate)
        render(modelview)
        ctrl.send(PlayerCommand(player.index, gct.action))
      case gct: GuiChangeCommand =>
        guistate = gct.modify(guistate)
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








