package name.brijest.storm.view


import net.slashie.libjcsi.CharKey



object Token {
  val NOFLAGS = 0
  val ESC = 8
  val CTRL = 1
  val ALT = 2
  val SHIFT = 4
  
  implicit def string2TokenList(s: String) = s map (new Token(_)) toList;
  def apply(ck: CharKey): Token = {
    val chr = ck.toString.charAt(0)
    val flags = ck.modifier
    new Token(chr, flags)
  }
}


case class Token(c: Char, flags: Int) {
  def this(c: Char) = this(c, Token.NOFLAGS)
  
  def isEscape = (flags & Token.ESC) != 0
}






