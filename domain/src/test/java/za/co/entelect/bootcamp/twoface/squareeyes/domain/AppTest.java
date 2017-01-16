package za.co.entelect.bootcamp.twoface.squareeyes.domain;

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

    Repository repo = new IssueRepository();
    repo.create(new Issue());

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {

    }

}
