package name.brijest.storm
package view


import scala.annotation.unchecked.uncheckedVariance

import name.brijest.storm.model._
import name.brijest.storm.model.impl.actions.CharacterAction







trait Commands[St <: GuiState] {
  
  def matcher: Matcher
  
  trait Matcher {
    def matchinput(input: List[Token]): Option[Creator]
    def register(cc: Creator): Unit
    def messageCommandCreator(msg: String) = new MessageCommandCreator(msg)
  }
  
  trait Creator {
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























