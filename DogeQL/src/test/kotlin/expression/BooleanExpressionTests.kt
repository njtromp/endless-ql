package expression

import data.value.BooleanValue
import org.junit.Assert
import org.junit.Test
import javax.naming.OperationNotSupportedException

class BooleanExpressionTests {

    @Test(expected = OperationNotSupportedException::class)
    fun `boolean addition results in exception`() {
        BooleanValue(true) + BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun `boolean subtraction results in exception`() {
        BooleanValue(true) - BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun `boolean multiplication results in exception`() {
        BooleanValue(true) * BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun `boolean division results in exception`() {
        BooleanValue(true) / BooleanValue(true)
    }

    @Test
    fun `boolean equality is as expected`() {
        val result1 = BooleanValue(true) == BooleanValue(true)
        Assert.assertTrue(result1)

        val result2 = BooleanValue(false) == BooleanValue(false)
        Assert.assertTrue(result2)
    }

    @Test
    fun `boolean inequality is as expected`() {
        val result = BooleanValue(false) != BooleanValue(true)
        Assert.assertTrue(result)
    }

    @Test
    fun `boolean equality and inequality are inverse`() {
        val resultTrue = BooleanValue(true) == BooleanValue(true)
        val resultFalse = BooleanValue(true) != BooleanValue(true)
        Assert.assertTrue(resultTrue)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun `boolean greater than behaves as expected`() {
        val result = BooleanValue(true) > BooleanValue(true)
        Assert.assertFalse(result)
    }

    @Test
    fun `boolean less than behaves as expected`() {
        val result = BooleanValue(true) < BooleanValue(true)
        Assert.assertFalse(result)
    }

    @Test
    fun `boolean greater than or equal behaves as expected`() {
        val result = BooleanValue(true) >= BooleanValue(true)
        Assert.assertTrue(result)
    }

    @Test
    fun `boolean less than or equal behaves as expected`() {
        val result = BooleanValue(true) <= BooleanValue(true)
        Assert.assertTrue(result)
    }

    @Test
    fun `boolean negation inverts input`() {
        val result = !BooleanValue(true)
        Assert.assertFalse(result.booleanValue.value)
    }

}