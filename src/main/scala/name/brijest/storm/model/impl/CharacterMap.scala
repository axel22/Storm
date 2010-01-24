package name.brijest.storm.model.impl


import name.brijest.storm.model._

import scala.collection._


class CharacterMap {
  private var idmap = new mutable.HashMap[Long, (Int, Int)]
  private var posmap = new mutable.HashMap[(Int, Int), Character]
  
  def at(pos: (Int, Int)) = posmap.get(pos)
  def withId(id: Long) = idmap.get(id) match  {
    case Some(pos) => posmap.get(pos)
    case None => None
  }
  def posWithId(id: Long) = idmap.get(id)
  def characters = posmap.values
  def contains(id: Long) = idmap.contains(id)
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












