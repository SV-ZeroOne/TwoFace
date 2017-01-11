package za.co.entelect.bootcamp;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {
        System.out.println("Start test");
        double convertedToDollar = App.convertRandToDollar(7.22);
        assertEquals(convertedToDollar,0.3271409152695967, 0);
        System.out.println("End test");
    }

}
