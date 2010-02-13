package name.brijest.storm.main


import scala.collection._
import scala.actors.Actor._

import name.brijest.storm.model._
import name.brijest.storm.model.impl.managers.PlayerManager
import name.brijest.storm.controller._
import name.brijest.storm.view.impl.adapters._
import name.brijest.storm.view.impl.input._
import name.brijest.storm.view.impl.commands._
import name.brijest.storm.view._


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
    val player = PlayerOwner(pid(1), gcid(2))
    val creator = Multiplexor.worldCreator(argmap("world"))
    val world = creator.createWorld
    val ctrl = Multiplexor.controller(argmap("mod"), world, player)
    val view = Multiplexor.view(argmap("view"), argmap("mod"), player, ctrl)
    
    deploy(world, ctrl, view, player, argmap)
  }
  
  def deploy(w: World, ctrl: Controller, view: View, player: PlayerOwner, argmap: Map[String, String]) {
    w.players.put(player.index, new PlayerManager(pid(1)))
    
    actor {
      view.init
    }
    
    ctrl.start
  }
  
}
















