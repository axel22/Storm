package name.brijest.storm.main


import name.brijest.storm.view._
import name.brijest.storm.model._
import name.brijest.storm.controller._
import name.brijest.storm.model.gui._

import name.brijest.storm.controller.impl.basic_controller.BasicController
import name.brijest.storm.view.impl.views.BasicView
import name.brijest.storm.view.impl.adapters.SwingConsoleRenderAdapter
import name.brijest.storm.view.impl.input.SwingKeyboardInputAdapter



object Multiplexor {
  
  case class BadArgument(s: String) extends IllegalArgumentException(s)
  
  def worldCreator(name: String) = name match {
    case "test-world" => new impl.SimpleWorldCreator
    case _ => throw new BadArgument("No world creator named '" + name + "'.")
  }
  
  def view(nm: String, player: PlayerOwner) = nm match {
    case "swing-console" =>
      val csi = new net.slashie.libjcsi.wswing.WSwingConsoleInterface("Storm", true)
      new BasicView(new SwingConsoleRenderAdapter(csi), new SwingKeyboardInputAdapter(csi), player)
    case _ => throw new BadArgument("No view named '" + nm + "'.")
  }
  
  def controller(name: String, w: World, player: PlayerOwner) = name match {
    case "basic" => new BasicController {
      def world = w
      def targetCid = player.characterid
      def stopCondition = false
    }
    case _ => throw new BadArgument("No controller named '" + name + "'.")
  }
  
}






