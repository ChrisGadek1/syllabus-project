package DGenerator
import java.io.File
import java.util

import Plan.{Semester, Subject}

class DirectoryGenerator {
  def generateDirectoryTree(semesters: util.LinkedList[Semester], path: String): Unit ={

    val path_V = path+"\\studia_test"
    val directory:File = new File(path_V)
    if(!directory.exists()){
      directory.mkdir()
    }
    semesters.forEach((semester: Semester) =>{
      val currentSemPath = path_V+"\\semestr "+semester.semesterNumber
      val semesterDir = new File(currentSemPath)
      if(!semesterDir.exists()){
        semesterDir.mkdir()
      }
      semester.subjects.forEach((subject: Subject) => {
        val currentSubjectPath = currentSemPath+"\\"+subject.subjectName
        val subjectDir = new File(currentSubjectPath)
        if(!subjectDir.exists()){
          subjectDir.mkdir()
        }
        for(i <- 1.to(subject.labNumbers/2)){
          val currentLabPath = currentSubjectPath+"\\"+"lab "+i
          val labDir = new File(currentLabPath)
          if(!labDir.exists()){
            labDir.mkdir()
          }
        }
        for(i <- 1.to(subject.audNumber/2)){
          val currentAudPath = currentSubjectPath+"\\"+"cw "+i
          val audDir = new File(currentAudPath)
          if(!audDir.exists()){
            audDir.mkdir()
          }
        }
      })
    })
  }
}
