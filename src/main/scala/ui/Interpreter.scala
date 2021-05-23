package ui

import scala.io.StdIn.readLine
import FacultiesPath._

class Interpreter {

  def start() = {
    println("Syllabus parser v1.0")
  }

  def askAboutCourseType()={
    println("Czy studiujesz informatykę na WIEiT? Tak/Nie")
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
    var i = 0
    print("Na jaką ścieżkę kształcenia uczęszczasz? 1/2/3\n")
    for(p <- FacultiesPath.values){
      println(i.toString + " " + facultiesPathToString(p))
      i += 1
    }
    def getResponse(): FacultiesPath = {
      val response = readLine()
      response match {
        case "1" => WO
        case "2" => Algo
        case "3" => AlgoApi
        case _ => {
          println("Podaj prawidłową ścieżkę")
          getResponse()
        }
      }
    }
    getResponse()
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
