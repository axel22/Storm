package name.brijest.storm.model.impl.characters


import name.brijest.storm.model._


abstract class GameCharacter
extends Character
with ActionPoints
with EnergyPoints
with Inventory
{
  override def isGameCharacter = true
}

object SomeGameCharacter {
  def unapply(chr: CharacterView): Option[GameCharacter] = {
    if (chr.isInstanceOf[GameCharacter]) Some(chr.asInstanceOf[GameCharacter]) else None
  }
}















