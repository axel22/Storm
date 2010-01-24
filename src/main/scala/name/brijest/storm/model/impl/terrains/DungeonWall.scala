package name.brijest.storm.model.impl.terrains


import name.brijest.storm.model._


object DungeonWall extends Terrain {
  val color = new Color(140, 140, 140)
  def representation = ('#', color)
  def name = "dungeon wall"
  def description = "A stone dungeon wall."
  def walkable = false
  def manager = None
  def darkened = false
  def obscuring = true
  def cost = Constants.DEFAULT_MOVEMENT_COST
}

