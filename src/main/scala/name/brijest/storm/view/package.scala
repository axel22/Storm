package name.brijest.storm.view





package object view {
  implicit def string2TokenList(s: String) = s map (new KeyToken(_)) toList;
}








