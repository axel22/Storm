package name.brijest.storm.model


trait CharacterAdapter extends ElementAdapter {
}

trait CharacterView extends ElementView with Agent {
  def representation: (Char, Color)
  def id: Long
  def typename: String
  def description: String
  def name: String
  def owner: Owner
  def view(modelview: ModelView): ModelView
  def canWalkOn(t: Terrain): Boolean = t.walkable
  def isOwned = owner != NoOwner
  def isGameCharacter = false
}

abstract class Character extends CharacterView with Element with CharacterAdapter {
  trait CharacterPerspective extends Manager {
    abstract override def timedAction(modelview: ModelView) = super.timedAction(view(modelview))
    abstract override def eventAction(e: Event, mv: ModelView) = super.eventAction(e, view(mv))
  }
  
  def onOwnAction(a: CharacterAction) {}
  
  override def hashCode = id.toInt
  override def equals(that: Any) = that match {
    case c: Character => id == c.id
    case _ => false
  }
}














