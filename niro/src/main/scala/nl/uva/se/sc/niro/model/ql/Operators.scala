package nl.uva.se.sc.niro.model.ql

sealed abstract class Operator
object Operator {
  def apply(operator: String): Operator = operator match {
    case "<"  => Lt
    case "<=" => Lte
    case "==" => Eq
    case "!=" => Ne
    case ">=" => Gte
    case ">"  => Gt
    case "-"  => Sub
    case "+"  => Add
    case "/"  => Div
    case "*"  => Mul
    case "||" => Or
    case "&&" => And
    case "!"  => Neg
  }
}

sealed abstract class ArithmeticOperator extends Operator
case object Add extends ArithmeticOperator
case object Sub extends ArithmeticOperator
case object Mul extends ArithmeticOperator
case object Div extends ArithmeticOperator

sealed abstract class BooleanOperator extends Operator
case object Lt extends BooleanOperator
case object Lte extends BooleanOperator
case object Gte extends BooleanOperator
case object Gt extends BooleanOperator
case object Ne extends BooleanOperator
case object Eq extends BooleanOperator

sealed abstract class LogicalOperator extends Operator
case object Or extends LogicalOperator
case object And extends LogicalOperator
case object Neg extends LogicalOperator
