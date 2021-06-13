package ui

object FacultiesPath extends Enumeration {

  type FacultiesPath = Value
  val WO, Algo, AlgoApi, None = Value

  def facultiesPathToString(p: FacultiesPath) = {
    p match {
      case WO => "Ścieżka wytwarzanie oprogramowania"
      case Algo => "Ścieżka algorytmiczna"
      case AlgoApi => "Ścieżka algorytmiczno aplikacyjna"
      case None => ""
      case _ => "Nieznana ścieżka"
    }
  }

  def pathToParse(p: FacultiesPath) = {
    p match {
      case WO => "wytwarzanie oprogramowania"
      case Algo => "algorytmiczna"
      case AlgoApi => "algorytmiczno-aplikacyjna"
      case None => ""
      case _ => ""
    }
  }


}
