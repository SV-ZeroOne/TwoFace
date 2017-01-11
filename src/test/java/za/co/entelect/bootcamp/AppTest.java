package za.co.entelect.bootcamp;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Before
    public void print(){
        System.out.println("=== Two face ===");
        System.out.println("Members");
        System.out.println("Quinton Weenink");
    }

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {
        double convertedToDollar = App.convertRandToDollar(7.22);
        assertEquals(convertedToDollar,0.3271409152695967, 0);
    }

}
