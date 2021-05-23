package ui
import FacultiesPath._
object MainTest {

  def main(args: Array[String]): Unit = {
    val interpreter = Interpreter()
    var faculyPath: FacultiesPath = null
    interpreter.start()

    val syllabusLink = interpreter.getSyllabusLink()


    val studiesCS: Boolean = interpreter.askAboutCourseType()
    if(studiesCS){
      faculyPath = interpreter.getFacultiesPath()
    }



  }

}
