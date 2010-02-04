package name.brijest.storm.model



/**
 * Classifies whether or not the event is matched.
 */
trait EventMatcher {
  def matches(e: Event): Boolean
}


object AllEvents extends EventMatcher {
  def matches(e: Event) = true
}




