package name.brijest.storm.worlds.models


import name.brijest.storm.model.impl.characters._
import name.brijest.storm.model.impl.managers._
import name.brijest.storm.model.impl.models._
import name.brijest.storm.model._


class SimpleModel(val id: mid, sz: (Int, Int), world: World) extends BasicModel(sz) {
  private def init = {
    // setup terrain
    val ad = createModelAdapter
    for (x <- 0 until sz._1) {
      ad.updateTerrain((x, 0), impl.terrains.DungeonWall)
      ad.updateTerrain((x, sz._2 - 1), impl.terrains.DungeonWall)
    }
    
    for (y <- 1 until sz._2 - 1) {
      ad.updateTerrain((0, y), impl.terrains.DungeonWall)
      ad.updateTerrain((sz._1 - 1, y), impl.terrains.DungeonWall)
    }
    
    for (x <- 1 until sz._1 - 1; y <- 1 until sz._2 - 1) {
      ad.updateTerrain((x, y), impl.terrains.DungeonFloor)
    }
    
    // add character
    val chr = new world.PlayerCharacter(pid(1)) {
      def world = SimpleModel.this.world
      def speed = 100
      def playerColor = Color.blue
      def owner = PlayerOwner(pid(1), gcid(2))
      def name = "Mihovil"
      def typename = "Human"
      def description = ""
      def view(mv: ModelView) = mv
      def id = gcid(2)
      def attributes = new {
        def strength = 1
      }
    }
    ad.addCharacter((5, 5), chr)
  }
  init
  
  def manager = impl.managers.IdleManager
}














