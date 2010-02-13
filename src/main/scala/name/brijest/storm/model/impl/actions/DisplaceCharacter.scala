package name.brijest.storm.model.impl.actions



import name.brijest.storm.model._



class DisplaceCharacter(chrid: cid, dest: (Int, Int)) extends Action {
  case class event(chrid: cid, destination: (Int, Int), val modelId: mid) extends Event {
    def text = None
    def position = Some(destination)
  }
  
  def apply(ad: ModelAdapter) = {
    ad.characterPos(chrid) match {
      case Some(pos) => ad.moveCharacter(pos, dest)
      case None =>
    }
    List[Event](event(chrid, dest, ad.id))
  }
}













