package name.brijest.storm.view.guis


import name.brijest.storm.model.gui.RenderAdapter
import name.brijest.storm.model._

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
  def drawAt(pos: (Int, Int), rep: (Char, Color)) = csi.print(pos._1, pos._2 + 2, rep._1, rep._2)
  def drawTerrainAt(pos: (Int, Int), t: Terrain) = drawAt(pos, t.representation)
  def drawElementAt(pos: (Int, Int), e: ElementView) = drawAt(pos, e.representation)
  def changeColorAt(pos: (Int, Int), col: Color) {
    val chr = csi.peekChar(pos._1, pos._2)
    csi.print(pos._1, pos._2, chr, col)
  }
  def mapDimensions = ((0, 2), (wwidth, wheight - 5))
  def clearMessages = {
    currentline = new firstline
    for (x <- 0 until wwidth; y <- 0 until 2) csi.print(x, y, " ")
  }
  def writeMessage(message: String) = {
    currentline = currentline.write(message + " ")
  }
}









