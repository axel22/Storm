package name.brijest.storm.model.impl.terrains


import name.brijest.storm.model._


object DungeonFloor extends Terrain {
  val color = new Color(200, 200, 200)
  def representation = ('.', color)
  def name = "dungeon floor"
  def description = "Dusty dungeon floor."
  def walkable = true
  def manager = None
  def darkened = false
  def obscuring = false
  def cost = Constants.DEFAULT_MOVEMENT_COST
}

