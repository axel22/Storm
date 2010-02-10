package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.model.model._



trait Renderer {
  def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int))
}































