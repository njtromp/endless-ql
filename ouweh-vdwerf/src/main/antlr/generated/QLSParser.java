// Generated from QLS.g4 by ANTLR 4.7.1

package antlr.generated;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, ID=19, STRING=20, COLOR=21, NUMBER=22, WHITESPACE=23, MULTI_LINE_COMMENT=24, 
		SINGLE_LINE_COMMENT=25, OPEN_BRACKET=26, CLOSE_BRACKET=27, OPEN_PARENTH=28, 
		CLOSE_PARENTH=29;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_section = 2, RULE_segment = 3, 
		RULE_defaultStatement = 4, RULE_question = 5, RULE_widget = 6, RULE_widgetType = 7, 
		RULE_type = 8, RULE_style = 9, RULE_styleProperty = 10, RULE_value = 11;
	public static final String[] ruleNames = {
		"stylesheet", "page", "section", "segment", "defaultStatement", "question", 
		"widget", "widgetType", "type", "style", "styleProperty", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'page'", "'section'", "'default'", "'question'", 
		"'widget'", "'radio'", "','", "'checkbox'", "'dropdown'", "'slider'", 
		"'text'", "'spinbox'", "'boolean'", "'integer'", "'money'", "'string'", 
		"':'", null, null, null, null, null, null, null, "'{'", "'}'", "'('", 
		"')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "ID", "STRING", "COLOR", "NUMBER", 
		"WHITESPACE", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "OPEN_BRACKET", 
		"CLOSE_BRACKET", "OPEN_PARENTH", "CLOSE_PARENTH"
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
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StylesheetContext extends ParserRuleContext {
		public Token id;
		public TerminalNode OPEN_BRACKET() { return getToken(QLSParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLSParser.CLOSE_BRACKET, 0); }
		public TerminalNode ID() { return getToken(QLSParser.ID, 0); }
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__0);
			setState(25);
			((StylesheetContext)_localctx).id = match(ID);
			setState(26);
			match(OPEN_BRACKET);
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				page();
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
			setState(32);
			match(CLOSE_BRACKET);
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

	public static class PageContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLSParser.ID, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(QLSParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLSParser.CLOSE_BRACKET, 0); }
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public List<DefaultStatementContext> defaultStatement() {
			return getRuleContexts(DefaultStatementContext.class);
		}
		public DefaultStatementContext defaultStatement(int i) {
			return getRuleContext(DefaultStatementContext.class,i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__1);
			setState(35);
			match(ID);
			setState(36);
			match(OPEN_BRACKET);
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				segment();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__4 );
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(42);
				defaultStatement();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(CLOSE_BRACKET);
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

	public static class SectionContext extends ParserRuleContext {
		public Token id;
		public TerminalNode OPEN_BRACKET() { return getToken(QLSParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLSParser.CLOSE_BRACKET, 0); }
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public List<DefaultStatementContext> defaultStatement() {
			return getRuleContexts(DefaultStatementContext.class);
		}
		public DefaultStatementContext defaultStatement(int i) {
			return getRuleContext(DefaultStatementContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__2);
			setState(51);
			((SectionContext)_localctx).id = match(STRING);
			setState(52);
			match(OPEN_BRACKET);
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				segment();
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__4 );
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(58);
				defaultStatement();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(CLOSE_BRACKET);
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

	public static class SegmentContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSegment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_segment);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				question();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				section();
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

	public static class DefaultStatementContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public DefaultStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultStatementContext defaultStatement() throws RecognitionException {
		DefaultStatementContext _localctx = new DefaultStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defaultStatement);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__3);
				setState(71);
				type();
				setState(72);
				widget();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__3);
				setState(75);
				type();
				setState(76);
				style();
				}
				break;
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
		public Token id;
		public TerminalNode ID() { return getToken(QLSParser.ID, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		int _la;
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(T__4);
				setState(81);
				((QuestionContext)_localctx).id = match(ID);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(82);
					widget();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(T__4);
				setState(86);
				((QuestionContext)_localctx).id = match(ID);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_BRACKET) {
					{
					setState(87);
					style();
					}
				}

				}
				break;
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

	public static class WidgetContext extends ParserRuleContext {
		public WidgetTypeContext widgetType() {
			return getRuleContext(WidgetTypeContext.class,0);
		}
		public WidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_widget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__5);
			setState(93);
			widgetType();
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

	public static class WidgetTypeContext extends ParserRuleContext {
		public WidgetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetType; }
	 
		public WidgetTypeContext() { }
		public void copyFrom(WidgetTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SpinboxTypeContext extends WidgetTypeContext {
		public SpinboxTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSpinboxType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSpinboxType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSpinboxType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckboxTypeContext extends WidgetTypeContext {
		public Token yes;
		public TerminalNode OPEN_PARENTH() { return getToken(QLSParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLSParser.CLOSE_PARENTH, 0); }
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public CheckboxTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterCheckboxType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitCheckboxType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitCheckboxType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SliderTypeContext extends WidgetTypeContext {
		public Token start;
		public Token end;
		public Token step;
		public TerminalNode OPEN_PARENTH() { return getToken(QLSParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLSParser.CLOSE_PARENTH, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(QLSParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(QLSParser.NUMBER, i);
		}
		public SliderTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSliderType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSliderType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSliderType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextTypeContext extends WidgetTypeContext {
		public TextTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTextType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTextType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTextType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RadioTypeContext extends WidgetTypeContext {
		public Token yes;
		public Token no;
		public TerminalNode OPEN_PARENTH() { return getToken(QLSParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLSParser.CLOSE_PARENTH, 0); }
		public List<TerminalNode> STRING() { return getTokens(QLSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QLSParser.STRING, i);
		}
		public RadioTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterRadioType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitRadioType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitRadioType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropdownTypeContext extends WidgetTypeContext {
		public Token yes;
		public Token no;
		public TerminalNode OPEN_PARENTH() { return getToken(QLSParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLSParser.CLOSE_PARENTH, 0); }
		public List<TerminalNode> STRING() { return getTokens(QLSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QLSParser.STRING, i);
		}
		public DropdownTypeContext(WidgetTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDropdownType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDropdownType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDropdownType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetTypeContext widgetType() throws RecognitionException {
		WidgetTypeContext _localctx = new WidgetTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_widgetType);
		int _la;
		try {
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				_localctx = new RadioTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(T__6);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTH) {
					{
					setState(96);
					match(OPEN_PARENTH);
					setState(97);
					((RadioTypeContext)_localctx).yes = match(STRING);
					setState(98);
					match(T__7);
					setState(99);
					((RadioTypeContext)_localctx).no = match(STRING);
					setState(100);
					match(CLOSE_PARENTH);
					}
				}

				}
				break;
			case T__8:
				_localctx = new CheckboxTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T__8);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTH) {
					{
					setState(104);
					match(OPEN_PARENTH);
					setState(105);
					((CheckboxTypeContext)_localctx).yes = match(STRING);
					setState(106);
					match(CLOSE_PARENTH);
					}
				}

				}
				break;
			case T__9:
				_localctx = new DropdownTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				match(T__9);
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTH) {
					{
					setState(110);
					match(OPEN_PARENTH);
					setState(111);
					((DropdownTypeContext)_localctx).yes = match(STRING);
					setState(112);
					match(T__7);
					setState(113);
					((DropdownTypeContext)_localctx).no = match(STRING);
					setState(114);
					match(CLOSE_PARENTH);
					}
				}

				}
				break;
			case T__10:
				_localctx = new SliderTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				match(T__10);
				setState(118);
				match(OPEN_PARENTH);
				setState(119);
				((SliderTypeContext)_localctx).start = match(NUMBER);
				setState(120);
				match(T__7);
				setState(121);
				((SliderTypeContext)_localctx).end = match(NUMBER);
				setState(122);
				match(T__7);
				setState(123);
				((SliderTypeContext)_localctx).step = match(NUMBER);
				setState(124);
				match(CLOSE_PARENTH);
				}
				break;
			case T__11:
				_localctx = new TextTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				match(T__11);
				}
				break;
			case T__12:
				_localctx = new SpinboxTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(126);
				match(T__12);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterBooleanType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitBooleanType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerTypeContext extends TypeContext {
		public IntegerTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterIntegerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitIntegerType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitIntegerType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyTypeContext extends TypeContext {
		public MoneyTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterMoneyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitMoneyType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitMoneyType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStringType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(T__13);
				}
				break;
			case T__14:
				_localctx = new IntegerTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(T__14);
				}
				break;
			case T__15:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				match(T__15);
				}
				break;
			case T__16:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				match(T__16);
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

	public static class StyleContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(QLSParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLSParser.CLOSE_BRACKET, 0); }
		public List<StylePropertyContext> styleProperty() {
			return getRuleContexts(StylePropertyContext.class);
		}
		public StylePropertyContext styleProperty(int i) {
			return getRuleContext(StylePropertyContext.class,i);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public StyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_style; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleContext style() throws RecognitionException {
		StyleContext _localctx = new StyleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_style);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(OPEN_BRACKET);
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136);
				styleProperty();
				}
				}
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(141);
				widget();
				}
			}

			setState(144);
			match(CLOSE_BRACKET);
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

	public static class StylePropertyContext extends ParserRuleContext {
		public Token property;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(QLSParser.ID, 0); }
		public StylePropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStyleProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStyleProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStyleProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylePropertyContext styleProperty() throws RecognitionException {
		StylePropertyContext _localctx = new StylePropertyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_styleProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			((StylePropertyContext)_localctx).property = match(ID);
			setState(147);
			match(T__17);
			setState(148);
			value();
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(QLSParser.NUMBER, 0); }
		public TerminalNode COLOR() { return getToken(QLSParser.COLOR, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << COLOR) | (1L << NUMBER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u009b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\6\2\37\n\2\r\2\16\2 \3\2\3\2\3\3"+
		"\3\3\3\3\3\3\6\3)\n\3\r\3\16\3*\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\6\49\n\4\r\4\16\4:\3\4\7\4>\n\4\f\4\16\4A\13\4\3\4\3"+
		"\4\3\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Q\n\6\3\7\3\7\3"+
		"\7\5\7V\n\7\3\7\3\7\3\7\5\7[\n\7\5\7]\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\th\n\t\3\t\3\t\3\t\3\t\5\tn\n\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"v\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0082\n\t\3\n\3\n\3"+
		"\n\3\n\5\n\u0088\n\n\3\13\3\13\6\13\u008c\n\13\r\13\16\13\u008d\3\13\5"+
		"\13\u0091\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\2\3\3\2\26\30\2\u00a5\2\32\3\2\2\2\4$\3\2\2\2\6\64"+
		"\3\2\2\2\bF\3\2\2\2\nP\3\2\2\2\f\\\3\2\2\2\16^\3\2\2\2\20\u0081\3\2\2"+
		"\2\22\u0087\3\2\2\2\24\u0089\3\2\2\2\26\u0094\3\2\2\2\30\u0098\3\2\2\2"+
		"\32\33\7\3\2\2\33\34\7\25\2\2\34\36\7\34\2\2\35\37\5\4\3\2\36\35\3\2\2"+
		"\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\35\2\2#\3\3\2\2\2"+
		"$%\7\4\2\2%&\7\25\2\2&(\7\34\2\2\')\5\b\5\2(\'\3\2\2\2)*\3\2\2\2*(\3\2"+
		"\2\2*+\3\2\2\2+/\3\2\2\2,.\5\n\6\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60"+
		"\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\35\2\2\63\5\3\2\2\2\64\65\7"+
		"\5\2\2\65\66\7\26\2\2\668\7\34\2\2\679\5\b\5\28\67\3\2\2\29:\3\2\2\2:"+
		"8\3\2\2\2:;\3\2\2\2;?\3\2\2\2<>\5\n\6\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2"+
		"?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\35\2\2C\7\3\2\2\2DG\5\f\7\2EG\5\6\4"+
		"\2FD\3\2\2\2FE\3\2\2\2G\t\3\2\2\2HI\7\6\2\2IJ\5\22\n\2JK\5\16\b\2KQ\3"+
		"\2\2\2LM\7\6\2\2MN\5\22\n\2NO\5\24\13\2OQ\3\2\2\2PH\3\2\2\2PL\3\2\2\2"+
		"Q\13\3\2\2\2RS\7\7\2\2SU\7\25\2\2TV\5\16\b\2UT\3\2\2\2UV\3\2\2\2V]\3\2"+
		"\2\2WX\7\7\2\2XZ\7\25\2\2Y[\5\24\13\2ZY\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\"+
		"R\3\2\2\2\\W\3\2\2\2]\r\3\2\2\2^_\7\b\2\2_`\5\20\t\2`\17\3\2\2\2ag\7\t"+
		"\2\2bc\7\36\2\2cd\7\26\2\2de\7\n\2\2ef\7\26\2\2fh\7\37\2\2gb\3\2\2\2g"+
		"h\3\2\2\2h\u0082\3\2\2\2im\7\13\2\2jk\7\36\2\2kl\7\26\2\2ln\7\37\2\2m"+
		"j\3\2\2\2mn\3\2\2\2n\u0082\3\2\2\2ou\7\f\2\2pq\7\36\2\2qr\7\26\2\2rs\7"+
		"\n\2\2st\7\26\2\2tv\7\37\2\2up\3\2\2\2uv\3\2\2\2v\u0082\3\2\2\2wx\7\r"+
		"\2\2xy\7\36\2\2yz\7\30\2\2z{\7\n\2\2{|\7\30\2\2|}\7\n\2\2}~\7\30\2\2~"+
		"\u0082\7\37\2\2\177\u0082\7\16\2\2\u0080\u0082\7\17\2\2\u0081a\3\2\2\2"+
		"\u0081i\3\2\2\2\u0081o\3\2\2\2\u0081w\3\2\2\2\u0081\177\3\2\2\2\u0081"+
		"\u0080\3\2\2\2\u0082\21\3\2\2\2\u0083\u0088\7\20\2\2\u0084\u0088\7\21"+
		"\2\2\u0085\u0088\7\22\2\2\u0086\u0088\7\23\2\2\u0087\u0083\3\2\2\2\u0087"+
		"\u0084\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0086\3\2\2\2\u0088\23\3\2\2"+
		"\2\u0089\u008b\7\34\2\2\u008a\u008c\5\26\f\2\u008b\u008a\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2"+
		"\2\2\u008f\u0091\5\16\b\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\7\35\2\2\u0093\25\3\2\2\2\u0094\u0095\7\25"+
		"\2\2\u0095\u0096\7\24\2\2\u0096\u0097\5\30\r\2\u0097\27\3\2\2\2\u0098"+
		"\u0099\t\2\2\2\u0099\31\3\2\2\2\23 */:?FPUZ\\gmu\u0081\u0087\u008d\u0090";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}