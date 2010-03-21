package name.brijest.storm.model


import name.brijest.storm.model.impl.characters.RPGCharacter


abstract class Action extends Function[ModelAdapter, List[Event]] {
  def name: String = getClass.getSimpleName
  def description: Option[String] = None
  
  def turnsNeeded(modelview: ModelView): Long = 1L
}


object Action {
  implicit def toAction(fp: (String, Function[ModelAdapter, List[Event]])) = new Action {
    override def name = fp._1
    def apply(m: ModelAdapter) = fp._2(m)
  }
  implicit def toAction(fp: (String, String, Function[ModelAdapter, List[Event]])) = new Action {
    override def name = fp._1
    override def description = Some(fp._2)
    def apply(m: ModelAdapter) = fp._3(m)
  }
}













