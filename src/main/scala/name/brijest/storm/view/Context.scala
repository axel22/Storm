package name.brijest.storm.view



import name.brijest.storm.model._




/* command contexts */

trait Context[State <: GuiState] {
  def modelview: ModelView
  def guistate: State
  def player: PlayerOwner
}


case class BasicContext[State <: GuiState](modelview: ModelView, guistate: State, player: PlayerOwner)
extends Context[State]








