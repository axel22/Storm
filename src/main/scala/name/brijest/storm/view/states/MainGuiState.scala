package name.brijest.storm.view.states


import name.brijest.storm.model.gui._
import name.brijest.storm.model._
import name.brijest.storm.model.model._
import name.brijest.storm.view._
import name.brijest.storm.view.guis._


/**
 * The state which renders the model and character stats.
 */
case class MainGuiState(inputAdapter: InputAdapter, renderAdapter: RenderAdapter) extends GuiState {
  self =>
  val renderer = new MainRenderer
  def render(context: Context, topleft: (Int, Int)) = renderer.render(renderAdapter, context, topleft)
  val matcher = new commands.MappedCommandMatcher with MainCommandRepository
}

/**
 * Renders map, message area, stats.
 */
class MainRenderer extends Renderer {
  def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderMap(renderAdapter, context.modelview, topleft)
    renderStats(renderAdapter, context, topleft)
  }
  
  def renderMap(renderAdapter: RenderAdapter, view: ModelView, topleft: (Int, Int)) {
    val dims = renderAdapter.mapDimensions
    for (pos <- topleft to (topleft + dims._2)) {
      val targetpos = pos - topleft
      (view characterAt pos) match {
        case None => (view elementsAt pos) match {
          case Nil => renderAdapter.drawTerrainAt(targetpos, (view terrainAt pos))
          case elem :: tail => renderAdapter.drawElementAt(targetpos, elem)
        }
        case Some(chr) => renderAdapter.drawElementAt(targetpos, chr)
      }
    }
  }
  
  def renderStats(ad: RenderAdapter, ctx: Context, topleft: (Int, Int)) {
    // TODO
  }
}














