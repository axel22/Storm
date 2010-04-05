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
    private var focuspos = 0
    private def numFits(sq: Seq[Widget], h: Int) = {
      var sum = 0
      sq.prefixLength(w => {val b = sum < h; sum += w.height; b})
    }
    def focusOn = focuspos
    def focusOn_=(p: Int) = focuspos = p
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      // check if focus is before
      if (focuspos < offset) offset = focuspos
      
      // check if focus is after
      var trimlst = lst.drop(offset)
      while (numFits(trimlst, h) <= (focuspos - offset)) {
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
  
  class Label(val str: String, val centered: Boolean, var highlighted: Boolean) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = drawLabel(this, x, y, w, h)
    def height = labelHeight
  }
  
  object Label {
    def apply(str: String, centered: Boolean = false, highlighted: Boolean = false) = new Label(str, centered, highlighted)
    def unapply(w: Label): Option[(String, Boolean, Boolean)] = if (w.isInstanceOf[Label]) {
      val lb = w.asInstanceOf[Label]
      Some(lb.str, lb.centered, lb.highlighted)
    } else None
  }
  
}































