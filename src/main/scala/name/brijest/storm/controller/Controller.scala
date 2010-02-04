package name.brijest.storm.controller


import name.brijest.storm.model._
import name.brijest.storm.view._


trait Controller extends Observable with Scheduler {
  def world: World
  def view: View
  def start
  
  registerEvent(view.eventMatcher)
  addObserver(view.eventMatcher, view)
}











