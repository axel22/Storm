package name.brijest.storm.main


import scala.collection.Map
import scala.actors.Actor._

import name.brijest.storm.view._
import name.brijest.storm.model._
import name.brijest.storm.controller._

import name.brijest.storm.model.impl.managers.PlayerManager
import name.brijest.storm.controller.impl.basic_controller.BasicController
import name.brijest.storm.view.impl.views.TurnBasedView
import name.brijest.storm.view.impl.adapters.SwingConsoleRenderAdapter
import name.brijest.storm.view.impl.input.SwingKeyboardInputAdapter



object Multiplexor {
  
  case class BadArgument(s: String) extends IllegalArgumentException(s)
  
  def worldCreator(name: String, argmap: Map[String, String]) = name match {
    case "test-world" => new impl.TestWorldCreator
    case _ => throw new BadArgument("No world creator named '" + name + "'.")
  }
  
  def controller(mod: String, w: World, owner: PlayerOwner, argmap: Map[String, String]) = mod match {
    case "turn-based" => new BasicController(w, owner, () => false)
    case _ => throw new BadArgument("No controller for mod named '" + mod + "'.")
  }
  
  def requestOwner(mod: String, w: World, argmap: Map[String, String]) = mod match {
    case "turn-based" =>
      val (playerid, pm) = w.players.iterator.next
      new PlayerOwner(playerid, pm.owner.characterid)
    case _ => throw new BadArgument("Cannot fetch owner for mod '" + mod + "'.")
  }
  
  def view(nm: String, mod: String, owner: PlayerOwner, argmap: Map[String, String]) = nm match {
    case "swing-console" =>
      val csi = new net.slashie.libjcsi.wswing.WSwingConsoleInterface("Storm", true)
      viewForMod(mod, new SwingConsoleRenderAdapter(csi), new SwingKeyboardInputAdapter(csi), owner, argmap)
    case _ => throw new BadArgument("No view named '" + nm + "'.")
  }
  
  private def viewForMod(mod: String, ra: RenderAdapter, ia: InputAdapter, owner: PlayerOwner, argmap: Map[String, String]) = mod match {
    case "turn-based" => new TurnBasedView(ra, ia, owner)
    case _ => throw new BadArgument("No view for mod '" + mod + "'.")
  }
  
  def deploy(mod: String, w: World, c: Controller, v: View, argmap: Map[String, String]) = mod match {
    case "turn-based" => deployTurnBased(w, c, v, argmap)
    case _ => throw new BadArgument("No deployment method for mod '" + mod + "'.")
  }
  
  def deployTurnBased(w: World, ctrl: Controller, view: View, argmap: Map[String, String]) {
    view.ctrl = ctrl
    view.init
    ctrl.start
  }
  
}






