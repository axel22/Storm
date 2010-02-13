package name.brijest.storm.view



import name.brijest.storm.model._
import name.brijest.storm.controller._



abstract class View(val renderAdapter: RenderAdapter, val inputAdapter: InputAdapter, val ctrl: Controller) {
  var guistate: GuiState
  
  def init
}
