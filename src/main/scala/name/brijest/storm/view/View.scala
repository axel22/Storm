package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.model.gui.RenderAdapter



abstract class View(renderAdapter: RenderAdapter, inputAdapter: InputAdapter) {
  def playerManager: TimeOnlyManager
  def player: PlayerOwner
  var guistate: GuiState
}
