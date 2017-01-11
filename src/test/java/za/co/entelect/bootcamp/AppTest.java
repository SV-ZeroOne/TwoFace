package za.co.entelect.bootcamp;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {
        double convertedToDollar = App.convertRandToDollar(7.22);
        assertEquals(convertedToDollar,1.593454, 0);
    }

}
