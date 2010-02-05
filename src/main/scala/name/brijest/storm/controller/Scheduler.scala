package name.brijest.storm.controller


import name.brijest.storm.model.World


trait Scheduler {
  def world: World
  
  def startScheduling
  def stopCondition: Boolean
}
