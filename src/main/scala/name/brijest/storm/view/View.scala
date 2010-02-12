package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.controller._



abstract class View(val renderAdapter: RenderAdapter, val inputAdapter: InputAdapter, val ctrl: Controller) {
  def playerManager: TimeOnlyManager
  def player: PlayerOwner
  var guistate: GuiState
}
