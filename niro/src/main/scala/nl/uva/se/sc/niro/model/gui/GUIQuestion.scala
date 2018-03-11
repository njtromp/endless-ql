package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.gui.control.Component
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.ql.expressions.Expression

case class GUIQuestion(
    id: String,
    answerType: AnswerType,
    label: String,
    isReadOnly: Boolean,
    visibility: Expression, // Holds the accumulated if-conditions.
    var component: Option[Component[_]] = None
)
