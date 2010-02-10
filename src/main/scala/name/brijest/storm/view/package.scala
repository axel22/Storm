package name.brijest.storm.view





package object view {
  object ctrl {
    def unapply(s: String): Option[Char] = if (s.startsWith("C-") && s.length == 3) Some(s.charAt(2)) else None
  }
  
  object alt {
    def unapply(s: String): Option[Char] = if (s.startsWith("M-") && s.length == 3) Some(s.charAt(2)) else None
  }
  
  implicit def string2TokenList(s: String): Seq[KeyToken] = s split " " map (_ match {
    case ctrl(k) => KeyToken(k, KeyToken.CTRL)
    case alt(k) => KeyToken(k, KeyToken.ALT)
    case k if k.length == 1 => KeyToken(k.charAt(0), KeyToken.NOFLAGS)
    case _ => throw new IllegalArgumentException("Cannot tokenize: " + s)
  })
}








