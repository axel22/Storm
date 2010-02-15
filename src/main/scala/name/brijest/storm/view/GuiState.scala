package name.brijest.storm.view


import name.brijest.storm.model._
import name.brijest.storm.view.impl.states._





trait GuiState extends States {
  def renderer: Renderer[this.type]
  def matcher: CommandMatcher
  def location: (Int, Int)
  def writeMessage(message: String): Unit
  def clearMessages(): Unit
  def createContext(mv: ModelView, p: PlayerOwner): Context[this.type] = new BasicContext[this.type](mv, this, p)
}














