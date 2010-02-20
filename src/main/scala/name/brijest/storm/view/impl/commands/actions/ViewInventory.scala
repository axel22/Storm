package name.brijest.storm.view
package impl.commands.actions







object ViewInventoryCreator extends CommandCreator {
  def bindings = Seq("C-i".t)
  def shortcut = "inv"
  def create(c: Context[GuiState]) = new GuiChangeCommand {
    def modify(gs: GuiState) = (new gs.InventoryState): GuiState
  }
}










