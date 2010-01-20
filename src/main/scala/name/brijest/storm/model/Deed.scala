package name.brijest.storm.model






abstract class Deed extends Function[ElementAdapter, Unit] {
  def name: String
  def description: Option[String]
  def source: Option[Element]
}


object Deed {
  implicit def toDeed(dp: (String, Function[ElementAdapter, Unit])) = new Deed {
    def name = dp._1
    def description = None
    def apply(ad: ElementAdapter) = dp._2(ad)
    def source = None
  }
  implicit def toDeed(dp: (String, String, Function[ElementAdapter, Unit])) = new Deed {
    def name = dp._1
    def description = Some(dp._2)
    def apply(ad: ElementAdapter) = dp._3(ad)
    def source = None
  }
}








