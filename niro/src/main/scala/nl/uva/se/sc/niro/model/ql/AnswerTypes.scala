package nl.uva.se.sc.niro.model.ql

sealed abstract class AnswerType

case object BooleanType extends AnswerType
case object IntegerType extends AnswerType
case object StringType extends AnswerType
case object DecimalType extends AnswerType
case object MoneyType extends AnswerType
case object DateType extends AnswerType

object AnswerType {
  def apply(answerType: String): AnswerType = answerType match {
    case "boolean" => BooleanType
    case "integer" => IntegerType
    case "string"  => StringType
    case "decimal" => DecimalType
    case "money"   => MoneyType
    case "date"    => DateType
    case _ =>
      throw new IllegalArgumentException(s"Unsupported answer type: $answerType")
  }
}
