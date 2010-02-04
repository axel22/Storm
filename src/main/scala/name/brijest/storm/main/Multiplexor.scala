package name.brijest.storm.main


import name.brijest.storm.view._
import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.model.gui._

import name.brijest.storm.controller.impl.basic_controller.BasicController
import name.brijest.storm.view.states.MainGuiState


object Multiplexor {
  
  case class BadArgument(s: String) extends IllegalArgumentException(s)
  
  def worldCreator(name: String) = name match {
    case "test-world" => new impl.SimpleWorldCreator
    case _ => throw new BadArgument("No world creator named '" + name + "'.")
  }
  
  def gui(nm: String) = nm match {
    case "swing-console" =>
      new name.brijest.storm.view.guis.SwingConsoleGui(
    		  new net.slashie.libjcsi.wswing.WSwingConsoleInterface("Storm", true))
    case _ => throw new BadArgument("No render adapter named '" + nm + "'.")
  }
  
  def controller(name: String, w: World, player: PlayerOwner, gui: Gui) = name match {
    case "basic" => new BasicController(player.characterid) {
      def view = gui.asInstanceOf[View]
      def world = w
    }
    case _ => throw new BadArgument("No controller named '" + name + "'.")
  }
  
}






