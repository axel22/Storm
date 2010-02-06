package name.brijest.storm.model


import name.brijest.storm.model.impl.managers._


trait Owner

case object NoOwner extends Owner

case class PlayerOwner(val index: pid, val characterid: gcid) extends Owner








