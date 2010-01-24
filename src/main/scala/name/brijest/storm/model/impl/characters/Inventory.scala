package name.brijest.storm.model.impl.characters



import name.brijest.storm.model.Character
import name.brijest.storm.model.Element



trait Inventory extends Character {
  val stuff = collection.mutable.Set[Element]()
}









