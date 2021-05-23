package ui

import HTML.HTMLParsing

class ParserWrapper(val parser: HTMLParsing, val syllabusLink: String ) {

  def

}

object ParserWrapper{

  def apply(parser: HTMLParsing, syllabusLink: String): ParserWrapper = new ParserWrapper(parser, syllabusLink)

}
