package DGenerator
import java.io.File

import Plan.{Semester, Subject}

class DirectoryGenerator {
  def generateDirectoryTree(semesters: Vector[Semester], directoryPath: String): Unit ={


    val directory:File = new File(directoryPath)
    if(!directory.exists()){
      directory.mkdir()
    }
    semesters.foreach((semester: Semester) =>{
      val currentSemPath = directoryPath+"\\semestr "+semester.semesterNumber
      val semesterDir = new File(currentSemPath)
      if(!semesterDir.exists()){
        semesterDir.mkdir()
      }
      semester.subjects.foreach((subject: Subject) => {
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
        for(i <- 1.to(subject.projNumber/2)){
          val currentAudPath = currentSubjectPath+"\\"+"proj "+i
          val audDir = new File(currentAudPath)
          if(!audDir.exists()){
            audDir.mkdir()
          }
        }
      })
    })
  }

  def createDirsForSemester(semester: Semester, directoryPath: String): Unit = generateDirectoryTree(Vector(semester), directoryPath)


}
