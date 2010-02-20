package name.brijest.storm.view
package impl.states


import name.brijest.storm.model._
import name.brijest.storm.model.impl.characters.GameCharacter



trait InventoryUtils {
  var offset = 0
  
  def renderAdapter: RenderAdapter
  
  def findStuff(modelview: ModelView, chrid: gcid) = modelview.character(chrid) match {
    case Some(gc) => gc.stuff.groupBy(_.itemtype)
    case None => collection.Map[ItemType, Item]()
  }
  
  def screenWidgets(mv: ModelView, chrid: gcid) = {
    val stuff = findStuff(mv, chrid)
    
  }
}


trait InventoryRenderer[State <: States#InventoryState] extends Renderer[State] {
  def render(ra: RenderAdapter, context: Context[State]) {
    
  }
}
















