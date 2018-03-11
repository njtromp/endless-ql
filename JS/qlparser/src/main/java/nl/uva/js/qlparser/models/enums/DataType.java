package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.models.expressions.data.Variable;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;
import wrappers.DoubleWrapper;
import wrappers.IntegerWrapper;

import javax.swing.*;
import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, ComponentBuilder::buildTextField),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> new DoubleWrapper(value.replace(',', '.')), ComponentBuilder::buildTextField),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), ComponentBuilder::buildTextField),
    DECIMAL(DoubleWrapper::new, ComponentBuilder::buildTextField),
    BOOLEAN(Boolean::valueOf, ComponentBuilder::buildTextField),
    INTEGER(IntegerWrapper::new, ComponentBuilder::buildTextField);

    @NonNull @Getter private Function<String, ?>                      valueOf;
    @NonNull @Getter private Function<Variable, ? extends JComponent> component;
}