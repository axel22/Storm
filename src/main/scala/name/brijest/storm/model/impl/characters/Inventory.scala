package name.brijest.storm.model
package impl.characters


import scala.collection._

import name.brijest.storm.model.Character
import name.brijest.storm.model.impl.elements.Item


trait InventoryView extends CharacterView {
  def stuff: Set[Item]
}

trait Inventory extends Character with InventoryView {
  val stuff: mutable.Set[Item] = mutable.Set[Item]()
}









