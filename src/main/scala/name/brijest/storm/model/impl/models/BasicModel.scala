package name.brijest.storm.model.impl.models


import name.brijest.storm.model._
import name.brijest.storm.model.impl.CharacterMap
import name.brijest.storm.model.impl.terrains.EmptyTerrain

import scala.collection._


abstract class BasicModel(val size: (Int, Int)) extends Model with ModelAdapter {
  self =>
  
  private val terrain = new Array[Terrain](size._1 * size._2)
  val elementmap = new mutable.HashMap[(Int, Int), List[Element]]
  val charactermap = new CharacterMap

  def neighbours(modelrepo: ModelRepository): List[Model] = Nil
  
  def terrainAt(pos: (Int, Int)) = terrain(pos._1, pos._2)
  
  def elementsAt(pos: (Int, Int)) = elementmap.get(pos) match {
    case Some(lst) => lst
    case None => Nil
  }
  
  def characterAt(pos: (Int, Int)): Option[Character] = charactermap.at(pos).map(_.adapter)
  
  def characterPos[Repr <: Character, View <: CharacterView](id: CharacterId[Repr, View]): Option[(Int, Int)] =
    charactermap.posWithId(id)
  
  def characters = charactermap.characters
  
  def character[Repr <: Character with View, View <: CharacterView](id: CharacterId[Repr, View]) =
    charactermap.withId(id).map(_.adapter)
  
  def createModelAdapter = this
  
  def updateTerrain(pos: (Int, Int), t: Terrain) = if (within(pos)) {
    setTerrain(pos._1, pos._2, t)
    true
  } else false
  
  def addElement(pos: (Int, Int), e: Element) = if (within(pos)) elementmap.get(pos) match {
    case Some(lst) => elementmap(pos) = e :: lst; true
    case None => elementmap(pos) = List(e); true
  } else false
  
  def removeElement(pos: (Int, Int), e: Element) = elementmap.get(pos) match {
    case Some(lst) if (lst.contains(e)) => elementmap(pos) = lst.filter(_ != e); true
    case _ => false
  }
  def moveElement(pos: (Int, Int), e: Element, dest: (Int, Int)) = if (hasElementAt(pos, e) && within(dest)) {
    removeElement(pos, e)
    addElement(dest, e)
    true
  } else false
  
  def addCharacter(pos: (Int, Int), c: Character) = if (within(pos) && walkable(c, pos)) {
    charactermap.add(pos, c)
  } else false
  
  def removeCharacter(pos: (Int, Int)) = charactermap.remove(pos)
  
  def moveCharacter(pos: (Int, Int), dest: (Int, Int)) = {
    characterAt(pos) match {
      case Some(chr) =>
        if (within(dest) && walkable(chr, dest)) {
      	  charactermap.move(pos, dest)
        } else false
      case None => false
    }
  }
  
  def performDeedOnElement(pos: (Int, Int), e: Element, d: Deed) = elementsAt(pos) match {
    case lst if lst.contains(e) => e.receiveDeed(d); true
    case _ => false
  }
  
  def performDeedOnCharacter(pos: (Int, Int), d: Deed) = self.characterAt(pos) match {
    case Some(chr) => chr.receiveDeed(d); true
    case _ => false
  }
  
  for (x <- 0 until size._1; y <- 0 until size._2) setTerrain(x, y, EmptyTerrain)
  
  def within(x: Int, y: Int) = x >= 0 && x < size._1 && y >= 0 && y < size._2
  
  def within(pos: (Int, Int)): Boolean = within(pos._1, pos._2)
  
  def terrain(x: Int, y: Int): Terrain = {
    if (within(x, y)) terrain(y * size._1 + x)
    else EmptyTerrain
  }
  
  def setTerrain(x: Int, y: Int, t: Terrain) = terrain(y * size._1 + x) = t
  
}













