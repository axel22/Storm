package name.brijest.storm.model.impl.characters


import name.brijest.storm.model._


trait GameCharacterView
extends CharacterView
   with ActionPointsView
   with EnergyPointsView
   with InventoryView


abstract class GameCharacter
extends Character
   with ActionPoints
   with EnergyPoints
   with Attributes
   with Inventory
   with GameCharacterView
   with ElementLike[GameCharacter, GameCharacterView]
{
  override def isGameCharacter = true
}


















