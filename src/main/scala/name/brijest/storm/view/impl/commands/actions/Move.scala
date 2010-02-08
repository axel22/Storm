package name.brijest.storm.view.impl.commands.actions


import name.brijest.storm.model.impl.actions._
import name.brijest.storm.model.model._
import name.brijest.storm.view.KeyToken._
import name.brijest.storm.view.view._
import name.brijest.storm.view._


/* Dummy for ant. */
class Move

trait MoveCreator extends CommandCreator {
  def create(dir: (Int, Int), cc: Context) = {
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
          gui.renderAdapter.clearMessages
          gui.renderAdapter.writeMessage("You cannot move there.")
          gui
        }
      }
    }
  }
}

object MoveUpCreator extends MoveCreator {
  def create(c: Context) = create((0, -1), c)
  def bindings = Seq("u")
  def shortcut = "up"
}
object MoveRightUpCreator extends MoveCreator {
  def create(c: Context) = create((1, -1), c)
  def bindings = Seq("i")
  def shortcut = "upright"
}
object MoveLeftUpCreator extends MoveCreator {
  def create(c: Context) = create((-1, -1), c)
  def bindings = Seq("y")
  def shortcut = "upleft"
}
object MoveDownCreator extends MoveCreator {
  def create(c: Context) = create((0, 1), c)
  def bindings = Seq("m")
  def shortcut = "down"
}
object MoveRightDownCreator extends MoveCreator {
  def create(c: Context) = create((1, 1), c)
  def bindings = Seq(",")
  def shortcut = "downright"
}
object MoveLeftDownCreator extends MoveCreator {
  def create(c: Context) = create((-1, 1), c)
  def bindings = Seq("n")
  def shortcut = "downleft"
}
object MoveRightCreator extends MoveCreator {
  def create(c: Context) = create((1, 0), c)
  def bindings = Seq("k")
  def shortcut = "right"
}
object MoveLeftCreator extends MoveCreator {
  def create(c: Context) = create((-1, 0), c)
  def bindings = Seq("h")
  def shortcut = "left"
}












