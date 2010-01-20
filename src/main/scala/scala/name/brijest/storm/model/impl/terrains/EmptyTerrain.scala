package name.brijest.storm.model.impl.terrains


import name.brijest.storm.model._


object EmptyTerrain extends Terrain {
  def representation = (' ', Color.black)
  def walkable = false
  def name = "empty"
  def description = "Emptyness."
  def manager = None
  def darkened = true
  def obscuring = true
  def cost = Constants.DEFAULT_MOVEMENT_COST
}
