package ui
import FacultiesPath._
object MainTest {

  def main(args: Array[String]): Unit = {
    val interpreter = Interpreter()
    interpreter.start()
    val studiesCS = interpreter.askAboutCourseType()

  }

}
