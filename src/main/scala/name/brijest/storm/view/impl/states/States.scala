package name.brijest.storm.view



import name.brijest.storm.view.impl.states._
import name.brijest.storm.view.impl.commands._






trait States {
base =>
  val renderAdapter: RenderAdapter
  
  def writeMessage(message: String) = renderAdapter.writeMessage(message)
  def clearMessages() = renderAdapter.clearMessages()
  
  class InventoryState extends States with GuiState with InventoryUtils {
    val renderAdapter = base.renderAdapter
    val matcher = null // TODO
    val renderer = null // new InventoryRenderer[this.type] {}
    def location = (0, 0)
  }
  
  /**
   * The state which renders the model and character stats.
   */
  class MainGuiState extends States with GuiState {
    val renderAdapter = base.renderAdapter
    val matcher = null // new MappedCommandMatcher[this.type] with MainCommandRepository[this.type]
    val renderer = new MainRenderer[this.type] with MapRenderer[this.type]
    var location = (0, 0)
  }
  
}













