package name.brijest.storm.model
package impl
package characters



import managers._



abstract class PlayerCharacter(val playerId: pid) extends GameCharacter {
self =>
  def world: World
  def playerColor: Color
  def manager: Manager = new PlayerManager(playerId) with CharacterPerspective {
    def world = self.world
  }
  
  def representation = ('@', playerColor)
}













