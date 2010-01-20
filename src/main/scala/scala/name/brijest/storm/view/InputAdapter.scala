package name.brijest.storm.view



/**
 * Manages user input.
 */
trait InputAdapter {
  def manageInput(matcher: CommandMatcher): CommandCreator
}
