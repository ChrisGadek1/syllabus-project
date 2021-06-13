package ui

import DGenerator.DirectoryGenerator

import scala.io.StdIn.readLine
import scala.io.StdIn.readf
import FacultiesPath._
import Plan.{Semester, Subject}


class Interpreter {

  def start() = {
    println("Syllabus parser v1.0")
  }

  def askAboutCourseType()={
    println("Czy studiujesz informatykę na WIEiT? Tak/Nie")
    def getResponse(): Boolean = {
      val response = readLine()
      response.toLowerCase() match {
        case "tak" => true
        case "nie" => false
        case _ => {
          println("Zła odpowiedź")
          getResponse()
        }
      }
    }
    getResponse()
  }

  def getFacultiesPath()={
    var i = 1
    print("Na jaką ścieżkę kształcenia uczęszczasz? 1/2/3\n")
    for(p <- Seq(WO, Algo, AlgoApi)){
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
    println("Podaj link do syllabusów Twojego kierunku, np. https://sylabusy.agh.edu.pl/pl/1/1/17/1/4/12/13")

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

  def getSemesterNumber(): Int ={
    println("Dla którego semestru chesz utworzyć katalogi?")
    def getResponse(): Int = {
      try {
        val n = readLine().toInt
        if(n < 0 || n > 7){
          println("Podaj prawidłowy numer semestru")
          getResponse()
        }
        n
      }catch {
        case _: Throwable => {
          println("Podaj prawidłowy numer semestru")
          getResponse()
        }
      }

    }
    getResponse()

  }

  def editSubjects(semester: Semester)={

    def getResponse(): Unit ={
      try {
//        val res = readf("{0} {1}")
        val res = readLine()
        val splitRes = res.split(" ")
        var subjectNumber = 0
        val actionToPerform = splitRes(0)
        if(splitRes.length == 2) subjectNumber = splitRes(1).toInt

        if (subjectNumber > semester.subjects.length || subjectNumber < 0) {
          println("Podaj prawidłowy numer przedmiotu")
          getResponse()
        }
        actionToPerform.toLowerCase match {
          case "u" => {
            semester.removeSubject(subjectNumber)
            semester.printSemester()
            getResponse()
          }
          case "n" => {
            println("Podaj nową nazwę dla przedmiotu " + semester.subjects(subjectNumber - 1).subjectName)
            val newSubjectName = readLine()
            semester.subjects(subjectNumber - 1).subjectName = newSubjectName
            semester.printSemester()
            getResponse()
          }
          case x: String => if (x != "k") {
            println("Podano złą akcję")
            getResponse()
          }
        }
      } catch {
        case _: java.text.ParseException => {
          println("Podaj prawidłową akcję")
          getResponse()
        }
        case ex => {
          println(ex.toString)
          println("Wystąpił wyjątek" + ex.getStackTrace.toString)
          getResponse()
        }
      }
    }

    println("Podaj akcję jaką chcesz wykonać i numer przedmiotu ")
    println("U X - usuń przedmiot")
    println("N X - zmień nazwę przedmiotu")
    println("K - koniec edycji przedmiotów")
    semester.printSemester()
    getResponse()


  }

  def editOrCreateDirs(semester: Semester) = {
    println("Edytuj przedmioty lub utwórz katalogi E/U")
    def getResponse(): Unit={
      val res = readLine()
      res.toLowerCase match {
        case "e" => editSubjects(semester)
        case x: String => if(x!= "u") {
          println("Podaj prawidłową odpowiedź")
          getResponse()
        }
      }
    }
    getResponse()
  }

  def createDirs(semester: Semester) = {
    val dirGen = new DirectoryGenerator()
    println("Podaj ścieżkę do folderu, gdzie mają zostać utworzone katalogii?")
    val directoryPath = readLine()
    dirGen.createDirsForSemester(semester, directoryPath)
  }


}

object Interpreter{
  def apply(): Interpreter = new Interpreter()
}
