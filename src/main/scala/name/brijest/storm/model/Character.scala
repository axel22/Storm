package name.brijest.storm.model


import name.brijest.storm.model.impl.actions.CharacterAction


trait CharacterAdapter extends ElementAdapter {
}


trait CharacterView extends ElementView with Agent {
  def representation: (Char, Color)
  def id: cid
  def typename: String
  def description: String
  def name: String
  def owner: Owner
  def view(modelview: ModelView): ModelView
  def canWalkOn(t: Terrain): Boolean = t.walkable
  def isOwned = owner != NoOwner
  def isRPGCharacter = false
}


trait CharacterLike[+Repr <: Character with View, +View <: CharacterView]
extends ElementLike[Repr, View]
   with CharacterView
   with CharacterAdapter


abstract class Character extends Element
                            with CharacterLike[Character, CharacterView]
{
  trait CharacterPerspective extends Manager {
    abstract override def timedAction(modelview: ModelView) = super.timedAction(view(modelview))
    abstract override def eventAction(e: Event, mv: ModelView) = super.eventAction(e, view(mv))
  }
  
  def onOwnAction(a: CharacterAction) {}
  
  override def view: CharacterView = this
  override def hashCode = id.hashCode
  override def equals(that: Any) = that match {
    case c: Character => id == c.id
    case _ => false
  }
}














