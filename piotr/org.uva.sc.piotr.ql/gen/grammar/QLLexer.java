// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/grammar/QL.g4 by ANTLR 4.7
package grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "OP_AND", "OP_OR", "OP_NOT", "OP_ASSIG", 
		"OP_MULT", "OP_DIV", "OP_PLUS", "OP_MINUS", "OP_GT", "OP_GE", "OP_LT", 
		"OP_LE", "OP_EQ", "OP_NEQ", "IF", "ELSE", "BEGIN", "END", "TYPE_BOOLEAN", 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_DECIMAL", "BOOL_TRUE", "BOOL_FALSE", 
		"WS", "COMMENT", "IDENTIFIER", "INTEGER", "STRING", "DECIMAL"
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u00ed\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00a4\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\5\34\u00ae\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\5\35\u00ba\n\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37"+
		"\u00c4\n\37\f\37\16\37\u00c7\13\37\3\37\3\37\3\37\3\37\3\37\3 \3 \7 \u00d0"+
		"\n \f \16 \u00d3\13 \3!\6!\u00d6\n!\r!\16!\u00d7\3\"\3\"\7\"\u00dc\n\""+
		"\f\"\16\"\u00df\13\"\3\"\3\"\3#\6#\u00e4\n#\r#\16#\u00e5\3#\3#\6#\u00ea"+
		"\n#\r#\16#\u00eb\4\u00c5\u00dd\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$\3\2\6\5\2\13\f\17\17\"\"\4"+
		"\2C\\c|\6\2\62;C\\aac|\3\2\62;\2\u00f5\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5L\3\2\2\2\7N\3\2\2\2\tP\3\2\2"+
		"\2\13R\3\2\2\2\rU\3\2\2\2\17X\3\2\2\2\21Z\3\2\2\2\23\\\3\2\2\2\25^\3\2"+
		"\2\2\27`\3\2\2\2\31b\3\2\2\2\33d\3\2\2\2\35f\3\2\2\2\37i\3\2\2\2!k\3\2"+
		"\2\2#n\3\2\2\2%q\3\2\2\2\'t\3\2\2\2)w\3\2\2\2+|\3\2\2\2-~\3\2\2\2/\u0080"+
		"\3\2\2\2\61\u0088\3\2\2\2\63\u008f\3\2\2\2\65\u00a3\3\2\2\2\67\u00ad\3"+
		"\2\2\29\u00b9\3\2\2\2;\u00bb\3\2\2\2=\u00bf\3\2\2\2?\u00cd\3\2\2\2A\u00d5"+
		"\3\2\2\2C\u00d9\3\2\2\2E\u00e3\3\2\2\2GH\7h\2\2HI\7q\2\2IJ\7t\2\2JK\7"+
		"o\2\2K\4\3\2\2\2LM\7<\2\2M\6\3\2\2\2NO\7*\2\2O\b\3\2\2\2PQ\7+\2\2Q\n\3"+
		"\2\2\2RS\7(\2\2ST\7(\2\2T\f\3\2\2\2UV\7~\2\2VW\7~\2\2W\16\3\2\2\2XY\7"+
		"#\2\2Y\20\3\2\2\2Z[\7?\2\2[\22\3\2\2\2\\]\7,\2\2]\24\3\2\2\2^_\7\61\2"+
		"\2_\26\3\2\2\2`a\7-\2\2a\30\3\2\2\2bc\7/\2\2c\32\3\2\2\2de\7@\2\2e\34"+
		"\3\2\2\2fg\7@\2\2gh\7?\2\2h\36\3\2\2\2ij\7>\2\2j \3\2\2\2kl\7>\2\2lm\7"+
		"?\2\2m\"\3\2\2\2no\7?\2\2op\7?\2\2p$\3\2\2\2qr\7#\2\2rs\7?\2\2s&\3\2\2"+
		"\2tu\7k\2\2uv\7h\2\2v(\3\2\2\2wx\7g\2\2xy\7n\2\2yz\7u\2\2z{\7g\2\2{*\3"+
		"\2\2\2|}\7}\2\2},\3\2\2\2~\177\7\177\2\2\177.\3\2\2\2\u0080\u0081\7d\2"+
		"\2\u0081\u0082\7q\2\2\u0082\u0083\7q\2\2\u0083\u0084\7n\2\2\u0084\u0085"+
		"\7g\2\2\u0085\u0086\7c\2\2\u0086\u0087\7p\2\2\u0087\60\3\2\2\2\u0088\u0089"+
		"\7u\2\2\u0089\u008a\7v\2\2\u008a\u008b\7t\2\2\u008b\u008c\7k\2\2\u008c"+
		"\u008d\7p\2\2\u008d\u008e\7i\2\2\u008e\62\3\2\2\2\u008f\u0090\7k\2\2\u0090"+
		"\u0091\7p\2\2\u0091\u0092\7v\2\2\u0092\u0093\7g\2\2\u0093\u0094\7i\2\2"+
		"\u0094\u0095\7g\2\2\u0095\u0096\7t\2\2\u0096\64\3\2\2\2\u0097\u0098\7"+
		"f\2\2\u0098\u0099\7g\2\2\u0099\u009a\7e\2\2\u009a\u009b\7k\2\2\u009b\u009c"+
		"\7o\2\2\u009c\u009d\7c\2\2\u009d\u00a4\7n\2\2\u009e\u009f\7o\2\2\u009f"+
		"\u00a0\7q\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a4\7{\2\2"+
		"\u00a3\u0097\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\66\3\2\2\2\u00a5\u00a6"+
		"\7v\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8\7w\2\2\u00a8\u00ae\7g\2\2\u00a9"+
		"\u00aa\7V\2\2\u00aa\u00ab\7T\2\2\u00ab\u00ac\7W\2\2\u00ac\u00ae\7G\2\2"+
		"\u00ad\u00a5\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ae8\3\2\2\2\u00af\u00b0\7"+
		"h\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7u\2\2\u00b3\u00ba"+
		"\7g\2\2\u00b4\u00b5\7H\2\2\u00b5\u00b6\7C\2\2\u00b6\u00b7\7N\2\2\u00b7"+
		"\u00b8\7U\2\2\u00b8\u00ba\7G\2\2\u00b9\u00af\3\2\2\2\u00b9\u00b4\3\2\2"+
		"\2\u00ba:\3\2\2\2\u00bb\u00bc\t\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be"+
		"\b\36\2\2\u00be<\3\2\2\2\u00bf\u00c0\7\61\2\2\u00c0\u00c1\7,\2\2\u00c1"+
		"\u00c5\3\2\2\2\u00c2\u00c4\13\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c7\3"+
		"\2\2\2\u00c5\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\u00c9\7,\2\2\u00c9\u00ca\7\61\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00cc\b\37\2\2\u00cc>\3\2\2\2\u00cd\u00d1\t\3\2\2\u00ce\u00d0"+
		"\t\4\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2@\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d6\4\62;\2"+
		"\u00d5\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8"+
		"\3\2\2\2\u00d8B\3\2\2\2\u00d9\u00dd\7$\2\2\u00da\u00dc\13\2\2\2\u00db"+
		"\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00de\3\2\2\2\u00dd\u00db\3\2"+
		"\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e1\7$\2\2\u00e1"+
		"D\3\2\2\2\u00e2\u00e4\t\5\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9"+
		"\7\60\2\2\u00e8\u00ea\t\5\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2"+
		"\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ecF\3\2\2\2\f\2\u00a3\u00ad"+
		"\u00b9\u00c5\u00d1\u00d7\u00dd\u00e5\u00eb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}