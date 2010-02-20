package name.brijest.storm.view
package impl.commands



import name.brijest.storm.view.impl.commands.actions._



trait MainCommandRepository extends CommandMatcher {
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
  
  // view inventory
  register(ViewInventoryCreator)
}
