package name.brijest.storm.view.widget







trait Widgets {
  
  def drawLabel(label: Label, x: Int, y: Int, w: Int, h: Int): (Int, Int)
  def frameInsets: (Int, Int, Int, Int)
  def labelHeight: Int
  
  trait Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int)
    def height: Int
  }
  
  case class Listing(lst: Seq[Widget]) extends Widget {
    var offset = 0
    var focusOn = 0
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      def numFits(sq: Seq[Widget]) = {
        var sum = 0
        sq.prefixLength(w => {val b = sum < h; sum += w.height; b})
      }
      
      // check if focus is before
      if (focusOn < offset) offset = focusOn
      
      // check if focus is after
      var trimlst = lst.drop(offset)
      println(numFits(trimlst) + " vs. " + (focusOn - offset))
      while (numFits(trimlst) <= (focusOn - offset)) {
        trimlst = trimlst.tail
        offset += 1
      }
      
      var yp = y
      for (wd <- trimlst; if yp < y + h) {
        yp += wd.display(x, yp, w, h - yp)._2
      }
      (w, yp - y)
    }
    def height = lst.foldLeft(0)(_ + _.height)
  }
  
  case class Frame(title: Label, inner: Widget) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      var yp = y + title.display(x, y, w, h)._2
      yp += (if (yp < y + h) {
        val fi = frameInsets
        inner.display(x + fi._1, yp + fi._2, w - fi._1 - fi._3, h - (yp - y) - fi._2 - fi._4)._2
      } else 0)
      (w, yp - y)
    }
    def height = title.height + inner.height
  }
  
  class Label(val str: String, val centered: Boolean, val highlighted: Boolean) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = drawLabel(this, x, y, w, h)
    def height = labelHeight
  }
  
  object Label {
    def apply(str: String, centered: Boolean = false, highlighted: Boolean = false) = new Label(str, centered, highlighted)
  }
  
}































