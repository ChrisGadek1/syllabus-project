package HTML

//import java.util

import Plan.{Semester, Subject}
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, Node}

import scala.collection.immutable.VectorBuilder

class HTMLParsing {
  def getHTML(url: String): String ={
    scala.io.Source.fromURL(url).mkString
  }

  def getSubjectsHours(name: String, hours:String): Int = {
    if(hours.contains(name)){
      var index: Int = hours.indexOf(name)+name.size
      var numberStr = ""
      while(index < hours.size && hours.charAt(index) != ' '){
        numberStr += hours.charAt(index)
        index = index + 1
      }
      Integer.parseInt(numberStr)
    }
   else{
      0
    }

  }
  def parseHoursToObject(semester: Semester, element: Element, subjectName: String): Unit ={
    val hours: String = element.select("td:eq(1)").text()
    val labNumber: Int = getSubjectsHours("Ćwiczenia laboratoryjne: ", hours)
    val audNumber: Int = getSubjectsHours("Ćwiczenia audytoryjne: ", hours)
//    semester.subjects.add(new Subject(subjectName, labNumber, audNumber))
    semester.subjectsBuffer += Subject(subjectName, labNumber, audNumber)
  }

  @throws(classOf[Exception])
  def getParsedPlan(url: String, is_CS: Boolean, path: String): Vector[Semester] = {
    val html = getHTML(url)
    val doc = Jsoup.parse(html)

    val result: VectorBuilder[Semester] = new VectorBuilder
    var i: Int = 1

    doc.select("div[class$=tab-content] > div[aria-labelledby~=(period\\-[0-9])]").forEach((element: Element) => {

      var semester = new Semester(new VectorBuilder[Subject], i)

      element.select("tr:not([class])").forEach((subject: Element) => {

        val subjectName: String = subject.select("td[scope=row] div").text()

        if(is_CS && subjectName.contains("Ścieżki")){

          var pathElement: Element = subject;
          while(pathElement != null && pathElement.select("td:eq(0)") != null && pathElement.select("td:eq(0)").text() != "Ścieżka "+path){
            pathElement = pathElement.nextElementSibling()
          }

          if(pathElement == null){
            throw new java.io.IOException("podana ścieżka nie istnieje: "+path)
          }

          pathElement = pathElement.nextElementSibling()
          while(pathElement != null && pathElement.select("td:eq(0)") != null && pathElement.select("td:eq(0)").attr("style").contains("padding-left: 50")){
            parseHoursToObject(semester, pathElement, pathElement.select("td[scope=row] div").text())
            pathElement = pathElement.nextElementSibling()
          }

        }
        else{
          parseHoursToObject(semester, subject, subjectName)
        }

      })
      semester.bufferToVector()
      result += semester
      i += 1
    })
    result.result()
  }
}
