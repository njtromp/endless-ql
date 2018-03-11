
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.booleans;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;
import static org.quicktheories.generators.SourceDSL.bigDecimals;

import java.math.BigDecimal;

import org.junit.Test;

import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;

public class TestValue {

	Value valueOf( boolean i ) {
		return new Value( Type.Boolean, i ? "True" : "False" );
	}

	Value iValue( int i ) {
		return new Value( Type.Integer, Integer.toString( i ) );
	}

	Value sValue( String s ) {
		return new Value( Type.String, s );
	}

	Value moneyValue( BigDecimal s ) {
		return new Value( Type.Money, s.toString() );
	}

	@Test
	public void boolean_and_boolean() {
		qt().forAll( booleans().all(), booleans().all() )
				.check( ( φ, ψ ) -> valueOf( φ ).apply( "&&", valueOf( ψ ) ).equals( valueOf( φ && ψ ) ) );
	}

	@Test
	public void boolean_or_boolean() {
		qt().forAll( booleans().all(), booleans().all() )
				.check( ( φ, ψ ) -> valueOf( φ ).apply( "||", valueOf( ψ ) ).equals( valueOf( φ || ψ ) ) );
	}

	@Test
	public void string_plus_string() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ),
				strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ) )
				.check( ( s1, s2 ) -> sValue( s1 ).apply( "+", sValue( s2 ) ).equals( sValue( s1 + s2 ) ) );
	}

	@Test
	public void string_plus_integer() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ), integers().all() ).check(
				( s, n ) -> sValue( s ).apply( "+", iValue( n ) ).equals( sValue( s + Integer.toString( n ) ) ) );
	}

	@Test
	public void string_plus_money() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ),
				bigDecimals().ofBytes( 32 ).withScale( 2 ) )
				.check( ( s, n ) -> sValue( s ).apply( "+", moneyValue( n ) ).equals( sValue( s + n.toString() ) ) );
	}

	@Test
	public void integer_plus_integer() {
		qt().forAll( integers().between( -(Integer.MAX_VALUE) / 2, (Integer.MAX_VALUE) / 2 ),
				integers().between( -(Integer.MAX_VALUE) / 2, (Integer.MAX_VALUE) / 2 ) )
				.check( ( n1, n2 ) -> iValue( n1 ).apply( "+", iValue( n2 ) ).equals( iValue( n1 + n2 ) ) );
	}

}