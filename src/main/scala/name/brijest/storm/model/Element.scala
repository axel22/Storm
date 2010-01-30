package name.brijest.storm.model



trait ElementAdapter {
}

trait ElementView {
  def representation: (Char, Color)
}

trait ElementLike[+Repr <: ElementLike[Repr, View] with ElementAdapter, +View <: ElementView]
extends ElementView with ElementAdapter {
  def adapter: Repr = this.asInstanceOf[Repr]
  def view: View = this.asInstanceOf[View]
  def receiveDeed(d: Deed) = d(adapter)
  def receiveDeed(opt: Option[Deed]) = opt match {
    case Some(d) => d(adapter)
    case None =>
  }
}

trait Element extends ElementLike[Element, ElementView]








