package name.brijest.storm.main


import name.brijest.storm.view._
import name.brijest.storm.model._
import name.brijest.storm.controller._

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
  
  def view(nm: String, mod: String, player: PlayerOwner, ctrl: Controller) = nm match {
    case "swing-console" =>
      val csi = new net.slashie.libjcsi.wswing.WSwingConsoleInterface("Storm", true)
      viewForMod(mod, new SwingConsoleRenderAdapter(csi), new SwingKeyboardInputAdapter(csi), player, ctrl)
    case _ => throw new BadArgument("No view named '" + nm + "'.")
  }
  
  private def viewForMod(mod: String, ra: RenderAdapter, ia: InputAdapter, player: PlayerOwner, ctrl: Controller) = mod match {
    case "turn-based" => new BasicView(ra, ia, player, ctrl)
    case _ => throw new BadArgument("No view for mod named '" + mod + "'.")
  }
  
  def controller(mod: String, w: World, player: PlayerOwner) = mod match {
    case "turn-based" => new BasicController(w, player.characterid, () => false)
    case _ => throw new BadArgument("No controller for mod named '" + mod + "'.")
  }
  
}






