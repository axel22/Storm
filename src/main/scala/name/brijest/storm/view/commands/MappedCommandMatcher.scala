package name.brijest.storm.view.commands


import scala.collection._

import name.brijest.storm.view._


class MappedCommandMatcher extends ExtendableCommandMatcher {
  private val cmap = new mutable.HashMap[List[Token], CommandCreator]
  private var maxlength = 0
  
  def register(command: CommandCreator) = for (b <- command.bindings) {
    cmap.put(b, command)
    if (b.length > maxlength) maxlength = b.length
  }
  def matchinput(input: List[Token]): Option[CommandCreator] = cmap.get(input) match {
    case s @ Some(c) => s
    case None => if (input.length >= maxlength) Some(new MessageCommandCreator("No such command.")) else None
  }
}

















