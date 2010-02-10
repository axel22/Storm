package name.brijest.storm.view
package impl.states


import name.brijest.storm.model._
import name.brijest.storm.model.model._
import name.brijest.storm.model.model._
import name.brijest.storm.view._
import name.brijest.storm.view.impl.adapters._






/**
 * Renders map, message area, stats.
 */
class MainRenderer extends Renderer {
  def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderStats(renderAdapter, context)
  }
  
  def renderStats(ad: RenderAdapter, ctx: Context) {
    // TODO
  }
}


trait MapRenderer extends Renderer {
  abstract override def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderMap(renderAdapter, context.modelview, topleft)
    super.render(renderAdapter, context, topleft)
  }
  
  def renderMap(renderAdapter: RenderAdapter, view: ModelView, topleft: (Int, Int)) {
    renderAdapter.displayMap(view, topleft)
  }
}













