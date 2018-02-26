package visiting;

import java.util.HashMap;
import java.util.Map;

import ast.Block;
import ast.Form;
import ast.expression.*;
import ast.literal.*;
import ast.statement.*;
import ast.type.*;

	
public class TypeCheckerVisitor 
    implements ExpressionVisitor<Type, Void>, StatementVisitor<Void, Void>, LiteralVisitor<Type, Void> {
	
	// constructor
	public TypeCheckerVisitor() {
		   
	}
	
	private Map<String, Type> mapNameType = new HashMap<>();
	
 	public void insertToMap(String name, Type type) {
    	mapNameType.put(name, type);
 	}

	public Type getType(String name) {
		if (!mapNameType.containsKey(name)) {
			return new UndefinedType();
		}
		return mapNameType.get(name);
 	}
	


	public void visit(Form form) {
		form.getBlock().accept(new MainVisitor<Void, Void>() {
						 @Override
						 public Void visit(ComputedQuestion question, Void ctx) {
							 insertToMap(question.getIdentifier().getIdentifier(), question.getType());
						   return null;
						 };
	
						 @Override
						 public Void visit(NormalQuestion question, Void ctx) {
							 insertToMap(question.getIdentifier().getIdentifier(), question.getType());
						   return null;
						 }
					 	},
					    null);
	
		form.getBlock().accept(this, null);
	}
	
	private void checkInvalidOperands(BinaryExpression expr, Type expectedType) {
		checkTypeMismatch(expr.getLeft(), expectedType);
		checkTypeMismatch(expr.getRight(), expectedType);
	}

	private void checkTypeMismatch(Expression expr, Type expectedType) {
		Type currentType =  expr.accept(this, null);
		if (isUndefinedType(currentType)) {
			return;
		}
		if (!currentType.equals(expectedType)) {
			  System.out.println("Error: Type mismatch in " + expr + "between: " + currentType + "and, " + expectedType);
		}
	}
	
	private boolean isUndefinedType(Type type) {
		return type.equals(new UndefinedType());
	}

	/* Block */
	@Override
	public Void visit(Block node, Void ctx) {
		node.getStatements().forEach(statement -> statement.accept(this, ctx));
		return null;
	}

	/* Statements and master-expressions */
	@Override
	public Void visit(IfThenStatement node, Void ctx) {
		checkTypeMismatch(node.getExpression(), Type.BOOLEAN);
		node.getIfBody().accept(TypeCheckerVisitor.this, ctx);
		return null;
	}
	
	@Override
	public Void visit(IfThenElseStatement node, Void ctx) {
		checkTypeMismatch(node.getExpression(), Type.BOOLEAN);
		node.getIfBody().accept(TypeCheckerVisitor.this, ctx);
		node.getElseBody().accept(TypeCheckerVisitor.this, ctx);
		return null;
	}
	
	@Override
	public Void visit(ComputedQuestion node, Void ctx) {
	   Type type =  getType(node.getIdentifier().getIdentifier());
	   checkTypeMismatch(node.getExpression(), type);
	   return null;
	}

	@Override
	public Void visit(NormalQuestion node, Void ctx) {
	   return null;
	}


	@Override
	public Type visit(LiteralExpression node, Void ctx) {
		return node.getObj().accept(this, ctx);
	}
	
	@Override
	public Type visit(IdentityExpression node, Void ctx) {
	   Type type;
	   type = getType(node.getName());
	   if (isUndefinedType(type)) {
		   System.out.println("Error: Undeclared variable: " + node.getName());
		}
	   return type;
	}



	/* Literals, return type boolean */
	@Override
	public Type visit(BooleanLiteral node, Void ctx) {
	   return Type.BOOLEAN;
	}

	@Override
	public Type visit(IntegerLiteral node, Void ctx) {
	   return Type.INTEGER;
	}

	@Override
	public Type visit(StringLiteral node, Void ctx) {
	   return Type.STRING;
	}
   
	@Override
	public Type visit(DateLiteral node, Void ctx) {
	   return Type.DATE;
	}
   
	@Override
	public Type visit(DecimalLiteral node, Void ctx) {
	   return Type.DECIMAL;
	}
   
	@Override
	public Type visit(MoneyLiteral node, Void ctx) {
	   return Type.MONEY;
	}


	/* Mathematical operations */
	@Override
	public Type visit(Add node, Void ctx) {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.INTEGER;
	}
	
	@Override
	public Type visit(Sub node, Void ctx) {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.INTEGER;
	}


	@Override
	public Type visit(Mul node, Void ctx)  {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.INTEGER;
	}
	
	@Override
	public Type visit(Div node, Void ctx) {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.INTEGER;
	}

	
	@Override
	public Type visit(Neg node, Void ctx) {
	checkTypeMismatch(node, Type.INTEGER);
	return Type.INTEGER;
	}

	@Override
	public Type visit(Pos node, Void ctx) {
		checkTypeMismatch(node, Type.INTEGER);
		return Type.INTEGER;
	}


	/* Comparison operations */
	@Override
	public Type visit(GT node, Void ctx) {
	   checkInvalidOperands(node, Type.INTEGER);
	   return Type.BOOLEAN;
	}
	
	@Override
	public Type visit(GEq node, Void ctx) {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.BOOLEAN;
	}
	
	@Override
	public Type visit(LT node, Void ctx) {
		checkInvalidOperands(node, Type.INTEGER);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(LEq node, Void ctx) {
	   checkInvalidOperands(node, Type.INTEGER);
	   return Type.BOOLEAN;
	}
	
	@Override
	public Type visit(Eq node, Void ctx) {
		Type leftType = node.getLeft().accept(this, ctx);
		Type rightType = node.getRight().accept(this, ctx);
		if (!isUndefinedType(leftType) && !isUndefinedType(rightType)) {
		  if (!leftType.equals(rightType)) {
			  System.out.println("Error: Invalid operand for Eq: " + leftType + ", " + rightType);
		  }
		}
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(NEq node, Void ctx) {
		Type leftType = node.getLeft().accept(this, ctx);
		Type rightType = node.getRight().accept(this, ctx);
		if (!isUndefinedType(leftType) && !isUndefinedType(rightType)) {
		  if (!leftType.equals(rightType)) {
			  System.out.println("Error: Invalid operand for NEq: " + leftType + ", " + rightType);
		  }
		}
		return Type.BOOLEAN;
	}

	
	/* Logical operations */
	@Override
	public Type visit(And node, Void ctx) {
	   checkInvalidOperands(node, Type.BOOLEAN);
	   return Type.BOOLEAN;
	}

	@Override
	public Type visit(Or node, Void ctx) {
	   checkInvalidOperands(node, Type.BOOLEAN);
	   return Type.BOOLEAN;
	}

	@Override
	public Type visit(Not node, Void ctx) {
		checkTypeMismatch(node, Type.BOOLEAN);
		return Type.BOOLEAN;
	}

	/* (expression) */
	@Override
	public Type visit(ParenthesesExpression node, Void ctx) {
		checkTypeMismatch(node.getExpression(), Type.BOOLEAN);
		return Type.BOOLEAN;
	}

 }