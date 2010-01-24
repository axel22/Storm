package name.brijest.storm.main.impl


import name.brijest.storm.model._
import name.brijest.storm.view._
import name.brijest.storm.main._
import name.brijest.storm.model.impl.models._
import name.brijest.storm.worlds.BasicWorld
import name.brijest.storm.view.input.SwingKeyboardInputAdapter
import name.brijest.storm.worlds.models.SimpleModel


class SimpleWorldCreator extends WorldCreator {
  def createWorld = {
    val world = new BasicWorld()
    world.addModel(new SimpleModel(1, (40, 20)))
    world
  }
}





