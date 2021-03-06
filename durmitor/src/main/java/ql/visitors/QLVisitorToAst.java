package ql.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import ql.ast.QLNode;
import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Expression;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.Subtract;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.exceptions.ANTLRError;
import ql.exceptions.QLException;
import ql.grammar.QLBaseVisitor;
import ql.grammar.QLParser;
import ql.grammar.QLParser.MoneyContext;
import ql.grammar.QLParser.MoneyTypeContext;
import ql.helpers.Currency;
import ql.helpers.Location;

public class QLVisitorToAst extends QLBaseVisitor<Object> {
    
    List<QLException> errors;
    
    public QLVisitorToAst() {
        errors = new ArrayList<QLException>();
    }
    
    public QLVisitorToAst(List<QLException> errors) {
        this.errors = errors;
    }
    
    private Map<String,Identifier> identifiers = new HashMap<String,Identifier>();
    
    @Override 
    public QLNode visitForm(QLParser.FormContext ctx) { 
        
        Identifier id   = (Identifier) visitIdentifier(ctx.identifier());
        Block block     = (Block) visitBlock(ctx.block());
        Form form       = new Form(id,block);
        
        return setLocation(form, ctx.start);
    }
    
    @Override 
    public QLNode visitBlock(QLParser.BlockContext ctx) { 
        
        Block block = new Block();
        
        for(QLParser.StatementContext child : ctx.statement())
        {
            Statement stmt = (Statement) visitStatement(child);
            
            block.addStatement((Statement) setLocation(stmt,child.start));
        }
        
        return block; 
    }

    @Override 
    public QLNode visitIfThen(QLParser.IfThenContext ctx) { 
        
        Expression expr     = (Expression) visit(ctx.condition);
        Statement thenStmt  = (Statement) visitStatement(ctx.thenStmt);
        IfThen stmt         = new IfThen(expr,thenStmt);
        
        return setLocation(stmt, ctx.start);
    }
    
    @Override 
    public QLNode visitIfThenElse(QLParser.IfThenElseContext ctx) { 
        
        Expression expr     = (Expression) visit(ctx.condition);
        Statement thenStmt  = (Statement) visitStatement(ctx.thenStmt);
        Statement elseStmt  = (Statement) visitStatement(ctx.elseStmt);
        IfThenElse stmt     = new IfThenElse(expr,thenStmt,elseStmt);
        
        return setLocation(stmt, ctx.start);
    }

