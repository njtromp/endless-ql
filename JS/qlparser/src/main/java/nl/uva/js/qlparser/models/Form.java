package nl.uva.js.qlparser.models;

import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Form<T extends Component & HasValue>
        implements Expression, Expression.TypeCheckable, Expression.Visualizable {

    @NonNull private String name;
    private LinkedList<FormExpression> formExpressions;

    @Override
    public List<AbstractField> getComponents() {
        LinkedList<AbstractField> components = new LinkedList<>();

        formExpressions.stream()
                .map(FormExpression::getComponents)
                .forEach(components::addAll);

        return components;
    }

    @Override
    public void checkType() {
        formExpressions.forEach(Expression.TypeCheckable::checkType);
    }

    public String getHumanizedName() {
        return StringUtils.capitalize(
                Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name))
                        .map(String::toLowerCase)
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" ")));
    }
}
