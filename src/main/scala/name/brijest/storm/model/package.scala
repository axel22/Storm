package name.brijest.storm



import name.brijest.storm.model.impl.characters.GameCharacter



package object model {
  
  def asGameCharacter[U](optc: Option[Character])(f: GameCharacter => U) = optc match {
    case Some(chr) if (chr.isGameCharacter) => Some(f(chr.asInstanceOf[GameCharacter]))
    case _ => None // do nothing if it's not a game character
  }
  
  implicit def toPos(p: (Int, Int)) = new Pos(p._1, p._2)
  
}









