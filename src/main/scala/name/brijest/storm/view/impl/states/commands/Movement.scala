package name.brijest.storm.view.impl.states
package commands


import name.brijest.storm.model.impl.actions._
import name.brijest.storm.model._
import name.brijest.storm.view.KeyToken._
import name.brijest.storm.view._


trait MovementActions extends Commands[States#MainGuiState] {
  
  trait MoveCreator extends Creator {
    def create(dir: (Int, Int), cc: Context[States#MainGuiState]) = {
      // check if its possible to move
      // then either change gui state to return an error message, or move character
      val chrid = cc.player.characterid.asgcid.get
      val Some(pos) = cc.modelview.characterPos(chrid)
      val Some(chr) = cc.modelview.character(chrid)
      if (cc.modelview.walkable(chr, pos + dir)) {
        val moveaction = new MoveCharacter(chrid, pos + dir)
        ActionCommand(moveaction)
      } else {
        GuiChangeCommand { gui: GuiState =>
          gui.clearMessages
          gui.writeMessage("You cannot move there.")
          gui
        }
      }
    }
  }
  
  val MoveUpCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((0, -1), c)
    def bindings = Seq("u".t)
    def shortcut = "up"
  }
  
  val MoveRightUpCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((1, -1), c)
    def bindings = Seq("i".t)
    def shortcut = "upright"
  }
  
  val MoveLeftUpCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((-1, -1), c)
    def bindings = Seq("y".t)
    def shortcut = "upleft"
  }
  
  val MoveDownCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((0, 1), c)
    def bindings = Seq("m".t)
    def shortcut = "down"
  }
  
  val MoveRightDownCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((1, 1), c)
    def bindings = Seq(",".t)
    def shortcut = "downright"
  }
  
  val MoveLeftDownCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((-1, 1), c)
    def bindings = Seq("n".t)
    def shortcut = "downleft"
  }
  
  val MoveRightCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((1, 0), c)
    def bindings = Seq("k".t)
    def shortcut = "right"
  }
  
  val MoveLeftCreator = new MoveCreator {
    def create(c: Context[States#MainGuiState]) = create((-1, 0), c)
    def bindings = Seq("h".t)
    def shortcut = "left"
  }

}












