package name.brijest.storm.main


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.view.guis._
import name.brijest.storm.view.input._
import name.brijest.storm.view.commands._
import name.brijest.storm.view._


object Main {
  
  def printHelp {
    println("Arguments: -world=<world-name> -renderer=<render-method> -ctrl=<controller>")
    println("where:    world-name - the name of the world to run")
    println("          renderer   - the method used to render to screen (for instance, swing-console)")
    println("          ctrl       - the controller used to schedule events (for instance, basic)")
  }
  
  def main(args: Array[String]) {
    try {
      val map = ArgExtractor.extractArgs(args)
      run(map)
    } catch {
      case Multiplexor.BadArgument(msg) =>
        println(msg)
        printHelp
      case e @ _ => e.printStackTrace
    }
  }
  
  def run(argmap: Map[String, String]) {
    val gui = Multiplexor.gui(argmap("renderer"))
    val creator = Multiplexor.worldCreator(argmap("world"))
    val world = creator.createWorld
    val player = new PlayerOwner(1, gcid(1))
    val ctrl = Multiplexor.controller(argmap("ctrl"), world, player, gui)
    ctrl.start
  }
  
}
















