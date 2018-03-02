package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.value.DateValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;

public class DateQuestionModel extends BaseQuestionModel {

    private final DateValue value;

    public DateQuestionModel(QuestionData data) {
        super(data);
        this.value = (DateValue) data.getValue();
    }

    public Calendar getBasicValue() {
        if (value != null) {
            return value.getDateValue();
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    @Override
    public void accept(QuestionModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        if (value != null) {
            return value.getDateValue().toString();
        } else {
            return "No value";
        }
    }
}
