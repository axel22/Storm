package name.brijest.storm.view
package impl.states


import name.brijest.storm.model.impl.characters.GameCharacter


trait InventoryRenderer[State <: States#InventoryState] extends Renderer[State] {
  def render(renderAdapter: RenderAdapter, context: Context[State]) {
    val chr = context.modelview.character(context.player.characterid) match {
      case Some(c) =>
        if (c.isGameCharacter) displayStuff(renderAdapter, c.asInstanceOf[GameCharacter])
        else renderAdapter.writeMessage("The character has no inventory.")
      case None => renderAdapter.writeMessage("Cannot find player character.")
    }
  }
  
  def displayStuff(ra: RenderAdapter, gc: GameCharacter) {
    val stuffgroups = gc.stuff.groupBy(_.itemtype)
    for ((itemtype, item) <- stuffgroups) {
      
    }
  }
}
















