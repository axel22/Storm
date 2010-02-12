package name.brijest.storm.controller


import name.brijest.storm.model._
import name.brijest.storm.view._


trait Controller extends Observable {
  def world: WorldView
  def start
  def send(c: Command)
}











