package name.brijest.storm.controller


import scala.collection._
import name.brijest.storm.model._


trait Observable {
  private val observers = new mutable.HashMap[EventMatcher, List[Event => Unit]]
  
  def registerEvent(em: EventMatcher) = observers.put(em, Nil)
  def addObserver(em: EventMatcher)(observer: Event => Unit) = {
    if (!observers.contains(em)) registerEvent(em)
    observers(em) = observer :: observers(em)
  }
  def removeObserver(em: EventMatcher, observer: Event => Unit) = if (observers.contains(em)) {
    observers(em) = observers(em).filter(_ != observer)
  }
  def onEvent(opt: Option[Event]): Unit = opt match {
    case Some(event) => onEvent(event)
    case None =>
  }
  def onEvents(lst: List[Event]) = lst.foreach(onEvent(_))
  def onEvent(e: Event) {
    for ((m, obss) <- observers) {
      if (m.matches(e)) obss.foreach(_(e))
    }
  }
}







