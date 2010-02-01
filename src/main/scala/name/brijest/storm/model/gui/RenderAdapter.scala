package name.brijest.storm.model.gui


import name.brijest.storm.model._


trait RenderAdapter {
  def drawTerrainAt(pos: (Int, Int), t: Terrain): Unit
  def drawElementAt(pos: (Int, Int), e: ElementView): Unit
  def flush()
  def mapDimensions: ((Int, Int), (Int, Int))
  def writeMessage(message: String)
  def clearMessages()
  def displayListDialog()
}

trait DelegatingRenderAdapter {
  val delegate: RenderAdapter
  
  def drawTerrainAt(pos: (Int, Int), t: Terrain) = delegate.drawTerrainAt(pos, t)
  def drawElementAt(pos: (Int, Int), e: ElementView) = delegate.drawElementAt(pos, e)
  def flush() = delegate.flush
  def mapDimensions: ((Int, Int), (Int, Int)) = delegate.mapDimensions
  def writeMessage(message: String) = delegate.writeMessage(message)
  def clearMessages() = delegate.clearMessages
  def displayListDialog() = delegate.displayListDialog()
}








