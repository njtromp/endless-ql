package visitor.stylesheet;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import expression.ReturnType;
import model.stylesheet.Default;
import model.stylesheet.widgets.Widget;

import java.util.ArrayList;

public class VisitorDefault extends QLSBaseVisitor<Default> {
    @Override
    public Default visitDefault_(QLSParser.Default_Context ctx) {

        VisitorWidget visitorWidget = new VisitorWidget();

        // TODO
        ReturnType returnType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        ArrayList<Widget> widgets = new ArrayList<>();
        for(QLSParser.WidgetContext widgetContext : ctx.widget()){
            Widget widget = visitorWidget.visitWidget(widgetContext);
            widgets.add(widget);
        }

        return new Default(returnType, widgets);
    }
}
