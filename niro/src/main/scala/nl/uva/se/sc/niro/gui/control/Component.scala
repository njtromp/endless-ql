package nl.uva.se.sc.niro.gui.control

import java.time.LocalDate

import javafx.scene.control._
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.factory._
import nl.uva.se.sc.niro.gui.listener.{ ComponentChangedListener, ValueChangedListener }
import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.answers._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

abstract class Component[T](id: String, label: Label, control: QLWidget[_]) extends HBox with ValueChangedListener {
  private val componentChangedListeners = ArrayBuffer[ComponentChangedListener]()

  control.addValueChangedListener(this)
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def getQuestionId: String = id

  def getControl: QLWidget[_] = control

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)
  def isReadOnly: Boolean = control.isDisabled

  def updateValue(dictionary: mutable.Map[String, Answer]): Unit
  def getValue: Answer
  def setValue(value: Option[T]): Unit

  def addComponentChangedListener(componentChangedListener: ComponentChangedListener): Unit =
    componentChangedListeners.append(componentChangedListener)

  override def valueChanged(control: QLWidget[_]): Unit = {
    componentChangedListeners.foreach(_.componentChanged(this))
  }

}

case class StringComponent(id: String, label: Label, control: QLWidget[String])
    extends Component[String](id, label, control) {
  override def getValue: StringAnswer = toAnswer(control.getValue)
  override def setValue(value: Option[String]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.map(_.toString) else None)
  }
  private def fromOption(value: Option[String]): String = value.orNull
  private def toAnswer(value: String): StringAnswer = if (value == null) StringAnswer() else StringAnswer(value)
}

case class BooleanComponent(id: String, label: Label, control: QLWidget[Boolean])
    extends Component[Boolean](id, label, control) {
  override def getValue: BooleanAnswer = BooleanAnswer(control.getValue)
  override def setValue(value: Option[Boolean]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.asInstanceOf[Option[Boolean]] else None)
  }
  private def fromOption(value: Option[Boolean]): Boolean = value.getOrElse(false)
}

case class DateComponent(id: String, label: Label, control: QLWidget[LocalDate])
    extends Component[LocalDate](id, label, control) {
  override def getValue: DateAnswer = DateAnswer(control.getValue)
  override def setValue(value: Option[LocalDate]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.asInstanceOf[Option[LocalDate]] else None)
  }
  private def fromOption(value: Option[LocalDate]): LocalDate = if (value.isDefined) value.get else null
}

case class IntegerComponent(id: String, label: Label, control: QLWidget[Integer])
    extends Component[Int](id, label, control) {
  override def getValue: IntegerAnswer = IntegerAnswer(control.getValue)
  override def setValue(value: Option[Int]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.asInstanceOf[Option[Int]] else None)
  }
  private def fromOption(value: Option[Int]): java.lang.Integer = if (value.isDefined) value.get else null
}

case class DecimalComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: DecimalAnswer = toAnswer(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.asInstanceOf[Option[BigDecimal]] else None)
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal =
    if (value.isDefined) value.get.bigDecimal else null
  private def toAnswer(value: java.math.BigDecimal): DecimalAnswer =
    if (value == null) DecimalAnswer() else DecimalAnswer(value)
}

case class MoneyComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: MoneyAnswer = toAnswer(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(if (dictionary.isDefinedAt(id)) dictionary(id).possibleValue.asInstanceOf[Option[BigDecimal]] else None)
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal =
    if (value.isDefined) value.get.bigDecimal else null
  private def toAnswer(value: java.math.BigDecimal): MoneyAnswer =
    if (value == null) MoneyAnswer() else MoneyAnswer(value)
}

object ComponentFactory {
  def make(question: GUIQuestion): Component[_] = {
    val component = question.answerType match {
      case StringType  => StringComponent(question.id, new Label(question.label), StringWidgetFactory.make(question))
      case BooleanType => BooleanComponent(question.id, new Label(question.label), BooleanWidgetFactory.make(question))
      case DateType    => DateComponent(question.id, new Label(question.label), DateWidgetFactory.make(question))
      case IntegerType => IntegerComponent(question.id, new Label(question.label), IntegerWidgetFactory.make(question))
      case DecimalType => DecimalComponent(question.id, new Label(question.label), DecimalWidgetFactory.make(question))
      case MoneyType   => MoneyComponent(question.id, new Label(question.label), MoneyWidgetFactory.make(question))
    }
    component.setReadOnly(question.isReadOnly)
    question.component = Some(component)
    component
  }

}
