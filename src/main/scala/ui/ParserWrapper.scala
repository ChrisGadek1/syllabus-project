package ui

import HTML.HTMLParsing
import Plan.{Semester, Subject}
import ui.FacultiesPath._

class ParserWrapper(val syllabusLink: String, val parser: HTMLParsing = new HTMLParsing(), var semesters: Vector[Semester] = null ) {

  def parse(isCs: Boolean = false, path: FacultiesPath = None): Unit ={
    semesters = parser.getParsedPlan(syllabusLink, isCs, pathToParse(path))
  }

}

object ParserWrapper{

  def apply(syllabusLink: String, parser: HTMLParsing): ParserWrapper = new ParserWrapper(syllabusLink, parser)
  def apply(syllabusLink: String): ParserWrapper = new ParserWrapper(syllabusLink)

}
