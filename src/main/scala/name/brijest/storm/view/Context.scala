package name.brijest.storm.view



import name.brijest.storm.model._




/* command contexts */

trait Context[+State <: GuiState] {
  val modelview: ModelView
  val guistate: State
  val player: PlayerOwner
}


case class BasicContext[+State <: GuiState](modelview: ModelView, guistate: State, player: PlayerOwner)
extends Context[State]








