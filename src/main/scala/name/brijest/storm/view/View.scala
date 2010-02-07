package name.brijest.storm.view



import name.brijest.storm.model._



abstract class View(renderAdapter: RenderAdapter, inputAdapter: InputAdapter) {
  def playerManager: TimeOnlyManager
  def player: PlayerOwner
  var guistate: GuiState
}
