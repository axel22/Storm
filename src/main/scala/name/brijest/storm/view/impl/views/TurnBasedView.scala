package name.brijest.storm.view
package impl.views


import name.brijest.storm.model._
import name.brijest.storm.view.impl.states.MainGuiState


class TurnBasedView(ra: RenderAdapter, ia: InputAdapter, val player: PlayerOwner) extends View(ra, ia) {
  var guistate: GuiState = new MainGuiState(ra)
  
  def playerManager = new BootstrapManager
    
  class BootstrapManager extends TimeOnlyManager {
    def timedAction(modelview: ModelView): Option[(Action, Long)] = {
      // first render
      render(modelview)
      
      // manage input action or change view
      val commandcreator = ia.manageInput(guistate.matcher)
      val command = commandcreator.create(BasicContext(modelview, guistate, player))
      guistate.renderAdapter.clearMessages
      command match {
        case ActionCommand(action) =>
          Some((action, action.turnsNeeded(modelview.character(player.characterid).get)))
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

  
}








