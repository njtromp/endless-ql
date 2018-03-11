// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/grammar/QL.g4 by ANTLR 4.7
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, OP_AND=5, OP_OR=6, OP_NOT=7, OP_ASSIG=8, 
		OP_MULT=9, OP_DIV=10, OP_PLUS=11, OP_MINUS=12, OP_GT=13, OP_GE=14, OP_LT=15, 
		OP_LE=16, OP_EQ=17, OP_NEQ=18, IF=19, ELSE=20, BEGIN=21, END=22, TYPE_BOOLEAN=23, 
		TYPE_STRING=24, TYPE_INTEGER=25, TYPE_DECIMAL=26, BOOL_TRUE=27, BOOL_FALSE=28, 
		WS=29, COMMENT=30, IDENTIFIER=31, INTEGER=32, STRING=33, DECIMAL=34;
	public static final int
		RULE_form = 0, RULE_statement = 1, RULE_question = 2, RULE_dataType = 3, 
		RULE_ifStatement = 4, RULE_elseStatement = 5, RULE_expression = 6;
	public static final String[] ruleNames = {
		"form", "statement", "question", "dataType", "ifStatement", "elseStatement", 
		"expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'('", "')'", "'&&'", "'||'", "'!'", "'='", "'*'", 
		"'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'if'", 
		"'else'", "'{'", "'}'", "'boolean'", "'string'", "'integer'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "BOOL_TRUE", "BOOL_FALSE", 
		"WS", "COMMENT", "IDENTIFIER", "INTEGER", "STRING", "DECIMAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormContext extends ParserRuleContext {
		public Token id;
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);
			setState(15);
			((FormContext)_localctx).id = match(IDENTIFIER);
			setState(16);
			match(BEGIN);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(17);
				statement();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				question();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				ifStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public Token label;
		public Token variableName;
		public DataTypeContext variableType;
		public ExpressionContext assignment;
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode OP_ASSIG() { return getToken(QLParser.OP_ASSIG, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			((QuestionContext)_localctx).label = match(STRING);
			setState(30);
			((QuestionContext)_localctx).variableName = match(IDENTIFIER);
			setState(31);
			match(T__1);
			setState(32);
			((QuestionContext)_localctx).variableType = dataType();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_ASSIG) {
				{
				setState(33);
				match(OP_ASSIG);
				setState(34);
				((QuestionContext)_localctx).assignment = expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeDeclarationIntegerContext extends DataTypeContext {
		public TerminalNode TYPE_INTEGER() { return getToken(QLParser.TYPE_INTEGER, 0); }
		public TypeDeclarationIntegerContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterTypeDeclarationInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitTypeDeclarationInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationDecimalContext extends DataTypeContext {
		public TerminalNode TYPE_DECIMAL() { return getToken(QLParser.TYPE_DECIMAL, 0); }
		public TypeDeclarationDecimalContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterTypeDeclarationDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitTypeDeclarationDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationDecimal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationStringContext extends DataTypeContext {
		public TerminalNode TYPE_STRING() { return getToken(QLParser.TYPE_STRING, 0); }
		public TypeDeclarationStringContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterTypeDeclarationString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitTypeDeclarationString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationBooleanContext extends DataTypeContext {
		public TerminalNode TYPE_BOOLEAN() { return getToken(QLParser.TYPE_BOOLEAN, 0); }
		public TypeDeclarationBooleanContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterTypeDeclarationBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitTypeDeclarationBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTypeDeclarationBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dataType);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_BOOLEAN:
				_localctx = new TypeDeclarationBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				match(TYPE_BOOLEAN);
				}
				break;
			case TYPE_STRING:
				_localctx = new TypeDeclarationStringContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(TYPE_STRING);
				}
				break;
			case TYPE_INTEGER:
				_localctx = new TypeDeclarationIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				match(TYPE_INTEGER);
				}
				break;
			case TYPE_DECIMAL:
				_localctx = new TypeDeclarationDecimalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(40);
				match(TYPE_DECIMAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public TerminalNode IF() { return getToken(QLParser.IF, 0); }
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(IF);
			setState(44);
			match(T__2);
			setState(45);
			((IfStatementContext)_localctx).condition = expression(0);
			setState(46);
			match(T__3);
			setState(47);
			match(BEGIN);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(48);
				statement();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(END);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(55);
				elseStatement();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(QLParser.ELSE, 0); }
		public TerminalNode BEGIN() { return getToken(QLParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(QLParser.END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_elseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ELSE);
			setState(59);
			match(BEGIN);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==STRING) {
				{
				{
				setState(60);
				statement();
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionParenthesisesContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionParenthesisesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionParenthesises(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionParenthesises(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionParenthesises(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionLessThanContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_LT() { return getToken(QLParser.OP_LT, 0); }
		public ExpressionComparisionLessThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionLessThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionLessThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionLessThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionNotEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_NEQ() { return getToken(QLParser.OP_NEQ, 0); }
		public ExpressionComparisionNotEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionNotEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionNotEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionNotEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_EQ() { return getToken(QLParser.OP_EQ, 0); }
		public ExpressionComparisionEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionSingleValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QLParser.DECIMAL, 0); }
		public TerminalNode BOOL_TRUE() { return getToken(QLParser.BOOL_TRUE, 0); }
		public TerminalNode BOOL_FALSE() { return getToken(QLParser.BOOL_FALSE, 0); }
		public ExpressionSingleValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionSingleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionSingleValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionSingleValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticMultiplicationContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_MULT() { return getToken(QLParser.OP_MULT, 0); }
		public ExpressionArithmeticMultiplicationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionArithmeticMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionArithmeticMultiplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticMultiplication(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionNegationContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public TerminalNode OP_NOT() { return getToken(QLParser.OP_NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionNegationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionLogicalOrContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_OR() { return getToken(QLParser.OP_OR, 0); }
		public ExpressionLogicalOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionLessEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_LE() { return getToken(QLParser.OP_LE, 0); }
		public ExpressionComparisionLessEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionLessEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionLessEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionLessEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionLogicalAndContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_AND() { return getToken(QLParser.OP_AND, 0); }
		public ExpressionLogicalAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticDivisionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_DIV() { return getToken(QLParser.OP_DIV, 0); }
		public ExpressionArithmeticDivisionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionArithmeticDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionArithmeticDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionGreaterEqualContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_GE() { return getToken(QLParser.OP_GE, 0); }
		public ExpressionComparisionGreaterEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionGreaterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionGreaterEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionGreaterEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionVariableReferenceContext extends ExpressionContext {
		public Token variableReference;
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public ExpressionVariableReferenceContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionVariableReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionVariableReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionVariableReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticMinusContext extends ExpressionContext {
		public ExpressionContext subExpression;
		public TerminalNode OP_MINUS() { return getToken(QLParser.OP_MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionArithmeticMinusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionArithmeticMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionArithmeticMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionComparisionGreaterThanContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_GT() { return getToken(QLParser.OP_GT, 0); }
		public ExpressionComparisionGreaterThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionComparisionGreaterThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionComparisionGreaterThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionComparisionGreaterThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticSubtractionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_MINUS() { return getToken(QLParser.OP_MINUS, 0); }
		public ExpressionArithmeticSubtractionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionArithmeticSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionArithmeticSubtraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionArithmeticAdditionContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token binaryOperator;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_PLUS() { return getToken(QLParser.OP_PLUS, 0); }
		public ExpressionArithmeticAdditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpressionArithmeticAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpressionArithmeticAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpressionArithmeticAddition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				_localctx = new ExpressionParenthesisesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(69);
				match(T__2);
				setState(70);
				((ExpressionParenthesisesContext)_localctx).subExpression = expression(0);
				setState(71);
				match(T__3);
				}
				break;
			case OP_NOT:
				{
				_localctx = new ExpressionNegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(OP_NOT);
				setState(74);
				((ExpressionNegationContext)_localctx).subExpression = expression(16);
				}
				break;
			case OP_MINUS:
				{
				_localctx = new ExpressionArithmeticMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75);
				match(OP_MINUS);
				setState(76);
				((ExpressionArithmeticMinusContext)_localctx).subExpression = expression(15);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new ExpressionVariableReferenceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				((ExpressionVariableReferenceContext)_localctx).variableReference = match(IDENTIFIER);
				}
				break;
			case BOOL_TRUE:
			case BOOL_FALSE:
			case INTEGER:
			case STRING:
			case DECIMAL:
				{
				_localctx = new ExpressionSingleValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				((ExpressionSingleValueContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_TRUE) | (1L << BOOL_FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << DECIMAL))) != 0)) ) {
					((ExpressionSingleValueContext)_localctx).value = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(117);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionArithmeticMultiplicationContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticMultiplicationContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(81);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(82);
						((ExpressionArithmeticMultiplicationContext)_localctx).binaryOperator = match(OP_MULT);
						setState(83);
						((ExpressionArithmeticMultiplicationContext)_localctx).rhs = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionArithmeticDivisionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticDivisionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(84);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(85);
						((ExpressionArithmeticDivisionContext)_localctx).binaryOperator = match(OP_DIV);
						setState(86);
						((ExpressionArithmeticDivisionContext)_localctx).rhs = expression(14);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionArithmeticAdditionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticAdditionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(87);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(88);
						((ExpressionArithmeticAdditionContext)_localctx).binaryOperator = match(OP_PLUS);
						setState(89);
						((ExpressionArithmeticAdditionContext)_localctx).rhs = expression(13);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionArithmeticSubtractionContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionArithmeticSubtractionContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(90);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(91);
						((ExpressionArithmeticSubtractionContext)_localctx).binaryOperator = match(OP_MINUS);
						setState(92);
						((ExpressionArithmeticSubtractionContext)_localctx).rhs = expression(12);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionComparisionGreaterThanContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionGreaterThanContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(93);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(94);
						((ExpressionComparisionGreaterThanContext)_localctx).binaryOperator = match(OP_GT);
						setState(95);
						((ExpressionComparisionGreaterThanContext)_localctx).rhs = expression(11);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionComparisionGreaterEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionGreaterEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(96);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(97);
						((ExpressionComparisionGreaterEqualContext)_localctx).binaryOperator = match(OP_GE);
						setState(98);
						((ExpressionComparisionGreaterEqualContext)_localctx).rhs = expression(10);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionComparisionLessThanContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionLessThanContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(99);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(100);
						((ExpressionComparisionLessThanContext)_localctx).binaryOperator = match(OP_LT);
						setState(101);
						((ExpressionComparisionLessThanContext)_localctx).rhs = expression(9);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionComparisionLessEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionLessEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(102);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(103);
						((ExpressionComparisionLessEqualContext)_localctx).binaryOperator = match(OP_LE);
						setState(104);
						((ExpressionComparisionLessEqualContext)_localctx).rhs = expression(8);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionComparisionEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(105);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(106);
						((ExpressionComparisionEqualContext)_localctx).binaryOperator = match(OP_EQ);
						setState(107);
						((ExpressionComparisionEqualContext)_localctx).rhs = expression(7);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionComparisionNotEqualContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionComparisionNotEqualContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(108);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(109);
						((ExpressionComparisionNotEqualContext)_localctx).binaryOperator = match(OP_NEQ);
						setState(110);
						((ExpressionComparisionNotEqualContext)_localctx).rhs = expression(6);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionLogicalAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionLogicalAndContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(111);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(112);
						((ExpressionLogicalAndContext)_localctx).binaryOperator = match(OP_AND);
						setState(113);
						((ExpressionLogicalAndContext)_localctx).rhs = expression(5);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionLogicalOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionLogicalOrContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(114);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(115);
						((ExpressionLogicalOrContext)_localctx).binaryOperator = match(OP_OR);
						setState(116);
						((ExpressionLogicalOrContext)_localctx).rhs = expression(4);
						}
						break;
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 7);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$}\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2\f"+
		"\2\16\2\30\13\2\3\2\3\2\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"&\n\4\3\5\3\5\3\5\3\5\5\5,\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6\64\n\6\f\6"+
		"\16\6\67\13\6\3\6\3\6\5\6;\n\6\3\7\3\7\3\7\7\7@\n\7\f\7\16\7C\13\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bR\n\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bx\n"+
		"\b\f\b\16\b{\13\b\3\b\2\3\16\t\2\4\6\b\n\f\16\2\3\4\2\35\36\"$\2\u008e"+
		"\2\20\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b+\3\2\2\2\n-\3\2\2\2\f<\3\2\2"+
		"\2\16Q\3\2\2\2\20\21\7\3\2\2\21\22\7!\2\2\22\26\7\27\2\2\23\25\5\4\3\2"+
		"\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2"+
		"\30\26\3\2\2\2\31\32\7\30\2\2\32\3\3\2\2\2\33\36\5\6\4\2\34\36\5\n\6\2"+
		"\35\33\3\2\2\2\35\34\3\2\2\2\36\5\3\2\2\2\37 \7#\2\2 !\7!\2\2!\"\7\4\2"+
		"\2\"%\5\b\5\2#$\7\n\2\2$&\5\16\b\2%#\3\2\2\2%&\3\2\2\2&\7\3\2\2\2\',\7"+
		"\31\2\2(,\7\32\2\2),\7\33\2\2*,\7\34\2\2+\'\3\2\2\2+(\3\2\2\2+)\3\2\2"+
		"\2+*\3\2\2\2,\t\3\2\2\2-.\7\25\2\2./\7\5\2\2/\60\5\16\b\2\60\61\7\6\2"+
		"\2\61\65\7\27\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2"+
		"\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\28:\7\30\2\29;\5\f\7\2:9\3"+
		"\2\2\2:;\3\2\2\2;\13\3\2\2\2<=\7\26\2\2=A\7\27\2\2>@\5\4\3\2?>\3\2\2\2"+
		"@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\30\2\2E\r\3\2\2"+
		"\2FG\b\b\1\2GH\7\5\2\2HI\5\16\b\2IJ\7\6\2\2JR\3\2\2\2KL\7\t\2\2LR\5\16"+
		"\b\22MN\7\16\2\2NR\5\16\b\21OR\7!\2\2PR\t\2\2\2QF\3\2\2\2QK\3\2\2\2QM"+
		"\3\2\2\2QO\3\2\2\2QP\3\2\2\2Ry\3\2\2\2ST\f\20\2\2TU\7\13\2\2Ux\5\16\b"+
		"\21VW\f\17\2\2WX\7\f\2\2Xx\5\16\b\20YZ\f\16\2\2Z[\7\r\2\2[x\5\16\b\17"+
		"\\]\f\r\2\2]^\7\16\2\2^x\5\16\b\16_`\f\f\2\2`a\7\17\2\2ax\5\16\b\rbc\f"+
		"\13\2\2cd\7\20\2\2dx\5\16\b\fef\f\n\2\2fg\7\21\2\2gx\5\16\b\13hi\f\t\2"+
		"\2ij\7\22\2\2jx\5\16\b\nkl\f\b\2\2lm\7\23\2\2mx\5\16\b\tno\f\7\2\2op\7"+
		"\24\2\2px\5\16\b\bqr\f\6\2\2rs\7\7\2\2sx\5\16\b\7tu\f\5\2\2uv\7\b\2\2"+
		"vx\5\16\b\6wS\3\2\2\2wV\3\2\2\2wY\3\2\2\2w\\\3\2\2\2w_\3\2\2\2wb\3\2\2"+
		"\2we\3\2\2\2wh\3\2\2\2wk\3\2\2\2wn\3\2\2\2wq\3\2\2\2wt\3\2\2\2x{\3\2\2"+
		"\2yw\3\2\2\2yz\3\2\2\2z\17\3\2\2\2{y\3\2\2\2\f\26\35%+\65:AQwy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}