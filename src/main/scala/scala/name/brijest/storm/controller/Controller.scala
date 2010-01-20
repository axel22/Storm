package name.brijest.storm.controller


import name.brijest.storm.model._
import name.brijest.storm.view._


abstract class Controller(val world: World, var guistate: GuiState) extends Observable {
  /**
   * Searches for all the managers and starts
   * the event loop.
   */
  def start
}











