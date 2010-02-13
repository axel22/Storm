package name.brijest.storm.controller
package impl
package basic_controller


import name.brijest.storm.model._
import name.brijest.storm.view._

import scala.collection._



/**
 * Basic controller for a one-player turn-based game.
 */
class BasicController(val world: World, val owner: PlayerOwner, stop: () => Boolean)
extends Controller with TurnBasedScheduler {
  def stopCondition = stop()
  
  def start {
    startScheduling
  }
  
  def send(c: Command) = c match {
    case commands.PlayerCommand(playerId, action) =>
      world.players(playerId).push(action)
    case _ =>
  }
}

















