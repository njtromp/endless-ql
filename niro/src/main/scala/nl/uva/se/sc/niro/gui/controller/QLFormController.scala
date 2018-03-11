package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.control.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.gui.converter.ModelConverter
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.{ GUIForm, GUIQuestion }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer }

import scala.collection.{ JavaConverters, mutable }

class QLFormController extends QLBaseController with ComponentChangedListener {
  private val dictionary = mutable.Map[String, Answer]()
  private var qlForm: QLForm = _
  private var guiForm: GUIForm = _
  private var questions: Seq[Component[_]] = _

  @FXML var formName: Label = _
  @FXML var questionArea: VBox = _

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    QLForms.openHomeScreen(getActiveStage(event))

  @FXML def saveData(event: ActionEvent): Unit =
    // TODO Implement
    println("Data is saved....")

  def componentChanged(component: Component[_]): Unit = {
    dictionary(component.getQuestionId) = component.getValue
    evaluateAnswers
    updateView
  }

  def initializeForm(form: QLForm): Unit = {
    this.qlForm = form
    guiForm = ModelConverter.convert(this.qlForm)
    formName.setText(guiForm.name)

    questions = guiForm.questions.map(ComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))

    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    evaluateAnswers
    updateView
  }

  private def evaluateAnswers(): Unit = {
    dictionary ++= Evaluator.evaluate(qlForm, dictionary.toMap)
  }

  private def updateView = {
    updateValues()
    updateVisibility()
  }

  private def updateValues(): Unit = {
    questions.foreach(_.updateValue(dictionary))
  }

  private def updateVisibility(): Unit = {
    guiForm.questions.foreach(question => {
      getVisibilitySetting(question) match {
        case visibility: BooleanAnswer => question.component.map(_.setVisible(isVisible(visibility)))
        case _                         => throw new IllegalArgumentException("A if-condition did not result in a boolean expression!")
      }
    })
  }

  private def isVisible(b: BooleanAnswer) = {
    b.possibleValue.getOrElse(false)
  }

  private def getVisibilitySetting(question: GUIQuestion) = {
    Evaluator.evaluateExpression(question.visibility, qlForm.symbolTable, dictionary.toMap)
  }
}
