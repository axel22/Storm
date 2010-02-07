package name.brijest.storm.view
package impl.states


import name.brijest.storm.model._
import name.brijest.storm.model.model._
import name.brijest.storm.model.model._
import name.brijest.storm.view._
import name.brijest.storm.view.impl.commands._
import name.brijest.storm.view.impl.adapters._


/**
 * The state which renders the model and character stats.
 */
class MainGuiState(ra: RenderAdapter) extends GuiState(ra) {
  self =>
  val renderer = new MainRenderer with MapRenderer
  def render(context: Context, topleft: (Int, Int)) = renderer.render(renderAdapter, context, topleft)
  val matcher = new MappedCommandMatcher with MainCommandRepository
}

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














