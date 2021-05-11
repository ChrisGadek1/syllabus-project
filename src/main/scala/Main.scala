import HTML.HTMLParsing
import Plan.{Semester, Subject}

object Main {
  def main(args: Array[String]): Unit = {
    val htmlParsing = new HTML.HTMLParsing()
    val semesters = htmlParsing.getParsedPlan("https://sylabusy.agh.edu.pl/pl/1/1/15/1/4/12/13")
    semesters.forEach((semester: Semester) => {
      print("semestr numer: "+semester.semesterNumber+"\n\n")
      semester.subjects.forEach((subject: Subject) => {
        print("nazwa: "+subject.subjectName+", labów: "+subject.labNumbers+", ćwiczeń: "+subject.audNumber+"\n")
      })
      print("\n========================\n")
    })
  }
}