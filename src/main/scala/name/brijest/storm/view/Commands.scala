package name.brijest.storm.view


import name.brijest.storm.model._



/* command contexts */

trait Context {
  def modelview: ModelView
  def guistate: GuiState
  def player: PlayerOwner
}

case class BasicContext(modelview: ModelView, guistate: GuiState, player: PlayerOwner) extends Context




/* command creators */

trait CommandCreator {
  def bindings: Seq[List[Token]]
  def shortcut: String
  def create(c: Context): Command
}

trait NoBinding {
  def bindings: Seq[List[Token]] = Nil
  def shortcut = ""
}

class MessageCommandCreator(msg: String) extends CommandCreator with NoBinding {
  def create(c: Context) = new MessageCommand(msg)
}



/* commands */

trait Command

case class ActionCommand(action: CharacterAction) extends Command

abstract class GuiChangeCommand() extends Command {
  def modify(gui: GuiState): GuiState
}

abstract class GuiChangeActionCommand(val action: Action, val time: Long) extends GuiChangeCommand()

case class MessageCommand(msg: String) extends GuiChangeCommand {
  def modify(gui: GuiState): GuiState = {
    gui.renderAdapter.clearMessages
    gui.renderAdapter.writeMessage(msg)
    gui
  }
}
