    @Override 
    public QLNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) { 
        
        String label    = stripQuotations(ctx.label());
        Type type       = (Type) visit(ctx.type());
        Identifier id   = createIdentifier(ctx.identifier(),type);
        Expression expr = (Expression) visit(ctx.expr());
        
        return setLocation(new ComputedQuestion(label,id,expr), ctx.start);
    }

    @Override 
    public QLNode visitAnswerableQuestion(QLParser.AnswerableQuestionContext ctx) { 
        
        String label    = stripQuotations(ctx.label());
        Type type       = (Type) visit(ctx.type());
        Identifier id   = createIdentifier(ctx.identifier(),type);
        
        return setLocation(new AnswerableQuestion(label,id), ctx.start);
    }
    
    @Override 
    public QLNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return setLocation(new BoolLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return setLocation(new StrLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return setLocation(new IntLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return setLocation(new DecimalLiteral(ctx.getText()), ctx.start);
    }

    @Override
    public Object visitMoney(MoneyContext ctx) {
        return setLocation(new MoneyLiteral(Currency.valueOf(ctx.currency().getText()), ctx.value.getText()), ctx.start);
    }

    @Override 
    public QLNode visitDateLiteral(QLParser.DateLiteralContext ctx) {
        return setLocation(new DateLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitIdentifier(QLParser.IdentifierContext ctx) {
        return createIdentifier(ctx);
    }
    
    @Override 
    public QLNode visitMulExpr(QLParser.MulExprContext ctx) {
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.MULTIPLY))       expr = new Multiply(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.DIVIDE))    expr = new Divide(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitAddExpr(QLParser.AddExprContext ctx) { 
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.PLUS))       expr = new Add(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.MINUS)) expr = new Subtract(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitRelExpr(QLParser.RelExprContext ctx) { 
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.LT))      expr = new Less(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.LE)) expr = new LessEqual(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.GT)) expr = new Greater(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.GE)) expr = new GreaterEqual(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.EQ)) expr = new Equal(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.NE)) expr = new NotEqual(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitAndExpr(QLParser.AndExprContext ctx) { 

        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        And expr        = new And(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitOrExpr(QLParser.OrExprContext ctx) {
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        Or expr         = new Or(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitPreExpr(QLParser.PreExprContext ctx) { 

        Expression expr = (Expression) visit(ctx.ex);
        QLNode node     = null;
        
        if(isTokenType(ctx.op,QLParser.NOT))         node = new Negation(expr);
        else if(isTokenType(ctx.op,QLParser.PLUS))   node = new Positive(expr);
        else if(isTokenType(ctx.op,QLParser.MINUS))  node = new Negative(expr);
        
        return setLocation(node, ctx.start);
    }

    @Override 
    public QLNode visitBraExpr(QLParser.BraExprContext ctx) { 
        
        Expression expr = (Expression) visit(ctx.ex);
        
        return setLocation(expr, ctx.start);
    }
    
    @Override 
    public QLNode visitBooleanType(QLParser.BooleanTypeContext ctx) { 
        return setLocation(new Bool(), ctx.start); 
    }

    @Override 
    public QLNode visitStringType(QLParser.StringTypeContext ctx) { 
        return setLocation(new Str(), ctx.start); 
    }

    @Override 
    public QLNode visitIntegerType(QLParser.IntegerTypeContext ctx) { 
        return setLocation(new Int(), ctx.start); 
    }

    @Override 
    public QLNode visitDecimalType(QLParser.DecimalTypeContext ctx) { 
        return setLocation(new Decimal(), ctx.start); 
    }

    @Override
    public QLNode visitMoneyType(MoneyTypeContext ctx) {
        
        Currency currency;
        
        if(Currency.exists(ctx.getText())) {
            currency = Currency.valueOf(ctx.getText());
        } else {
            currency = Currency.defaultCurrency;
            errors.add(new ANTLRError("Unknown currency "+ctx.getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine()));
        }
        
        return setLocation(new Money(currency), ctx.start);
    }

    @Override 
    public QLNode visitDateType(QLParser.DateTypeContext ctx) { 
        return setLocation(new Date(), ctx.start); 
    }

    private QLNode setLocation(QLNode n, Token t) {
        
        Location l = tokenToLocation(t);
        
        l.setLength(n.toString().length());
        n.setLocation(l);
        
        return n;
    }
    
    private Location tokenToLocation(Token t) {
        int line    = t.getLine();
        int column  = t.getCharPositionInLine();
        int offset  = t.getStartIndex();
        int length  = 1 + t.getStopIndex() - offset;
        
        return new Location(line, column, offset, length);
    }
    
    private boolean isTokenType(Token t, int type) {
        return t.getType() == type;
    }
    
    private String stripQuotations(QLParser.LabelContext lbl) {
        String label    = lbl.getText();
        int endIndex    = label.length() - 1;
        label           = label.substring(1, endIndex);
        
        return label;
    }
    
    private Identifier createIdentifier(QLParser.IdentifierContext ctx, Type type) {
        
        Identifier id = new Identifier(ctx.getText(), type);
        
        id.setLocation(tokenToLocation(ctx.start)).updateLength();
        
        if(identifiers.containsKey(id.getName())) id = replaceWithOriginalIdentifier(id);
        else identifiers.put(id.getName(), id);
        
        return id;
    }
    
    private Identifier createIdentifier(QLParser.IdentifierContext ctx) {
        return createIdentifier(ctx, new Undefined());
    }
    
    private Identifier replaceWithOriginalIdentifier(Identifier id) {
        
        String name = id.getName();
        
        if(identifiers.containsKey(name))
        {
            Identifier original = identifiers.get(name);
            
            if(id.getType().isUndefined()) return original;
            
            if(original.getType().isUndefined())
            {
                original.setType(id.getType());
                original.setLocation(id.getLocation());
            }
            
            return (original.getType().equals(id.getType()))? identifiers.get(name) : id;
        }
        
        return id;
    }
}
