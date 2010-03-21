package name.brijest.storm.view
package impl.commands.actions







trait InventoryActions extends Commands[States#InventoryState] {
  
  trait InventoryMovementCreator extends Creator {
    def create(c: Context[States#InventoryState], dir: Int) = GuiChangeCommand { gs: States#InventoryState =>
      gs // TODO
    }
  }
  
}















