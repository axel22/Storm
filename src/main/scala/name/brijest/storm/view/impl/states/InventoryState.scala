package name.brijest.storm.view
package impl.states


import name.brijest.storm.model.gui._


class InventoryState(ra: RenderAdapter)
extends GuiState(ra) {
  def matcher = null
  def render(context: Context, topleft: (Int, Int)) {
    
  }
}

object InventoryRenderer extends Renderer {
  override def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderAdapter.displayListDialog()
  }
}