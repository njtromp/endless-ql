package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.gui.converter.ModelConverter
import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql.expressions.{ BinaryOperation, Expression }
import nl.uva.se.sc.niro.model.ql.{ And, Conditional }

object GUIConditionalFactory {

  def makeGUIConditional(visible: Expression, conditional: Conditional): Seq[GUIQuestion] = {
    ModelConverter.convert(combineConditions(visible, conditional.predicate), conditional.thenStatements)
  }

  private def combineConditions(visible: Expression, predicate: Expression): Expression = {
    BinaryOperation(And, visible, predicate)
  }

}
