package name.brijest.storm.controller


import name.brijest.storm.model._



trait View extends (Event => Unit) {
  
  def awaitCommand: Command
  
  def eventMatcher: EventMatcher
  
}
