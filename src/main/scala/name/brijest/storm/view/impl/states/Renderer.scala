package name.brijest.storm.view
package impl.states



import name.brijest.storm.model._
import name.brijest.storm.model.model._



trait Renderer {
  def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int))
}


trait MapRenderer extends Renderer {
  abstract override def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderMap(renderAdapter, context.modelview, topleft)
  }
  
  def renderMap(renderAdapter: RenderAdapter, view: ModelView, topleft: (Int, Int)) {
    renderAdapter.displayMap(view, topleft)
  }
}




























