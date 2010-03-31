package name.brijest.storm.view
package impl.commands.actions







trait InventoryActions extends Commands[States#InventoryState] {
  
  trait InventoryMovementCreator extends Creator {
    def create(c: Context[States#InventoryState], dir: Int) = GuiChangeCommand { gs: States#InventoryState =>
      val stuff = gs.getStuff(c.modelview, c.player.characterid.asgcid.get)
      val nval = gs.selected + dir
      if (nval >= 0 && nval < stuff.flatMap(_._2).size) gs.selected = nval
      gs
    }
  }
  
  val PreviousItemCreator = new InventoryMovementCreator {
    def create(c: Context[States#InventoryState]) = create(c, -1)
    def bindings = Seq("u".t)
    def shortcut = "prev"
  }
  
  val NextItemCreator = new InventoryMovementCreator {
    def create(c: Context[States#InventoryState]) = create(c, 1)
    def bindings = Seq("m".t)
    def shortcut = "next"
  }
  
}















