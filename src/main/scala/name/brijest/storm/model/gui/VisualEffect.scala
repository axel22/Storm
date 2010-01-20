package name.brijest.storm.model.gui



abstract class VisualEffect {
  def drawItself(ad: RenderAdapter, topleft: (Int, Int), size: (Int, Int)): Unit
  def next: Boolean
}







