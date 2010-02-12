package name.brijest.storm.model
package impl
package actions



import name.brijest.storm.model.model._
import characters.GameCharacter


trait CharacterAction extends Action {
  def characterId: cid
  def timecost: Long
  def energycost: Long
  
  override def apply(m: ModelAdapter): List[Event] = {
    m.character(characterId) match {
      case Some(chr) => chr.onOwnAction(this)
      case None =>
    }
    performAction(m)
  }
  
  def performAction(m: ModelAdapter): List[Event]
  
  override def turnsNeeded(modelview: ModelView): Long = turnsNeeded(modelview.character(characterId).get) 
  
  def turnsNeeded(chr: CharacterView): Long = chr match {
    case gc: GameCharacter => calculateNeededTurns(gc)
    case _ => 1L
  }
  
  private def calculateNeededTurns(gc: GameCharacter): Long = {
    val apneeded = -(gc.actionpoints - timecost)
    if (apneeded <= 0) 1
    else apneeded / gc.speed + 1
  }
}















