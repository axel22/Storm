package name.brijest.storm.view.impl.states



import name.brijest.storm.view.GuiState
import name.brijest.storm.view.RenderAdapter
import name.brijest.storm.view.impl.states.commands._





trait States {
base =>
  val renderAdapter: RenderAdapter
  
  def writeMessage(message: String) = renderAdapter.writeMessage(message)
  def clearMessages() = renderAdapter.clearMessages()
  
  class InventoryState extends States with GuiState with InventoryUtils with InventoryActions {
    val renderAdapter = base.renderAdapter
    val renderer = new InventoryRenderer[this.type] {}
    def location = (0, 0)
  }
  
  /**
   * The state which renders the model and character stats.
   */
  class MainGuiState extends States with GuiState with DialogActions with MovementActions {
    val renderAdapter = base.renderAdapter
    val renderer = new MainRenderer[this.type] with MapRenderer[this.type]
    var location = (0, 0)
  }
}













