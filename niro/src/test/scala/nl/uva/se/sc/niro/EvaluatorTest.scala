package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.Evaluator.Dictionary
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ BooleanAnswer, DecimalAnswer, IntegerAnswer }
import nl.uva.se.sc.niro.model.ql.expressions.{ BinaryOperation, Reference, UnaryOperation }
import nl.uva.se.sc.niro.model.ql._
import org.scalatest.WordSpec

class EvaluatorTest extends WordSpec {

  "EvaluatorTest" should {
    // TODO implement test for the evaluator
    "evaluateConditional" in {}

    "evaluateQuestion" in {}

    "evaluateStatement" in {}

    "evaluate a QLForm" can {
      "with single expression" in {
        val qLForm = QLForm(
          formName = "Revenue",
          statements = List(
            Question("revenue", "How much did you earn", IntegerType, IntegerAnswer(1000)),
            Question("expenses", "How much did you earn", IntegerType, IntegerAnswer(800)),
            Conditional(
              BooleanAnswer(true),
              Seq(
                Question(
                  "profit",
                  "You still have",
                  IntegerType,
                  BinaryOperation(Sub, Reference("revenue"), Reference("expenses")))
              ))
          )
        )

        val result = Evaluator.evaluate(qLForm, Map.empty)
        val expected =
          Map(
            "revenue" -> IntegerAnswer(Some(1000)),
            "expenses" -> IntegerAnswer(Some(800)),
            "profit" -> IntegerAnswer(Some(200))
          )

        assert(result == expected)
      }

      "with multiple expressions" in {
        val qlForm = QLForm(
          "EditOrNotToEdit",
          List(
            Question("booleanVariable", "Boolean variable", BooleanType, BooleanAnswer(None)),
            Question("integerVariable", "Integer variable", IntegerType, IntegerAnswer(None)),
            Question("decimalVariable", "Decimal variable", DecimalType, DecimalAnswer(None)),
            Conditional(
              Reference("booleanVariable"),
              List(
                Question(
                  "integerConstant",
                  "Integer constant",
                  IntegerType,
                  BinaryOperation(Mul, IntegerAnswer(21), IntegerAnswer(2))
                ),
                Question("decimalConstant", "Decimal constant", DecimalType, DecimalAnswer(42.4))
              )
            ),
            Conditional(
              UnaryOperation(Neg, Reference("booleanVariable")),
              List(
                Question(
                  "integerExpression",
                  "Integer expression",
                  IntegerType,
                  BinaryOperation(
                    Add,
                    BinaryOperation(Add, Reference("integerConstant"), IntegerAnswer(Some(1))),
                    Reference("integerVariable")
                  )
                ),
                Question(
                  "decimalExpression",
                  "Decimal expression",
                  DecimalType,
                  BinaryOperation(
                    Add,
                    BinaryOperation(Add, Reference("decimalConstant"), DecimalAnswer(1.0)),
                    Reference("decimalVariable")
                  )
                )
              )
            )
          )
        )

        val inputs: Dictionary = Map(
          "booleanVariable" -> BooleanAnswer(false),
          "integerVariable" -> IntegerAnswer(12),
          "decimalConstant" -> DecimalAnswer(42.4)
        )

        val result = Evaluator.evaluate(qlForm, inputs)
        val expected: Dictionary =
          Map(
            "integerConstant" -> IntegerAnswer(42),
            "booleanVariable" -> BooleanAnswer(false),
            "integerExpression" -> IntegerAnswer(55),
            "integerVariable" -> IntegerAnswer(12),
            "decimalConstant" -> DecimalAnswer(42.4)
          )

        assert(result == expected)
      }
    }
  }
}
