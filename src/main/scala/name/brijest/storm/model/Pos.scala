package name.brijest.storm.model





class Pos(x: Int, y: Int) extends (Int, Int)(x, y) {
  def to(end: (Int, Int)) = new Iterator[(Int, Int)] {
    var currx = x;
    var curry = y;
    def hasNext = curry <= end._2 || (curry == end._2 && currx <= end._1)
    def next = {
      val res = (currx, curry)
      currx += 1
      if (currx > end._1) {
        currx = x
        curry += 1
      }
      res
    }
  }
  def +(p: (Int, Int)) = (x + p._1, y + p._2)
  def -(p: (Int, Int)) = (x - p._1, y - p._2)
}



