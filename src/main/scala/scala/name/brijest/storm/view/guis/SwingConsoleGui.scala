package name.brijest.storm.view.guis


import name.brijest.storm.view._
import name.brijest.storm.view.input._

import net.slashie.libjcsi.wswing._
import net.slashie.libjcsi._


class SwingConsoleGui(csi: net.slashie.libjcsi.wswing.WSwingConsoleInterface) extends Gui {
  val renderAdapter = new SwingConsoleRenderAdapter(csi)
  val inputAdapter = new SwingKeyboardInputAdapter(csi)
}

class SwingConsoleRenderAdapter(val wcsi: WSwingConsoleInterface)
extends ConsoleRenderAdapter(wcsi)