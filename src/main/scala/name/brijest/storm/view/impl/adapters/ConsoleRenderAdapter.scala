package name.brijest.storm.view
package impl.adapters


import name.brijest.storm.view._
import name.brijest.storm.model._
import name.brijest.storm.view.widget._

import net.slashie.libjcsi._


class ConsoleRenderAdapter(val csi: ConsoleSystemInterface) extends RenderAdapter {
  var currentline: line = new firstline
  
  trait line {
    var pos = 0
    def spaceleft = wwidth - pos
    def writePart(message: String): String
    def nextline: line
    def write(message: String): line = {
      val left = writePart(message)
      if (left.length == 0) this
      else nextline.write(message)
    }
  }
  class firstline extends line {
    def writePart(msg: String) = {
      val toWrite = if (msg.length < spaceleft) msg else msg.take(spaceleft).toString
      csi.print(pos, 0, toWrite)
      pos += toWrite.length
      msg.drop(toWrite.length)
    }
    def nextline = new secondline
  }
  class secondline extends line {
    def writePart(msg: String) = {
      if (msg.length < spaceleft) {
        csi.print(pos, 1, msg)
        pos += msg.length
        ""
      } else {
        val toWrite = msg.take(spaceleft - 6).toString
        csi.print(pos, 1, toWrite + "<MORE>")
        csi.refresh
        while (csi.inkey.code != CharKey.SPACE) {}
        msg.drop(spaceleft - 6)
      }
    }
    def nextline = new firstline
  }
  
  def wwidth = 79
  
  def wheight = 25
  
  def flush = csi.refresh
  
  def clear = csi.cls
  
  def drawAt(pos: (Int, Int), rep: (Char, Color)) = csi.print(pos._1, pos._2 + 2, rep._1, rep._2)
  
  def drawTerrainAt(pos: (Int, Int), t: Terrain) = drawAt(pos, t.representation)
  
  def drawElementAt(pos: (Int, Int), e: ElementView) = drawAt(pos, e.representation)
  
  def changeColorAt(pos: (Int, Int), col: Color) {
    val chr = csi.peekChar(pos._1, pos._2)
    csi.print(pos._1, pos._2, chr, col)
  }
  
  def mapDims = ((0, 2), (wwidth, wheight - 5))
  
  def screenDims = (wwidth, wheight)
  
  def clearMessages = {
    currentline = new firstline
    for (x <- 0 until wwidth; y <- 0 until 2) csi.print(x, y, " ")
  }
  
  def writeMessage(message: String) = {
    currentline = currentline.write(message + " ")
  }
  
  def displayDialog(w: Widget) = w.display(0, 2, wwidth, wheight - 2)  
  
  def drawLabel(label: Label, x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
    val str = label.str
    if (str.length >= w || !label.centered) {
      if (label.highlighted) csi.print(x, y, str.take(w - x), Color.indigo)
      else csi.print(x, y, str.take(w - x))
      (str.length max (w - x), 1)
    } else {
      csi.print(x + (w - str.length) / 2, y, str)
      for (xp <- 0 until (x + (w - str.length) / 2 - 1)) csi.print(xp, y, "-")
      for (xp <- (x + (w + str.length) / 2 + 1) to (x + w)) csi.print(xp, y, "-")
      (w, 1)
    }
  }
  
  val frameInsets = (2, 0, 0, 0)
  
  val labelHeight = 1
  
  def displayMap(view: ModelView, location: (Int, Int)) {
    val dims = mapDims
    for (pos <- location to (location + dims._2)) {
      val targetpos = pos - location
      (view characterAt pos) match {
        case None => (view elementsAt pos) match {
          case Nil => drawTerrainAt(targetpos, (view terrainAt pos))
          case elem :: tail => drawElementAt(targetpos, elem)
        }
        case Some(chr) => drawElementAt(targetpos, chr)
      }
    }
  }
  
}





















