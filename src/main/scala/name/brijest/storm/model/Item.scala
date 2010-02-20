package name.brijest.storm.model



import name.brijest.storm.model._



trait ItemType {
  def name: String = getClass.getSimpleName
}


case object Food extends ItemType


abstract class Item extends Element {
  def name: String
  def itemtype: ItemType
}
