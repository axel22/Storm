package name.brijest.storm.model


import net.slashie.libjcsi.CSIColor


case class Color(val r: Int, val g: Int, val b: Int) extends CSIColor(r, g, b)


object Color {
  val black = new Color(0, 0, 0)
  val blue = new Color(0, 0, 255)
}