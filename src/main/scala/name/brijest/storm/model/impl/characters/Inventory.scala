package name.brijest.storm.model
package impl.characters


import scala.collection._

import name.brijest.storm.model.Character
import name.brijest.storm.model.Element


trait InventoryView extends CharacterView {
  def stuff: Set[Element]
}

trait Inventory extends Character with InventoryView {
  val stuff: mutable.Set[Element] = mutable.Set[Element]()
}









