package se.kth.iv1350.amazingpos.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private Amount instanceNoArgumentConstr;
    private Amount instanceWithAmtThree;

    @BeforeEach
    public void setUp() {
        instanceNoArgumentConstr = new Amount();
        instanceWithAmtThree = new Amount(3);
    }

    @AfterEach
    public void tearDown() {
        instanceNoArgumentConstr = null;
        instanceWithAmtThree = null;
    }

    @Test
    public void testEqual() {
        int valueOfTwoThree = 3;
        Amount amountOfTree = new Amount(valueOfTwoThree);
        boolean expResult = true;
        boolean actualResult = instanceWithAmtThree.equals(amountOfTree);
        assertEquals (expResult, actualResult , "Amount instances with same value are not equal.");
    }

    @Test
    public void testPlus() {
        int valueOfTen  = 10;
        int valueOfTree = 3;
        Amount amountOfTen = new Amount(valueOfTen);
        Amount amountOfTree = new Amount(valueOfTree);
        Amount expectedSum = new Amount(valueOfTree + valueOfTen);
        Amount actualResultOfSum = amountOfTen.plus(amountOfTree);
        assertEquals(expectedSum, actualResultOfSum, "The actual sum of Amount "+
                " does not equal to expected sum.");
    }

    @Test
    public void testNotEqualsNull() {
        Object other = null;
        boolean expResult = true;
        boolean actualResult = instanceNoArgumentConstr.equals(other);
        assertNotEquals(expResult, actualResult,
                "The actual instance of Amount is equal to null.");
    }

    @Test
    public void testEqualsJavaLangObject() {
        Object instanceOfJavaLangObject = new Object();
        boolean expResult = false;
        boolean actualResult = instanceNoArgumentConstr.equals(instanceOfJavaLangObject);
        assertEquals(expResult, actualResult, "The actual instance of Amount " +
                "equals to java.lang.Object instance.");
    }

    @Test
    public void testNotEqualNoArgConstr() {
        int valueOfTwo = 2;
        Amount amountOfTwo = new Amount(valueOfTwo);
        boolean expResult = true;
        boolean actualResult = instanceNoArgumentConstr.equals(amountOfTwo);
        assertNotEquals(expResult, actualResult, "Amount instances with" +
                " different value  are equal.");
    }

    @Test
    public void testNotEqual() {
        int valueOfTwo = 2;
        Amount amountOfTwo = new Amount(valueOfTwo);
        boolean expResult = false;
        boolean actualResult = instanceWithAmtThree.equals(amountOfTwo);
        assertEquals(expResult, actualResult, "Amount instances with different " +
                " value are equal.");
    }

    @Test
    public void testPlusByOneNegativeNumberAndOnePositiveNumber() {
        int valueOfTen  = 10;
        int valueOfNegativeTree = -3;
        Amount amountOfTen = new Amount(valueOfTen);
        Amount amountOfTree = new Amount(valueOfNegativeTree);
        Amount expectedSum = new Amount(valueOfNegativeTree + valueOfTen);
        Amount actualResultOfSum = amountOfTen.plus(amountOfTree);
        assertEquals(expectedSum, actualResultOfSum, "The actual sum of Amount "+
                " does not equal to expected sum.");
    }

    @Test
    public void testToStringNegativeAmt() {
        int valueOfNegativeTen = -10;
        Amount amountOfNegativeTen = new Amount(valueOfNegativeTen);
        String expResult = Integer.toString(valueOfNegativeTen);
        String actualResult = amountOfNegativeTen.toString();
        assertEquals(expResult, actualResult, "The actual returned string "+
                " does not match the expected returned String");

    }
    @Test
    public void testToStringPositiveAmount() {
        int valueOfPositiveTen = 10;
        Amount amountOfTen = new Amount(valueOfPositiveTen);
        String expResult = Integer.toString(valueOfPositiveTen);
        String actualResult = amountOfTen.toString();
        assertEquals(expResult, actualResult, "The actual returned string "+
                " does not match the expected returned String");
    }
}