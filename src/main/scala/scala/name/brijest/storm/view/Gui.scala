package name.brijest.storm.view


import name.brijest.storm.model._
import name.brijest.storm.model.gui._


trait Gui extends DelegatingRenderAdapter {
  val delegate = renderAdapter
  def renderAdapter: RenderAdapter
  def inputAdapter: InputAdapter
}















