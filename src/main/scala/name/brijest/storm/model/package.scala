package name.brijest.storm.model



import name.brijest.storm.model.impl.characters.GameCharacter



package object model {
  
  def asGameCharacter(optc: Option[Character])(f: GameCharacter => Unit) = optc match {
    case Some(chr) if (chr.isGameCharacter) => f(chr.asInstanceOf[GameCharacter])
    case _ =>
  }
  
  implicit def toPos(p: (Int, Int)) = new Pos(p._1, p._2)
  
}









