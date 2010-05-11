package name.brijest.storm.view.impl.states.commands


import name.brijest.storm.view.KeyToken._
import name.brijest.storm.view._
import name.brijest.storm.view.impl.states.States


trait DialogActions extends Commands[States#MainGuiState] {
  
  val ViewInventoryCreator = new Creator {
    def bindings = Seq("C-i".t)
    def shortcut = "inv"
    def create(c: Context[States#MainGuiState]) = GuiChangeCommand { gs: GuiState =>
      (new gs.InventoryState): GuiState
    }
  }
  
}