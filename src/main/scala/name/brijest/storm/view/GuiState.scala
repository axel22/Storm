package name.brijest.storm.view


import name.brijest.storm.model.gui.RenderAdapter
import name.brijest.storm.model._




abstract class GuiState(val renderAdapter: RenderAdapter) {
  def render(context: Context, topleft: (Int, Int)): Unit
  def matcher: CommandMatcher
}













