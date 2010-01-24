package name.brijest.storm.view


import name.brijest.storm.model.gui.RenderAdapter
import name.brijest.storm.model._


trait GuiState {
  def render(context: Context, topleft: (Int, Int)): Unit
  def renderAdapter: RenderAdapter
  def inputAdapter: InputAdapter
  def matcher: CommandMatcher
}













