package name.brijest.storm.view
package impl.states


import name.brijest.storm.model._
import name.brijest.storm.view._
import name.brijest.storm.view.impl.adapters._






/**
 * Renders map, message area, stats.
 */
class MainRenderer[State <: States#MainGuiState] extends Renderer[State] {
  def render(renderAdapter: RenderAdapter, context: Context[State]) {
    renderStats(renderAdapter, context)
  }
  
  def renderStats(ad: RenderAdapter, ctx: Context[State]) {
    // TODO
  }
}


trait MapRenderer[State <: GuiState] extends Renderer[State] {
  abstract override def render(renderAdapter: RenderAdapter, context: Context[State]) {
    renderMap(renderAdapter, context.modelview, context.guistate.location)
    //super.render(renderAdapter, context)
  }
  
  def renderMap(renderAdapter: RenderAdapter, view: ModelView, topleft: (Int, Int)) {
    renderAdapter.displayMap(view, topleft)
  }
}













