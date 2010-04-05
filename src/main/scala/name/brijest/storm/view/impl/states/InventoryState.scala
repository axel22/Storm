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
  
  private var cachedList: Option[renderAdapter.Frame] = None
  
  def generateList(mv: ModelView, chrid: gcid) = {
    val stuff = getStuff(mv, chrid)
    val stufflisting = renderAdapter.Listing((for ((tp, items) <- stuff) yield {
      renderAdapter.Label(tp.name) +: (for (it <- items) yield {
        renderAdapter.Label("  " + it.name)
      })
    }).flatMap(n => n).toSeq)
    stufflisting.focusOn = selected
    stufflisting
  }
  
  def highlightList(stufflist: renderAdapter.Listing) = {
    val hl = stufflist.lst(selected)
    for (lb @ renderAdapter.Label(str, c, h) <- stufflist.lst) lb.highlighted = lb == hl
    stufflist
  }
  
  def enframeList(sl: renderAdapter.Listing) = renderAdapter.Frame(renderAdapter.Label("Inventory", true), sl)
  
  def getList(mv: ModelView, chrid: gcid) = {
    if (cachedList == None) cachedList = Some(enframeList(generateList(mv, chrid)))
    val renderAdapter.Frame(label, inner @ renderAdapter.Listing(lst)) = cachedList.get
    highlightList(inner)
    inner.focusOn = selected
    cachedList.get
  }
  
}


trait InventoryRenderer[State <: States#InventoryState] extends Renderer[State] {
  def render(ra: RenderAdapter, context: Context[State]) {
    val gs = context.guistate
    val stufflist = gs.getList(context.modelview, context.player.characterid.asgcid.get)
    ra.clear
    stufflist.display(0, 0, ra.screenDims._1, ra.screenDims._2)
  }
}
















