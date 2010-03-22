package name.brijest.storm.model


import net.slashie.libjcsi.CSIColor


case class Color(val r: Int, val g: Int, val b: Int) extends CSIColor(r, g, b)


object Color {
  val black = new Color(0, 0, 0)
  val blue = new Color(0, 0, 255)
  val white = new Color(255, 255, 255)
  val indigo = new Color(120, 170, 255)
}