package name.brijest.storm.view



import name.brijest.storm.model._



trait Renderer[State <: GuiState] {
  def render(renderAdapter: RenderAdapter, context: Context[State])
}































