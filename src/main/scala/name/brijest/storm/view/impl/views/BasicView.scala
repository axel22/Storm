package name.brijest.storm.view
package impl.views


import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.controller.impl.commands._
import name.brijest.storm.view.impl.states.MainGuiState


class BasicView(ra: RenderAdapter, ia: InputAdapter, val player: PlayerOwner, c: Controller)
extends View(ra, ia, c) with States {
  var guistate: GuiState = new MainGuiState
  
  def init = {
    registerListeners
    loop
  }
  
  private def registerListeners {
    c.addObserver(AllEvents) { e: Event =>
      render(c.world.locateModel(e.modelId).get)
    }
  }
    
  private def loop {
    var stop = false
    do {
      ctrl.world.locateCharacter(player.characterid) match {
        case None => stop = true
        case Some(modelview) => handleInput(modelview)
      }
    } while (!stop)
  }
  
  private def handleInput(modelview: ModelView) {
    render(modelview)
    
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
    guistate.renderer.render(ra, BasicContext(modelview, guistate, player), (0, 0))
    ra.flush
  }
  
}








