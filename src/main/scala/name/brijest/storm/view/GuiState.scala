package name.brijest.storm.view


import name.brijest.storm.model._
import name.brijest.storm.view.impl.states._



trait GuiState extends States {
  def renderer: Renderer
  def matcher: CommandMatcher
  def writeMessage(message: String): Unit
  def clearMessages(): Unit
}












