
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;
import nl.khonraad.visitors.InterpretingVisitor;
import utils.AbstractParserFactory;

public class Test_Interpretor {

	@Test
	public void test_Interpretor() throws Exception {

		InterpretingVisitor interpretingVisitor = new InterpretingVisitor();

		String testData =
				"form Box1HouseOwning {																			"+
				"   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"+
				"   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"+
				"   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"+
				"																								"+
				"	if (hasSoldHouse) {																			"+
				"		sellingPrice: \"Price the house was sold for:\" money										"+
				"		privateDebt: \"Private debts for the sold house:\" money									"+
				"  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01 )				"+
				"  		testDate: \"Testdate:\" date (01/01/1970 + 3 )				"+
				"  		testString: \"testString:\" string (\"abc\"  + \"ABC\")				"+
				"  	}																							"+
				"}																								";

		ParseTree parseTree = AbstractParserFactory.parseDataForTest( testData ).form();

		interpretingVisitor.visit( parseTree );

		// simulate answers given

		interpretingVisitor.answerableQuestions.get( "hasSoldHouse" ).parseThenSetValue( "True" );

		interpretingVisitor.visit( parseTree );

		interpretingVisitor.answerableQuestions.get( "sellingPrice" ).parseThenSetValue( "100.03" );
		interpretingVisitor.answerableQuestions.get( "privateDebt" ).parseThenSetValue( "25.00" );

		interpretingVisitor.visit( parseTree );

		assertEquals( "Calculated answer", new Value( Type.Money, "75.02" ),
				interpretingVisitor.computedQuestions.get( "valueResidue" ).getValue() );
		assertEquals( "Calculated answer", new Value( Type.Date, "04/01/1970" ),
				interpretingVisitor.computedQuestions.get( "testDate" ).getValue() );
		assertEquals( "Calculated answer", new Value( Type.String, "abcABC" ),
				interpretingVisitor.computedQuestions.get( "testString" ).getValue() );
	}

	@Test
	public void test_Operators() throws Exception {

		InterpretingVisitor interpretingVisitor = new InterpretingVisitor();

		String s = "";

		s = "form x { x: \"x:\" integer (4+5)   }";
		ParseTree parseTree = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree );
		assertEquals( "x", new Value( Type.Integer, "9" ),
				interpretingVisitor.computedQuestions.get( "x" ).getValue() );

		s = "form x { x: \"x:\" boolean (True || False)   }";
		ParseTree parseTree2 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree2 );
		assertEquals( "x", new Value( Type.Boolean, "True" ),
				interpretingVisitor.computedQuestions.get( "x" ).getValue() );

		s = "form x { x: \"x:\" boolean (True == False)   }";
		ParseTree parseTree3 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree3 );
		assertEquals( "x", new Value( Type.Boolean, "False" ),
				interpretingVisitor.computedQuestions.get( "x" ).getValue() );

		s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1) )   }";
		ParseTree parseTree4 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree4 );
		assertEquals( "x", new Value( Type.Boolean, "True" ),
				interpretingVisitor.computedQuestions.get( "x" ).getValue() );

	}

}