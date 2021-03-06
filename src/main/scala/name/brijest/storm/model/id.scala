package name.brijest.storm.model


import name.brijest.storm.model.impl.characters.RPGCharacter
import name.brijest.storm.model.impl.characters.RPGCharacterView



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


class cid(val num: Long) extends CharacterId[Character, CharacterView] {
  def asgcid: Option[gcid] = this match {
    case gcid(n) => Some(gcid(n))
    case _ => None
  }
}


object cid {
  def apply(n: Long) = new cid(n)
  def unapply(any: Any) = if (any.isInstanceOf[cid]) Some(any.asInstanceOf[cid].num) else None
}


class gcid(n: Long) extends cid(n) with CharacterId[RPGCharacter, RPGCharacterView]


object gcid {
  def apply(n: Long) = new gcid(n)
  def unapply(any: Any) = if (any.isInstanceOf[gcid]) Some(any.asInstanceOf[gcid].num) else None
}
















