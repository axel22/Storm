import sbt._


class StormEnrouteProject(info: ProjectInfo) extends DefaultProject(info) {
  /// compiling
  override def compileOptions = "-unchecked -encoding us-ascii".split(" ").map(CompileOption).toSeq ++ super.compileOptions

  override def javaCompileOptions =
    "-deprecation -encoding us-ascii -Xlint:all -Xlint:-serial -Xlint:-fallthrough -source 1.5 -target 1.5"
            .split(" ").map(JavaCompileOption).toSeq ++ super.javaCompileOptions
}