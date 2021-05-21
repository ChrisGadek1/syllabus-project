package ui

import scala.io.StdIn.readLine

class Interpreter {

  def start() = {
    println("Syllabus parser v1.0")
  }

  def askAboutCourseType()={
    println("Czy studiuejsz informatykę na WIEiT? Tak/Nie")
    def getResponse(): Boolean = {
      val response = readLine()
      response match {
        case "Tak" => true
        case "Nie" => false
        case _ => {
          println("Zła odpowieź")
          getResponse()
        }
      }
    }
    getResponse()
  }

  def getFacultiesPath()={

  }

  def getSyllabusLink()={
    println("Podaj link do syllbusów Twojego kierunku, np. sylabusy.agh.edu.pl/pl/1/1/17/1/4/12/13")

    def getLink():String = {
      val syllabusLink = readLine()
      if(!syllabusLink.contains("sylabusy.agh.edu.pl")){
        println("Podano nieprawidłowy link")
        getLink()
      } else{
        syllabusLink
      }
    }
    getLink()
  }

}

object Interpreter{
  def apply(): Interpreter = new Interpreter()
}
