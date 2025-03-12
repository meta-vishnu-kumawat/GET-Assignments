import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class TestingFile {
    Recursion R = new Recursion();
    Search S = new Search();
    NQueen Q = new NQueen();
    KnightsTourProblem K = new KnightsTourProblem();
    @Test
    public void testCaseForLCM() {

        /**
         * Positive Test case for LCM
         */

        assertEquals(8, R.findLCF(8, 2));
        assertEquals(150, R.findLCF(25, 30));
        assertEquals(3485, R.findLCF(85, 41));

    }

    @Test
    public void testCaseForHCF() {

        /**
         * Positive Test case for HCF
         */

        assertEquals(5, R.findHCF(25, 30));
        assertEquals(1, R.findHCF(85, 41));
        assertEquals(2, R.findHCF(8, 2));

    }
    @Test
    public void testCaseForLinearSearch() {

        /**
         * Positive Test case for Linear Search
         */

        assertEquals(4,S.linearSearch(new int[]{1,2,95,87,85,6}, 85, 0));
        assertEquals(8,S.linearSearch(new int[]{1,2,95,87,85,6,7554,955,-1,14}, -1, 0));
        assertEquals(-1,S.linearSearch(new int[]{1,2,95,87,85,6}, 5, 0));

    }

    @Test
    public void testCaseForBinarySearch() {

        /**
         * Positive Test case for Binary Search
         */

        assertEquals(4,S.binarySearch(new int[]{1,2,9,17,85,600}, 0, 5,85));
        assertEquals(-1,S.binarySearch(new int[]{1,2,9,17,85,600}, 0, 5,5));

    }
}
