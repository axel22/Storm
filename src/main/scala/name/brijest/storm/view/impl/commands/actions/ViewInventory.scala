package name.brijest.storm.view
package impl.commands.actions




import name.brijest.storm.view.view._



class ViewInventoryCreator extends CommandCreator {
  def bindings = Seq("C-i")
  def shortcut = "inv"
  def create(c: Context[GuiState]) = null
}
