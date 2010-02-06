package name.brijest.storm.view




trait CommandMatcher {
  def matchinput(input: List[Token]): Option[CommandCreator]
  def register(cc: CommandCreator): Unit
}











