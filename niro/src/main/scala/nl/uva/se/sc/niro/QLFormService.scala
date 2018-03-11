package nl.uva.se.sc.niro

import java.io.File

import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.parser.QLFormParser
import nl.uva.se.sc.niro.typechecking.TypeChecker
import org.antlr.v4.runtime.CharStreams

object QLFormService {

  def importQLSpecification(file: File): Either[Seq[Error], QLForm] = {
    val qlFormAst: QLForm = QLFormParser.parse(CharStreams.fromFileName(file.getAbsolutePath))
    val parseErrors: Seq[Error] = QLFormParser.getParseErrors.toList

    if (parseErrors.isEmpty) {
      TypeChecker.pipeline(qlFormAst).left.map(error => Seq(error))
    } else {
      Left(parseErrors)
    }
  }
}
