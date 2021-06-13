package ui
import FacultiesPath._
import Plan.Semester
object MainTest {

  def main(args: Array[String]): Unit = {
    val interpreter = Interpreter()
    var facultyPath: FacultiesPath = null
    interpreter.start()

    val syllabusLink: String = interpreter.getSyllabusLink()

    val studiesCS: Boolean = interpreter.askAboutCourseType()
    if(studiesCS){
      facultyPath = interpreter.getFacultiesPath()
    }

    val parser = ParserWrapper(syllabusLink)

    parser.parse(studiesCS, facultyPath)

    val semesterNumber = interpreter.getSemesterNumber()

    val semester: Semester = parser.semesters(semesterNumber - 1)

    semester.printSemester()

    interpreter.editOrCreateDirs(semester)

    interpreter.createDirs(semester)


  }

}
