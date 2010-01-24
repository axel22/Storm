package name.brijest.storm.model




abstract class Action extends Function[ModelAdapter, Unit] {
  def name: String = getClass.getSimpleName
  def description: Option[String] = None
  def events: List[Event] = Nil
}

trait CharacterAction extends Action {
  def characterId: Long
  def timecost: Long
  def energycost: Long
  
  abstract override def apply(m: ModelAdapter) {
    m.character(characterId) match {
      case Some(chr) => chr.onOwnAction(this)
      case None =>
    }
    super.apply(m)
  }
}

object Action {
  implicit def toAction(fp: (String, Function[ModelAdapter, Unit])) = new Action {
    override def name = fp._1
    def apply(m: ModelAdapter) = fp._2(m)
  }
  implicit def toAction(fp: (String, String, Function[ModelAdapter, Unit])) = new Action {
    override def name = fp._1
    override def description = Some(fp._2)
    def apply(m: ModelAdapter) = fp._3(m)
  }
}













