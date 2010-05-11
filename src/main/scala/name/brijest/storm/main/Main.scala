package name.brijest.storm.main


import scala.collection._

import name.brijest.storm.model.impl.managers.PlayerManager


object Main {
  
  def printHelp {
    println("Arguments: -world=<world-name> -view=<view-name> -mod=<game-mod>")
    println("where:    world-name - the name of the world to run")
    println("          view-name  - the view type used to render to screen (for instance, swing-console)")
    println("          game-mod   - the mod used to schedule events and run the world (for instance, turn-based)")
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
    val creator = Multiplexor.worldCreator(argmap("world"), argmap)
    val world = creator.createWorld
    val player = Multiplexor.requestOwner(argmap("mod"), world, argmap)
    val ctrl = Multiplexor.controller(argmap("mod"), world, player, argmap)
    val view = Multiplexor.view(argmap("view"), argmap("mod"), player, argmap)
    Multiplexor.deploy(argmap("mod"), world, ctrl, view, argmap)
  }
  
}
















