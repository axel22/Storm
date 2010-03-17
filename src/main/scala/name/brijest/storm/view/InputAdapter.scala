package name.brijest.storm.view



/**
 * Manages user input.
 */
trait InputAdapter {
  def manageInput[St <: GuiState](matcher: Commands[St]#Matcher): Commands[St]#Creator
}
