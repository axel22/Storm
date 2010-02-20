import sbt._


class StormEnrouteProject(info: ProjectInfo) extends DefaultProject(info) {
  /// compiling
  override def compileOptions = scalacompline.split(" ").map(CompileOption).toSeq ++ super.compileOptions
  override def javaCompileOptions = javacompline.split(" ").map(JavaCompileOption).toSeq ++ super.javaCompileOptions
  
  val scalacompline = "-unchecked -encoding us-ascii";
  val javacompline = "-deprecation -encoding us-ascii -Xlint:all -Xlint:-serial -Xlint:-fallthrough -source 1.5 -target 1.5";
  
  override def fork = forkRun
  
  lazy val turnBased = {
    val argline = "-mod=turn-based -view=swing-console -world=test-world".split(" ").toList
    runTask(Some("name.brijest.storm.main.Main"), compileClasspath, argline)
  } dependsOn (`package`)
}