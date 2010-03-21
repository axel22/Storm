package name.brijest.storm



import name.brijest.storm.model.impl.characters.RPGCharacter



package object model {
  
  def asRPGCharacter[U](optc: Option[Character])(f: RPGCharacter => U) = optc match {
    case Some(chr) if (chr.isRPGCharacter) => Some(f(chr.asInstanceOf[RPGCharacter]))
    case _ => None // do nothing if it's not an rpg character
  }
  
  implicit def toPos(p: (Int, Int)) = new Pos(p._1, p._2)
  
}









