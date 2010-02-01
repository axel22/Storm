package name.brijest.storm.view
package commands



import actions._



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
