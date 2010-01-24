package name.brijest.storm.view


import name.brijest.storm.model._
import name.brijest.storm.model.gui._


trait Renderer {
  def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int))
}















