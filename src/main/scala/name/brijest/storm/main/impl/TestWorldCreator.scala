package name.brijest.storm.main.impl


import name.brijest.storm.model._
import name.brijest.storm.view._
import name.brijest.storm.main._
import name.brijest.storm.model.impl.models._
import name.brijest.storm.model.impl.managers.PlayerManager
import name.brijest.storm.worlds.BasicWorld
import name.brijest.storm.worlds.models.TestModel


class TestWorldCreator extends WorldCreator {
  def createWorld = {
    val world = new BasicWorld()
    world.addModel(new TestModel(mid(1), (40, 20), world))
    world.players.put(pid(1), new PlayerManager(new PlayerOwner(pid(1), gcid(2))))
    world
  }
}





