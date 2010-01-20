package name.brijest.storm.model.impl.characters



import name.brijest.storm.model._



abstract class PlayerCharacter extends GameCharacter {
  def playerColor: Color
  def manager: Manager = new impl.managers.DelegatingManager(impl.managers.PlayerManager) with CharacterPerspective
  
  def representation = ('@', playerColor)
}













