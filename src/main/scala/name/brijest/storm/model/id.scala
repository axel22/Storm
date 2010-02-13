package name.brijest.storm.model


import name.brijest.storm.model.impl.characters.GameCharacter
import name.brijest.storm.model.impl.characters.GameCharacterView



trait Id[+Repr, +View] {
  val num: Long
  
  override def hashCode = num.hashCode
  override def equals(that: Any) = that match {
    case that: Id[_, _] => that.getClass == this.getClass && that.num == this.num
    case _ => false
  }
  override def toString = getClass.getSimpleName + "[" + num + "]"
}


class pid(val num: Long) extends Id[Unit, Unit]


object pid {
  def apply(n: Long) = new pid(n)
}


trait ModelId[+Repr <: Model, +View <: ModelView] extends Id[Repr, View]


class mid(val num: Long) extends ModelId[Model, ModelView]


object mid {
  def apply(n: Long) = new mid(n)
}


trait CharacterId[+Repr <: Character, +View <: CharacterView] extends Id[Repr, View]


class cid(val num: Long) extends CharacterId[Character, CharacterView]


object cid {
  def apply(n: Long) = new cid(n)
}


class gcid(n: Long) extends cid(n) with CharacterId[GameCharacter, GameCharacterView]


object gcid {
  def apply(n: Long) = new gcid(n)
}
















