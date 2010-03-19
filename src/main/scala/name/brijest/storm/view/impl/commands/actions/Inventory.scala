package name.brijest.storm.view
package impl.commands.actions







trait InventoryActions extends Commands[States#InventoryState] {
  
  val InventoryMovementCreator = new Creator {
    def bindings = Seq("n".t)
    def shortcut = "n"
    def create(c: Context[States#InventoryState]) = GuiChangeCommand { gs: States#InventoryState =>
      gs // TODO
    }
  }
  
}















