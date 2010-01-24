package name.brijest.storm.main


import scala.collection._


object ArgExtractor {
  def extractArgs(args: Array[String]): Map[String, String] = {
    val mp = new mutable.HashMap[String, String]
    for (arg <- args; if arg(0) == '-') {
      val parts = arg.substring(1).split("=")
      if (parts.length == 1) mp.put(parts(0), "true")
      else mp.put(parts(0), parts(1))
    }

    new immutable.Map[String, String] {
      def get(k: String) = mp.get(k) match {
        case Some(str) => Some(str)
        case None => Some("")
      }
      def +[B >: String](kv: (String, B)) = throw new UnsupportedOperationException
      def -(k: String) = throw new UnsupportedOperationException
      override def size = mp.size
      def iterator = mp.iterator
    }
  }
}
