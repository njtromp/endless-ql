package nl.uva.se.sc.niro

import javafx.application.Application
import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Scene
import javafx.scene.control._
import javafx.scene.layout.{ HBox, VBox }
import javafx.scene.text.Font
import javafx.stage.{ Screen, Stage }
import nl.uva.se.sc.niro.model.Expressions.answers.IntAnswer
import nl.uva.se.sc.niro.model.{ QLForm, Question, Statement }
import nl.uva.se.sc.niro.parser.QLFormParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.apache.logging.log4j.scala.Logging

import scala.io.Source
import scala.util.{ Failure, Success, Try }

object REPL {
  def main(args: Array[String]) {
    Application.launch(classOf[REPL], args: _*)
  }
}

class REPL extends Application with Logging {

  var inputFrameText = ""

  var qlForm = QLForm("", Seq.empty)

  override def start(primaryStage: Stage) {
    primaryStage.setTitle("Niro Repl")

    val primaryScreenBounds = Screen.getPrimary.getVisualBounds

    // set stage to right half screen
    primaryStage.setX(primaryScreenBounds.getWidth / 2)
    primaryStage.setY(primaryScreenBounds.getHeight)
    primaryStage.setWidth(primaryScreenBounds.getWidth / 2)
    primaryStage.setHeight(primaryScreenBounds.getHeight)

    val inputLabel = new Label("QL Form specification")
    inputLabel.setFont(new Font("Arial", 20))
    val inputTextArea: TextArea = new TextArea
    inputFrameText = getDefaultQLForm
    inputTextArea.setText(inputFrameText)
    val inputFrame = new VBox(inputLabel, inputTextArea)

    parseQL(inputFrameText)

    val qlFormText = new Label(prettyPrintQLForm(qlForm))

    val astLabel = new Label("AST")
    astLabel.setFont(new Font("Arial", 20))
    val astFrame: VBox = new VBox(astLabel, qlFormText)

    val outputLabel = new Label("Questions")
    outputLabel.setFont(new Font("Arial", 20))
    val outputFrame: VBox = new VBox(outputLabel)
    renderQuestions(astFrame, outputFrame)

    val hbox = new HBox(inputFrame, astFrame, outputFrame)

    inputTextArea.textProperty().addListener(new ChangeListener[String] {
      def changed(p1: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
        logger.debug("change event!")
        parseQL(newValue)
        astFrame.getChildren.remove(1)
        astFrame.getChildren.add(new Label(prettyPrintQLForm(qlForm)))

        outputFrame.getChildren.clear()
        outputFrame.getChildren.add(outputLabel)
        renderQuestions(astFrame, outputFrame)
      }
    })

    primaryStage.setScene(new Scene(hbox))
    primaryStage.show()
  }

  def getDefaultQLForm: String = {
    Source.fromResource("examples/example.ql").mkString
  }

  def parseQLInput(newInput: String): Unit = {
    logger.debug("Parsing new input")
    parseQL(newInput)
  }

  def parseQL(newQLInput: String): Unit = {
    val inputStream = new ANTLRInputStream(newQLInput)
    Try(QLFormParser.parse(inputStream)).foreach(parsedForm => qlForm = parsedForm)
  }

  def getQLTextAreaText(tryQl: Try[QLForm]) = {
    tryQl match {
      case Success(form) =>
        logger.debug("Succesfully parsed")
        prettyPrintQLForm(form)
      case Failure(ex) =>
        logger.error(s"Failed to parse: $ex")
        ex.toString
    }
  }

  def prettyPrintQLForm(qlForm: QLForm): String = {
    pprint.apply(qlForm).plainText
  }

  def renderQuestions(astFrame: VBox, outputFrame: VBox): Unit = {
      val allQuestionsInForm = Statement.collectAllQuestions(qlForm.statements)
      val questions = allQuestionsInForm.map { case Question(id, label, expression, answer) =>
        val idField = new Label(id)
        val labelField = new Label(label)
        val inputField = new TextField()

        answer foreach {
          case IntAnswer(Some(value)) => inputField.setText(value.toString)
          case IntAnswer(None) => ()
        }

        inputField.textProperty().addListener(new ChangeListener[String] {
          def changed(p1: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
            logger.debug(s"change event on question: $id")
            val intAnswer = IntAnswer(Try(newValue.toInt).toOption)

            qlForm = qlForm.save(id, intAnswer)
            qlForm = Evaluator.evaluateQLForm(qlForm)

            astFrame.getChildren.remove(1)
            astFrame.getChildren.add(new Label(prettyPrintQLForm(qlForm)))

            val outputLabel = new Label("Questions")
            outputLabel.setFont(new Font("Arial", 20))
            outputFrame.getChildren.clear()
            outputFrame.getChildren.add(outputLabel)
            renderQuestions(astFrame, outputFrame)
          }
        })
        new HBox(idField, labelField , inputField)
      }
    questions.foreach(outputFrame.getChildren.add)
  }
}
