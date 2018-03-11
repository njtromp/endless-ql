package org.uva.qls.parsing;

import antlr.generated.QLSBaseVisitor;
import antlr.generated.QLSParser;

import org.uva.ql.ast.type.*;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.*;
import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.Question;
import org.uva.qls.ast.Segment.Section;
import org.uva.qls.ast.Segment.Segment;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;
import org.uva.qls.ast.Style.StyleProperty.StylePropertyStatement;
import org.uva.qls.ast.Widget.Widget;
import org.uva.qls.ast.Widget.WidgetTypes.*;

import java.util.ArrayList;
import java.util.List;

public class ParseTreeVisitor extends QLSBaseVisitor {
    @Override
    public Object visitStylesheet(QLSParser.StylesheetContext ctx) {
        String styleSheetId = ctx.id.getText();
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return new Stylesheet(styleSheetId, pages);
    }

    @Override
    public Object visitPage(QLSParser.PageContext ctx) {
        String pageId = ctx.ID().getText();

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStatement> defaultStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            defaultStatements.add((DefaultStatement) visit(defaultStatementContext));
        }

        return new Page(pageId, segments, defaultStatements);
    }

    @Override
    public Object visitSection(QLSParser.SectionContext ctx) {
        String sectionId = ctx.id.getText();

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStatement> defaultStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            defaultStatements.add((DefaultStatement) visit(defaultStatementContext));
        }

        return new Section(sectionId, segments, defaultStatements);
    }

    @Override
    public Object visitSegment(QLSParser.SegmentContext ctx) {
        if(ctx.section() != null) {
            return visit(ctx.section());
        }
        return visit(ctx.question());
    }

    @Override
    public Object visitDefaultStatement(QLSParser.DefaultStatementContext ctx) {
        Type type = (Type) visit(ctx.type());
        if(ctx.style() != null) {
            return new DefaultStyleStatement(type, (Style) visit(ctx.style()));
        }
        return new DefaultWidgetStatement(type, (Widget) visit(ctx.widget()));
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.id.getText();
        Style style = null;
        Widget widget = null;

        if(ctx.style() != null) {
            style = (Style) visit(ctx.style());
        } else if (ctx.widget() != null) {
            widget = (Widget) visit(ctx.widget());
        }

        return new Question(id, style, widget);
    }

    @Override
    public Object visitWidget(QLSParser.WidgetContext ctx) {
        return new Widget((WidgetType) visit(ctx.widgetType()));
    }

    @Override
    public Object visitRadioType(QLSParser.RadioTypeContext ctx) {
        if(ctx.yes != null && ctx.no != null)
            return new RadioType(ctx.yes.getText(), ctx.no.getText());
        return new RadioType(null, null);
    }

    @Override
    public Object visitCheckboxType(QLSParser.CheckboxTypeContext ctx) {
        if(ctx.yes != null)
            return new CheckboxType(ctx.yes.getText());
        return new CheckboxType(null);
    }

    @Override
    public Object visitDropdownType(QLSParser.DropdownTypeContext ctx) {
        if(ctx.yes != null && ctx.no != null)
            return new DropDownType(ctx.yes.getText(), ctx.no.getText());
        return new DropDownType(null, null);

    }

    @Override
    public Object visitSliderType(QLSParser.SliderTypeContext ctx) {
        return new SliderType(ctx.start.getText(), ctx.end.getText(), ctx.step.getText());
    }

    @Override
    public Object visitTextType(QLSParser.TextTypeContext ctx) {
        return new TextType();
    }

    @Override
    public Object visitBooleanType(QLSParser.BooleanTypeContext ctx) {
        return new BooleanType();
    }

    @Override
    public Object visitIntegerType(QLSParser.IntegerTypeContext ctx) {
        return new IntegerType();
    }

    @Override
    public Object visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        return new MoneyType();
    }

    @Override
    public Object visitStringType(QLSParser.StringTypeContext ctx) {
        return new StringType();
    }

    @Override
    public Object visitStyle(QLSParser.StyleContext ctx) {
        List<StyleProperty> styleProperties = new ArrayList<>();
        for (QLSParser.StylePropertyContext stylePropertyContext : ctx.styleProperty()) {
            styleProperties.add((StyleProperty) visit(stylePropertyContext));
        }
        if (ctx.widget() != null)
            return new Style(styleProperties, (Widget) visit(ctx.widget()));
        return new Style(styleProperties, null);
    }

    @Override
    public Object visitStyleProperty(QLSParser.StylePropertyContext ctx) {
        return new StylePropertyStatement(ctx.property.getText(), (Value) visit(ctx.value()));
    }

    //TODO Process values correctly
    @Override
    public Object visitValue(QLSParser.ValueContext ctx) {
        return super.visitValue(ctx);
    }
}
