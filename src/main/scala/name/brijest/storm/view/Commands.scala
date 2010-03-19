package name.brijest.storm
package view


import scala.collection._

import name.brijest.storm.model._
import name.brijest.storm.model.impl.actions.CharacterAction








trait Commands[St <: GuiState] {
  
  trait Matcher {
    def matchinput(input: List[Token]): Option[Creator]
    def messageCommandCreator(msg: String) = new MessageCommandCreator(msg)
  }
  
  object matcher extends Matcher {
    private val cmap = new mutable.HashMap[Seq[Token], Creator]
    private var maxlength = 0
    
    def register(command: Creator) = for (b <- command.bindings) {
      cmap.put(b, command)
      if (b.length > maxlength) maxlength = b.length
    }
    
    def matchinput(input: List[Token]): Option[Creator] = cmap.get(input) match {
      case s @ Some(c) => s
      case None => if (input.length >= maxlength) Some(new MessageCommandCreator("No such command.")) else None
    }
  }
  
  trait Creator {
    matcher.register(this)
    def bindings: Seq[Seq[Token]]
    def shortcut: String
    def create(c: Context[St]): ViewCommand
  }
  
  class MessageCommandCreator(msg: String) extends Creator with NoBinding {
    def create(c: Context[St]) = GuiChangeCommand { (gui: St) =>
      gui.clearMessages
      gui.writeMessage(msg)
      gui
    }
  }
  
  /* commands */
  
  trait ViewCommand
  
  case class ActionCommand(action: CharacterAction) extends ViewCommand
  
  case class GuiChangeCommand(modifier: St => GuiState) extends ViewCommand {
    def modify(gui: St): GuiState = modifier(gui)
  }
  
  case class GuiChangeActionCommand(action: CharacterAction, modifier: St => GuiState) extends ViewCommand()
  
}



trait NoBinding {
  def bindings: Seq[List[Token]] = Nil
  def shortcut = ""
}























