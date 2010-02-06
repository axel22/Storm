package name.brijest.storm.view.impl.input


import name.brijest.storm.model._
import name.brijest.storm.model.impl.actions._
import name.brijest.storm.view._

import javax.swing.JPanel

import net.slashie.libjcsi._
import net.slashie.libjcsi.wswing._


class SwingKeyboardInputAdapter(val wcsi: WSwingConsoleInterface)
extends InputAdapter {
  def manageInput(matcher: CommandMatcher): CommandCreator = {
    val cc = recManageInput(matcher, Nil)
    cc
  }
  
  private def recManageInput(matcher: CommandMatcher, sofar: List[Token]): CommandCreator = {
    var entered = sofar
    readInput match {
      case t @ Token(c, flags) if t.isEscape =>
        println(t + ", " + t.isEscape)
        return new MessageCommandCreator("Command cancelled.")
      case inp @ _ =>
        entered ::= inp
    }
    println("Entered: " + entered)
    matcher.matchinput(entered.reverse) match {
      case Some(cc) => cc
      case None => recManageInput(matcher, entered)
    }
  }
  
  private def readInput: Token = {
    val ck = wcsi.inkey
    if (!ck.isNone) Token(ck)
    else readInput
  }
}





















