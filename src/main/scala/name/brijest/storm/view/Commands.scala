package name.brijest.storm
package view


import name.brijest.storm.model._
import name.brijest.storm.model.impl.actions.CharacterAction






/* command matchers */

trait CommandMatcher {
  def matchinput(input: List[Token]): Option[CommandCreator]
  def register(cc: CommandCreator): Unit
}




/* command creators */

trait CommandCreator {
  def bindings: Seq[Seq[Token]]
  def shortcut: String
  def create(c: Context[GuiState]): ViewCommand
}

trait NoBinding {
  def bindings: Seq[List[Token]] = Nil
  def shortcut = ""
}

class MessageCommandCreator(msg: String) extends CommandCreator with NoBinding {
  def create(c: Context[GuiState]) = new MessageCommand(msg)
}



/* commands */

trait ViewCommand

case class ActionCommand(action: CharacterAction) extends ViewCommand

abstract class GuiChangeCommand() extends ViewCommand {
  def modify(gui: GuiState): GuiState
}

abstract class GuiChangeActionCommand(val action: Action, val time: Long) extends GuiChangeCommand()

case class MessageCommand(msg: String) extends GuiChangeCommand {
  def modify(gui: GuiState): GuiState = {
    gui.clearMessages
    gui.writeMessage(msg)
    gui
  }
}
















