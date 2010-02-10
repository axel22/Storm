package name.brijest.storm.view



import name.brijest.storm.view.impl.states._
import name.brijest.storm.view.impl.commands._






trait States {
base =>
  def renderAdapter: RenderAdapter
  
  def writeMessage(message: String) = renderAdapter.writeMessage(message)
  def clearMessages() = renderAdapter.clearMessages()
  
  class InventoryState extends States with GuiState {
    def renderAdapter = base.renderAdapter
    val matcher = null // TODO
    val renderer = null // TODO
  }
  
  /**
   * The state which renders the model and character stats.
   */
  class MainGuiState extends States with GuiState {
    def renderAdapter = base.renderAdapter
    val renderer = new MainRenderer with MapRenderer
    val matcher = new MappedCommandMatcher with MainCommandRepository
  }
  
}













