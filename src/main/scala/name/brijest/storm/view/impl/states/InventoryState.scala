package name.brijest.storm.view
package impl.states


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.model.impl.characters.RPGCharacter
import widget._


trait InventoryUtils {
  var selected = 0
  
  val renderAdapter: RenderAdapter
  
  def getStuff(modelview: ModelView, chrid: gcid) = modelview.character(chrid) match {
    case Some(gc) =>
      gc.stuff.groupBy(_.itemtype).map(ntr => (ntr._1, ntr._2.toSeq)).toSeq.sortBy(_._1.name)
    case None => Map[ItemType, Seq[Item]]().toSeq
  }
  
  def generateList(mv: ModelView, chrid: gcid) = {
    var pos = -1
    var focusedpos = 0
    var realpos = -1
    val stuff = getStuff(mv, chrid)
    val stufflisting = renderAdapter.Listing((for ((tp, items) <- stuff) yield {
      realpos += 1
      renderAdapter.Label(tp.name) +: (for (it <- items) yield {
        pos += 1
        realpos += 1
        if (pos == selected) focusedpos = realpos
        if (pos != selected) renderAdapter.Label("  " + it.name)
        else renderAdapter.Label("  " + it.name, highlighted = true)
      })
    }).flatMap(n => n).toSeq)
    stufflisting.focusOn = focusedpos
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
















