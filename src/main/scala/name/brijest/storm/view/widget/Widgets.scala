package name.brijest.storm.view.widget





trait Widgets {
  
  def drawLabel(str: String, x: Int, y: Int, w: Int, h: Int): (Int, Int)
  
  
  trait Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int)
  }
  
  case class Listing(lst: List[Widget]) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = {
      var yp = y
      for (wd <- lst; if yp < y + h) {
        yp += wd.display(x, yp, w, h - yp)._2
      }
      (x, yp - y)
    }
  }
  
  case class Label(str: String) extends Widget {
    def display(x: Int, y: Int, w: Int, h: Int): (Int, Int) = drawLabel(str, x, y, w, h)
  }
  
}









