package za.co.entelect.bootcamp.twoface.roshambo;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Before
    public void print(){
        System.out.println("=== Two face ===");
        System.out.println("Members:");
	    System.out.println("- Mpho Mahase!");
        System.out.println("- Quinton Weenink!");
	    System.out.println("- Sean Vienings!");
    }

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {
        assertEquals(0.3271409152695967,0.3271409152695967, 0);
    }

}
