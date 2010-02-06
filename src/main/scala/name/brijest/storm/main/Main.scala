package name.brijest.storm.main


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.view.impl.adapters._
import name.brijest.storm.view.impl.input._
import name.brijest.storm.view.impl.commands._
import name.brijest.storm.view._


object Main {
  
  def printHelp {
    println("Arguments: -world=<world-name> -view=<view-name> -ctrl=<controller>")
    println("where:    world-name - the name of the world to run")
    println("          view-name  - the view type used to render to screen (for instance, swing-console)")
    println("          controller - the controller used to schedule events (for instance, basic)")
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
    val player = PlayerOwner(pid(1), gcid(2))
    val view = Multiplexor.view(argmap("view"), player)
    val creator = Multiplexor.worldCreator(argmap("world"))
    val world = creator.createWorld
    val ctrl = Multiplexor.controller(argmap("ctrl"), world, player)
    world.players.put(player.index, view.playerManager)
    ctrl.start
  }
  
}
















