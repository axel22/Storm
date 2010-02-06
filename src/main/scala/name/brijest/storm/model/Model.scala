package name.brijest.storm.model


import name.brijest.storm.model.model._


trait ModelAdapter extends ModelView {
  def updateTerrain(pos: (Int, Int), t: Terrain): Boolean
  def addElement(pos: (Int, Int), e: Element): Boolean
  def removeElement(pos: (Int, Int), e: Element): Boolean
  def moveElement(pos: (Int, Int), e: Element, dest: (Int, Int)): Boolean
  def addCharacter(pos: (Int, Int), c: Character): Boolean
  def removeCharacter(pos: (Int, Int)): Boolean
  def moveCharacter(pos: (Int, Int), dest: (Int, Int)): Boolean
  def performDeedOnElement(pos: (Int, Int), e: Element, d: Deed): Boolean
  def performDeedOnCharacter(pos: (Int, Int), d: Deed): Boolean
  
  def elementsAt(pos: (Int, Int)): List[Element]
  def characterAt(pos: (Int, Int)): Option[Character]
  def character[Repr <: Character with View, View <: CharacterView](id: CharacterId[Repr, View]): Option[Repr]
  def characters: Iterator[CharacterView]
}

trait ModelView {
  def id: mid
  def size: (Int, Int)
  def terrainAt(pos: (Int, Int)): Terrain
  def elementsAt(pos: (Int, Int)): List[ElementView]
  def characterAt(pos: (Int, Int)): Option[CharacterView]
  def characterPos[Repr <: Character, View <: CharacterView](id: CharacterId[Repr, View]): Option[(Int, Int)]
  def character[Repr <: Character with View, View <: CharacterView](id: CharacterId[Repr, View]): Option[View]
  def characters: Iterator[CharacterView]
  def manager: Manager
  
  def extractManagers: Seq[Manager] = {
    var managers: List[Manager] = Nil
    managers ::= manager
    for (chr <- characters) {
      managers ::= chr.manager
    }
    for (pos <- (0, 0) to size) terrainAt(pos).manager match {
      case Some(mng) => managers ::= mng
      case None =>
    }
    managers
  }
  def hasElementsAt(pos: (Int, Int)) = elementsAt(pos) != Nil
  def hasElementAt(pos: (Int, Int), e: Element) = elementsAt(pos) match {
    case Nil => false
    case lst => lst.contains(e)
  }
  def hasCharacter[Repr <: Character with View, View <: CharacterView](id: CharacterId[Repr, View]) =
    character(id) != None
  def hasCharacterAt(pos: (Int, Int)) = characterAt(pos) match {
    case None => false
    case Some(chr) => true
  }
  def walkable(chr: CharacterView, pos: (Int, Int)) = chr.canWalkOn(terrainAt(pos)) && !hasCharacterAt(pos)
}

abstract class Model extends ModelView {
  def createModelAdapter: ModelAdapter
  def receiveAction(a: Action) = a(createModelAdapter)
}












