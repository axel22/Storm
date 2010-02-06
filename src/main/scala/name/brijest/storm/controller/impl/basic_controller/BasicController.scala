package name.brijest.storm.controller
package impl
package basic_controller


import name.brijest.storm.model._
import name.brijest.storm.view._

import scala.collection._



/**
 * Basic controller for a one-player turn-based game.
 */
abstract class BasicController
extends Controller with TurnBasedScheduler {
  def start {
    startScheduling
  }
}

















