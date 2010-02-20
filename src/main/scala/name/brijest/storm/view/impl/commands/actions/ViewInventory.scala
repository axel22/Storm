package name.brijest.storm.view
package impl.commands.actions







class ViewInventoryCreator extends CommandCreator {
  def bindings = Seq("C-i".t)
  def shortcut = "inv"
  def create(c: Context[GuiState]) = null
}
