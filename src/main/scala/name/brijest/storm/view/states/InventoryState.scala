package name.brijest.storm.view
package states


import name.brijest.storm.model.gui._


class InventoryState(val inputAdapter: InputAdapter, val renderAdapter: RenderAdapter) extends GuiState {
  def matcher = null
  def render(context: Context, topleft: (Int, Int)) {
    
  }
}

object InventoryRenderer extends Renderer {
  override def render(renderAdapter: RenderAdapter, context: Context, topleft: (Int, Int)) {
    renderAdapter.displayListDialog()
  }
}