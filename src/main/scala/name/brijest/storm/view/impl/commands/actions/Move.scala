package name.brijest.storm.view.impl.commands.actions


import name.brijest.storm.model.impl.actions._
import name.brijest.storm.model._
import name.brijest.storm.view.KeyToken._
import name.brijest.storm.view._



trait MoveCreator extends CommandCreator {
  def create(dir: (Int, Int), cc: Context[GuiState]) = {
    // check if its possible to move
    // then either change gui state to return an error message, or move character
    val chrid = cc.player.characterid
    val Some(pos) = cc.modelview.characterPos(chrid)
    val Some(chr) = cc.modelview.character(chrid)
    if (cc.modelview.walkable(chr, pos + dir)) {
      val moveaction = new MoveCharacter(chrid, pos + dir)
      ActionCommand(moveaction)
    } else {
      new GuiChangeCommand {
        def modify(gui: GuiState) = {
          gui.clearMessages
          gui.writeMessage("You cannot move there.")
          gui
        }
      }
    }
  }
}

object MoveUpCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((0, -1), c)
  def bindings = Seq("u".t)
  def shortcut = "up"
}
object MoveRightUpCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((1, -1), c)
  def bindings = Seq("i".t)
  def shortcut = "upright"
}
object MoveLeftUpCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((-1, -1), c)
  def bindings = Seq("y".t)
  def shortcut = "upleft"
}
object MoveDownCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((0, 1), c)
  def bindings = Seq("m".t)
  def shortcut = "down"
}
object MoveRightDownCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((1, 1), c)
  def bindings = Seq(",".t)
  def shortcut = "downright"
}
object MoveLeftDownCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((-1, 1), c)
  def bindings = Seq("n".t)
  def shortcut = "downleft"
}
object MoveRightCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((1, 0), c)
  def bindings = Seq("k".t)
  def shortcut = "right"
}
object MoveLeftCreator extends MoveCreator {
  def create(c: Context[GuiState]) = create((-1, 0), c)
  def bindings = Seq("h".t)
  def shortcut = "left"
}












