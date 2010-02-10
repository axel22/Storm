package name.brijest.storm.view



import name.brijest.storm.model._



abstract class View(val renderAdapter: RenderAdapter, val inputAdapter: InputAdapter) {
  def playerManager: TimeOnlyManager
  def player: PlayerOwner
  var guistate: GuiState
}
