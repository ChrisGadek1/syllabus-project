package ui

object FacultiesPath extends Enumeration {

  type FacultiesPath = Value
  val WO, Algo, AlgoApi = Value

  def facultiesPathToString(p: FacultiesPath) = {
    p match {
      case WO => "Ścieżka wytwarzanie oprogramowania"
      case Algo => "Ścieżka algorytmiczna"
      case AlgoApi => "Ścieżka algorytmiczno aplikacyjna"
      case _ => "Nieznana ścieżka"
    }
  }


}
