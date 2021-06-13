import Plan.{Semester, Subject}
import DGenerator.DirectoryGenerator

object Main {
  def main(args: Array[String]): Unit = {
    val htmlParsing = new HTML.HTMLParsing()
    val semesters = htmlParsing.getParsedPlan("https://sylabusy.agh.edu.pl/pl/1/1/15/1/4/14/29", true,"wytwarzanie oprogramowania")
    semesters.foreach((semester: Semester) => {
      print("semestr numer: "+semester.semesterNumber+"\n\n")
      semester.subjects.foreach((subject: Subject) => {
        print("nazwa: "+subject.subjectName+", labów: "+subject.labNumbers+", ćwiczeń: "+subject.audNumber+"\n")
      })
      print("\n========================\n")
    })
//    val dirGen = new DirectoryGenerator()
//    dirGen.generateDirectoryTree(semesters, "C:\\")
  }
}