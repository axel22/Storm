package name.brijest.storm.model.impl


import name.brijest.storm.model._

import scala.collection._


class CharacterMap {
  private var idmap = new mutable.HashMap[CharacterId[_ <: Character, _ <: CharacterView], (Int, Int)]
  private var posmap = new mutable.HashMap[(Int, Int), CharacterLike[_ <: Character, _ <: CharacterView]]
  
  def at(pos: (Int, Int)) = posmap.get(pos).asInstanceOf[Option[CharacterLike[Character, CharacterView]]]
  def withId[Repr <: Character with View, View <: CharacterView](id: CharacterId[Repr, View]) = idmap.get(id) match {
    case Some(pos) => posmap.get(pos).asInstanceOf[Option[CharacterLike[Repr, View]]]
    case None => None
  }
  def posWithId[Repr <: Character, View <: CharacterView](id: CharacterId[Repr, View]) = idmap.get(id)
  def characters = posmap.valuesIterator
  def contains[Repr <: Character, View <: CharacterView](id: CharacterId[Repr, View]) = idmap.contains(id)
  def contains(pos: (Int, Int)) = posmap.contains(pos)
  def add(pos: (Int, Int), chr: Character) = {
    if (idmap.contains(chr.id) || posmap.contains(pos)) false
    else {
      idmap.put(chr.id, pos)
      posmap.put(pos, chr)
      true
    }
  }
  def remove(pos: (Int, Int)) = {
    if (!posmap.contains(pos)) false
    else {
      val chr = posmap(pos)
      posmap -= (pos)
      idmap -= (chr.id)
      true
    }
  }
  def move(pos: (Int, Int), dest: (Int, Int)) = {
    if (!posmap.contains(pos) || posmap.contains(dest)) false
    else {
      val chr = posmap(pos)
      posmap -= (pos)
      idmap -= (chr.id)
      idmap.put(chr.id, dest)
      posmap.put(dest, chr)
      true
    }
  }
}












