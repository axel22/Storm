package name.brijest.storm.controller
package impl
package commands


import name.brijest.storm.model._


case class PlayerCommand(playerId: pid, a: Action) extends Command
