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
}




























