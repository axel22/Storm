package name.brijest.storm.model




trait Event {
  def name: String = getClass.getName
  def text: Option[String]
  def position: Option[(Int, Int)]
  def modelId: mid
  def hashCode: Int
  def equals(that: Any): Boolean
}






