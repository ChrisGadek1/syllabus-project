package Plan

class Subject(var subjectName: String,val labNumbers: Int, val audNumber: Int,val projNumber: Int, var available: Boolean = true) {

  def subjectInfo(): String = {
    var res = ""
    if(available) {
      res += subjectName + ";"
      res += (if (labNumbers > 0) " laboratoria: " + labNumbers + ";" else "")
      res += (if (audNumber > 0) " ćwiczenia: " + audNumber + ";" else "")
      res += (if (projNumber > 0) " projektowe: " + projNumber + ";" else "")
    }
    res
  }


}

object Subject{
  def apply(subjectName: String, labNumbers: Int, audNumber: Int, projNumber: Int): Subject = new Subject(subjectName, labNumbers, audNumber, projNumber)
}
