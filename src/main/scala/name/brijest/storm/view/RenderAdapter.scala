package name.brijest.storm.view


import name.brijest.storm.model._


trait RenderAdapter extends widget.Widgets {
  def flush()
  def clear()
  def mapDims: ((Int, Int), (Int, Int))
  def screenDims: (Int, Int)
  def writeMessage(message: String)
  def clearMessages()
  def displayMap(modelview: ModelView, location: (Int, Int))
  def displayDialog(w: Widget)
}












