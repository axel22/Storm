package name.brijest.storm.model



abstract class Terrain {
  def representation: (Char, Color)
  def walkable: Boolean
  def obscuring: Boolean
  def cost: Int
  def manager: Option[Manager]
  def darkened: Boolean
  def name: String
  def description: String
}