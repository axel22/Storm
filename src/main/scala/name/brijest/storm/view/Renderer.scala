package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.model.model._



trait Renderer[State <: GuiState] {
  def render(renderAdapter: RenderAdapter, context: Context[State])
}































