package name.brijest.storm.controller


import name.brijest.storm.model._



trait View extends (Event => Unit) {
  
  def eventMatcher: EventMatcher
  
}
