package name.brijest.storm.model



trait Agent {
  /**
   * Returns an object which serves as a manager of this agent,
   * that is, which can return an action
   */
  def manager: Manager
}
