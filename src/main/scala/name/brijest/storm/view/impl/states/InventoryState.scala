package name.brijest.storm.view
package impl.states


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.model.impl.characters.GameCharacter
import widget._


trait InventoryUtils {
  var offset = 0
  
  val renderAdapter: RenderAdapter
  
  def findStuff(modelview: ModelView, chrid: gcid) = modelview.character(chrid) match {
    case Some(gc) => gc.stuff.groupBy(_.itemtype).map(ntr => (ntr._1, ntr._2.toSeq))
    case None => Map[ItemType, Seq[Item]]()
  }
  
  def generateList(mv: ModelView, chrid: gcid) = {
    val stuff = findStuff(mv, chrid)
    val stufflisting = renderAdapter.Listing((for ((tp, items) <- stuff) yield {
      renderAdapter.Frame(renderAdapter.Label(tp.name, false),
          renderAdapter.Listing(for (it <- items) yield renderAdapter.Label(it.name, false)))
    }).toSeq)
    renderAdapter.Frame(renderAdapter.Label("Inventory", true), stufflisting)
  }
}


trait InventoryRenderer[State <: States#InventoryState] extends Renderer[State] {
  def render(ra: RenderAdapter, context: Context[State]) {
    val stufflist = context.guistate.generateList(context.modelview, context.player.characterid.asgcid.get)
    ra.clear
    stufflist.display(0, 0, ra.screenDims._1, ra.screenDims._2)
  }
}
















