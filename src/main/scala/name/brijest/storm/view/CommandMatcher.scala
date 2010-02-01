package name.brijest.storm.view


import name.brijest.storm.view.commands.actions._


trait CommandMatcher {
  def matchinput(input: List[Token]): Option[CommandCreator]
}

trait ExtendableCommandMatcher extends CommandMatcher {
  def register(command: CommandCreator): Unit
}










