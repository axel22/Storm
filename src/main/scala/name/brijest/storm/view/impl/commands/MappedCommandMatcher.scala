package name.brijest.storm.view.impl.commands


import scala.collection._

import name.brijest.storm.view._


//class MappedCommandMatcher[St <: GuiState] extends Commands[St]#Matcher {
//  private val cmap = new mutable.HashMap[Seq[Token], Commands[St]#Creator]
//  private var maxlength = 0
//  
//  def register(command: Commands[St]#Creator) = for (b <- command.bindings) {
//    cmap.put(b, command)
//    if (b.length > maxlength) maxlength = b.length
//  }
//  
//  def matchinput(input: List[Token]): Option[Commands[St]#Creator] = cmap.get(input) match {
//    case s @ Some(c) => s
//    case None => if (input.length >= maxlength) Some(new MessageCommandCreator[St]("No such command.")) else None
//  }
//  
//}


















