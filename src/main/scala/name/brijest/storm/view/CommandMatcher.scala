package name.brijest.storm.view


import name.brijest.storm.view.commands.actions._


trait CommandMatcher {
  def matchinput(input: List[Token]): Option[CommandCreator]
}

trait ExtendableCommandMatcher extends CommandMatcher {
  def register(command: CommandCreator): Unit
}


trait MainCommandRepository extends ExtendableCommandMatcher {
  // all main view command creators are registered here
  
  // movement
  register(MoveUpCreator)
  register(MoveLeftUpCreator)
  register(MoveRightUpCreator)
  register(MoveDownCreator)
  register(MoveLeftDownCreator)
  register(MoveRightDownCreator)
  register(MoveLeftCreator)
  register(MoveRightCreator)
}










