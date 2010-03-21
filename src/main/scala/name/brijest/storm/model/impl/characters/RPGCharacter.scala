package name.brijest.storm.model.impl.characters


import name.brijest.storm.model._


trait RPGCharacterView
extends CharacterView
   with ActionPointsView
   with EnergyPointsView
   with InventoryView


abstract class RPGCharacter
extends Character
   with ActionPoints
   with EnergyPoints
   with Attributes
   with Inventory
   with RPGCharacterView
   with ElementLike[RPGCharacter, RPGCharacterView]
{
  override def isRPGCharacter = true
}


















