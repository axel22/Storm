package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.controller._



abstract class View(val renderAdapter: RenderAdapter, val inputAdapter: InputAdapter) {
  var ctrl: Controller = _
  
  var guistate: GuiState
  def init
}
