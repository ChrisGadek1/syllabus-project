package Plan

import scala.collection.immutable.VectorBuilder

class Semester(var subjectsBuffer: VectorBuilder[Subject], val semesterNumber: Int, var subjects: Vector[Subject] = null) {

  private class IsNotN[A](n: Int) extends (A => Boolean){
    private[this] var i = -1
    override def apply(v1: A): Boolean = {i += 1; i != n}
  }

  def bufferToVector(): Unit ={
    subjects = subjectsBuffer.result()
  }

  def removeSubject(num: Int) = {
    val removeSubjectF = new IsNotN[Subject](num - 1)
    subjects = subjects.filter(removeSubjectF)
  }

  def printSemester(): Unit ={
    for((subject, i) <- subjects.view.zip(LazyList from 1)){
      println(i.toString + " " + subject.subjectInfo())
    }
  }

}

object Semester{
  def apply(subjectsBuffer: VectorBuilder[Subject], semesterNumber: Int): Semester = new Semester(subjectsBuffer, semesterNumber)
}