package name.brijest.storm.model


import name.brijest.storm.model.impl.managers._


abstract class Owner

case object NoOwner extends Owner

case class PlayerOwner(index: Int, characterid: gcid) extends Owner








