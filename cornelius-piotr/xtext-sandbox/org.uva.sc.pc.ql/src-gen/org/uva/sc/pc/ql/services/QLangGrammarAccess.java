/*
 * generated by Xtext 2.12.0
 */
package org.uva.sc.pc.ql.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class QLangGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.uva.sc.pc.ql.QLang.Model");
		private final Assignment cFormsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cFormsFormParserRuleCall_0 = (RuleCall)cFormsAssignment.eContents().get(0);
		
		//Model:
		//	forms+=Form*;
		@Override public ParserRule getRule() { return rule; }
		
		//forms+=Form*
		public Assignment getFormsAssignment() { return cFormsAssignment; }
		
		//Form
		public RuleCall getFormsFormParserRuleCall_0() { return cFormsFormParserRuleCall_0; }
	}
	public class FormElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.uva.sc.pc.ql.QLang.Form");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFormKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cQuestionsAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cQuestionsQuestionParserRuleCall_3_0 = (RuleCall)cQuestionsAssignment_3.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//Form:
		//	'form' name=ID '{'
		//	questions+=Question*
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//'form' name=ID '{' questions+=Question* '}'
		public Group getGroup() { return cGroup; }
		
		//'form'
		public Keyword getFormKeyword_0() { return cFormKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_2() { return cLeftCurlyBracketKeyword_2; }
		
		//questions+=Question*
		public Assignment getQuestionsAssignment_3() { return cQuestionsAssignment_3; }
		
		//Question
		public RuleCall getQuestionsQuestionParserRuleCall_3_0() { return cQuestionsQuestionParserRuleCall_3_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_4() { return cRightCurlyBracketKeyword_4; }
	}
	public class QuestionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.uva.sc.pc.ql.QLang.Question");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cColonKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cLabelAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cLabelSTRINGTerminalRuleCall_2_0 = (RuleCall)cLabelAssignment_2.eContents().get(0);
		private final Assignment cTypeAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cTypeQuestionTypeParserRuleCall_3_0 = (RuleCall)cTypeAssignment_3.eContents().get(0);
		
		//Question:
		//	name=ID ':' label=STRING type=QuestionType;
		@Override public ParserRule getRule() { return rule; }
		
		//name=ID ':' label=STRING type=QuestionType
		public Group getGroup() { return cGroup; }
		
		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }
		
		//':'
		public Keyword getColonKeyword_1() { return cColonKeyword_1; }
		
		//label=STRING
		public Assignment getLabelAssignment_2() { return cLabelAssignment_2; }
		
		//STRING
		public RuleCall getLabelSTRINGTerminalRuleCall_2_0() { return cLabelSTRINGTerminalRuleCall_2_0; }
		
		//type=QuestionType
		public Assignment getTypeAssignment_3() { return cTypeAssignment_3; }
		
		//QuestionType
		public RuleCall getTypeQuestionTypeParserRuleCall_3_0() { return cTypeQuestionTypeParserRuleCall_3_0; }
	}
	public class QuestionTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.uva.sc.pc.ql.QLang.QuestionType");
		private final RuleCall cBoolTypeParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//QuestionType:
		//	BoolType;
		@Override public ParserRule getRule() { return rule; }
		
		//BoolType
		public RuleCall getBoolTypeParserRuleCall() { return cBoolTypeParserRuleCall; }
	}
	public class BoolTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.uva.sc.pc.ql.QLang.BoolType");
		private final Keyword cBooleanKeyword = (Keyword)rule.eContents().get(1);
		
		//BoolType:
		//	'boolean';
		@Override public ParserRule getRule() { return rule; }
		
		//'boolean'
		public Keyword getBooleanKeyword() { return cBooleanKeyword; }
	}
	
	
	private final ModelElements pModel;
	private final FormElements pForm;
	private final QuestionElements pQuestion;
	private final QuestionTypeElements pQuestionType;
	private final BoolTypeElements pBoolType;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public QLangGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pModel = new ModelElements();
		this.pForm = new FormElements();
		this.pQuestion = new QuestionElements();
		this.pQuestionType = new QuestionTypeElements();
		this.pBoolType = new BoolTypeElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.uva.sc.pc.ql.QLang".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Model:
	//	forms+=Form*;
	public ModelElements getModelAccess() {
		return pModel;
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}
	
	//Form:
	//	'form' name=ID '{'
	//	questions+=Question*
	//	'}';
	public FormElements getFormAccess() {
		return pForm;
	}
	
	public ParserRule getFormRule() {
		return getFormAccess().getRule();
	}
	
	//Question:
	//	name=ID ':' label=STRING type=QuestionType;
	public QuestionElements getQuestionAccess() {
		return pQuestion;
	}
	
	public ParserRule getQuestionRule() {
		return getQuestionAccess().getRule();
	}
	
	//QuestionType:
	//	BoolType;
	public QuestionTypeElements getQuestionTypeAccess() {
		return pQuestionType;
	}
	
	public ParserRule getQuestionTypeRule() {
		return getQuestionTypeAccess().getRule();
	}
	
	//BoolType:
	//	'boolean';
	public BoolTypeElements getBoolTypeAccess() {
		return pBoolType;
	}
	
	public ParserRule getBoolTypeRule() {
		return getBoolTypeAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}