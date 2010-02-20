package name.brijest.storm.view



import name.brijest.storm.view.impl.states._
import name.brijest.storm.view.impl.commands._






trait States {
base =>
  def renderAdapter: RenderAdapter
  
  def writeMessage(message: String) = renderAdapter.writeMessage(message)
  def clearMessages() = renderAdapter.clearMessages()
  
  class InventoryState extends States with GuiState with InventoryUtils {
    def renderAdapter = base.renderAdapter
    val matcher = null // TODO
    val renderer = new InventoryRenderer[this.type] {}
    def location = (0, 0)
  }
  
  /**
   * The state which renders the model and character stats.
   */
  class MainGuiState extends States with GuiState {
    def renderAdapter = base.renderAdapter
    val matcher = new MappedCommandMatcher with MainCommandRepository
    val renderer = new MainRenderer[this.type] with MapRenderer[this.type]
    var location = (0, 0)
  }
  
}













