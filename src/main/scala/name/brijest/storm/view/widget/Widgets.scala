package name.brijest.storm.view.widget





trait Widgets {
  
  def drawLabel(str: String, x: Int, y: Int, w: Int, h: Int): (Int, Int)
  def frameInsets: (Int, Int, Int, Int)
  
  trait Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int)
  }
  
  case class Listing(lst: List[Widget]) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      var yp = y
      for (wd <- lst; if yp < y + h) {
        yp += wd.display(x, yp, w, h - yp)._2
      }
      (w, yp - y)
    }
  }
  
  case class Frame(str: String, inner: Widget) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      var yp = y + drawLabel(str, x, y, w, h)._2
      yp += (if (yp < y + h) {
        val fi = frameInsets
        inner.display(x + fi._1, yp + fi._2, w - fi._1 - fi._3, h - (yp - y) - fi._2 - fi._4)._2
      } else 0)
      (w, yp - y)
    }
  }
  
  case class Label(str: String) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = drawLabel(str, x, y, w, h)
  }
  
}









