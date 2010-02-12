package name.brijest.storm.view
package impl.views


import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.view.impl.states.MainGuiState


class BasicView(ra: RenderAdapter, ia: InputAdapter, val player: PlayerOwner, c: Controller)
extends View(ra, ia, c) with States {
  var guistate: GuiState = new MainGuiState
  
  def playerManager = new BootstrapManager
    
  class BootstrapManager extends TimeOnlyManager {
    def timedAction(modelview: ModelView): Option[Action] = {
      // first render
      render(modelview)
      
      // manage input action or change view
      val commandcreator = ia.manageInput(guistate.matcher)
      val command = commandcreator.create(BasicContext(modelview, guistate, player))
      guistate.clearMessages
      command match {
        case ActionCommand(action) =>
          Some((action))
        case gct: GuiChangeActionCommand =>
          guistate = gct.modify(guistate)
          render(modelview)
          Some((gct.action))
        case gct: GuiChangeCommand =>
          guistate = gct.modify(guistate)
          render(modelview)
          timedAction(modelview)
      }
    }
    
    private def render(modelview: ModelView) = {
      guistate.renderer.render(ra, BasicContext(modelview, guistate, player), (0, 0))
      ra.flush
    }
  }

  
}








