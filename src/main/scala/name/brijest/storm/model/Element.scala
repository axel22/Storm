package name.brijest.storm.model



trait ElementAdapter {
}

trait ElementView {
  def representation: (Char, Color)
}

trait Element extends ElementView with ElementAdapter {
  def createAdapter = this
  def receiveDeed(d: Deed) = d(createAdapter)
  def receiveDeed(opt: Option[Deed]) = opt match {
    case Some(d) => d(createAdapter)
    case None =>
  }
}








