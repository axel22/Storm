package name.brijest.storm.view


import net.slashie.libjcsi.CharKey



trait Token


object KeyToken {
  val NOFLAGS = 0
  val ESC = 8
  val CTRL = 1
  val ALT = 2
  val SHIFT = 4
  
  def apply(ck: CharKey): Token = {
    val chr = ck.toString.charAt(0)
    val flags = ck.modifier
    new KeyToken(chr, flags)
  }
}


case class KeyToken(c: Char, flags: Int) extends Token {
  def this(c: Char) = this(c, KeyToken.NOFLAGS)
  
  def isEscape = (flags & KeyToken.ESC) != 0
}






