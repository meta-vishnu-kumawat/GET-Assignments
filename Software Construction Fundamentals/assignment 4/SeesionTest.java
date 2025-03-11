import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import  org.junit.BeforeClass;
public class SeesionTest {
 




    @BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("before class");  
    }  
    TestingAndJunit T = new TestingAndJunit();

    @Test
    public void testCaseForLargestMirrorSection(){
        System.out.println("test case find max");  
       
      
        /**
         * Positive Test case for LargestMirrorSectionfunction
         */
       
        assertEquals(3,T.largestMirrorSection(new int[]{1, 2, 3, 8, 9, 3, 2, 1}));
        assertEquals(2,T.largestMirrorSection(new int[]{7, 1, 4, 9, 7, 4, 1}));
        assertEquals(3,T.largestMirrorSection(new int[]{1, 2, 1, 4}));
        assertEquals(7,T.largestMirrorSection(new int[]{1, 4, 5, 3, 5, 4, 1}));
       


       /**
         * Negitive Test case for LargestMirrorSection function
         */
        AssertionError error = assertThrows(AssertionError.class,()->T.largestMirrorSection(new int[]{}));
        assertEquals("Array cannot be empty...!",error.getMessage());
        
    }

    @Test
    public void testCaseForCountClums(){
        System.out.println("test case find max");  
       
      
        /**
         * Positive Test case for CountClums function
         */
       
        assertEquals(2,T.clumps(new int[]{1, 2, 2, 3, 4, 4}));
        assertEquals(2,T.clumps(new int[]{1, 1, 2, 1, 1}));
        assertEquals(3,T.clumps(new int[]{1, 1,  2, 3,3,4,4,4,5}));
        assertEquals(2,T.clumps(new int[]{1, 1, 1,0,-1,-1}));
        assertEquals(1,T.clumps(new int[]{1,1,1,1,1,1}));


       /**
         * Negitive Test case for CountClums function
         */
        AssertionError error = assertThrows(AssertionError.class,()->T.clumps(new int[]{}));
        assertEquals("Array cannot be empty...!",error.getMessage());
        
    }
    @Test
    public void testCaseForfixXY(){
        System.out.println("test case find max");  
       
      
        /**
         * Positive Test case for SpitArray function
         */
       
        assertArrayEquals(new int[]{9, 4, 5, 4, 5, 9},T.fixXY(new int[]{5, 4, 9, 4, 9, 5},4,5));
        assertArrayEquals(new int[]{1, 4, 5, 1},T.fixXY(new int[]{1, 4, 1, 5},4,5));
        assertArrayEquals(new int[]{1, 4, 5, 1, 1, 4, 5},T.fixXY(new int[]{1, 4, 1, 5, 5, 4, 1},4,5));
       


       /**
         * Negitive Test case for SpitArray function
         * X = 4
         * Y = 5
         */
        AssertionError error = assertThrows(AssertionError.class,()->T.fixXY(new int[]{},4,5));
        assertEquals("Array cannot be empty...!",error.getMessage());

         error = assertThrows(AssertionError.class,()->T.fixXY(new int[]{1, 4, 5, 1, 1, 5, 4},4,5));
        assertEquals("X occurs at the last index of array",error.getMessage());

        error = assertThrows(AssertionError.class,()->T.fixXY(new int[]{1, 4, 5, 1, 4, 5, 5},4,5));
        assertEquals(" Unequal numbers of X and Y in input array.",error.getMessage());

        error = assertThrows(AssertionError.class,()->T.fixXY(new int[]{1, 4, 5,5, 1, 4, 4, 5},4,5));
        assertEquals(" two adjacents X values are there.",error.getMessage());
        
    }

    @Test
    public void testCaseForSpitArray(){
        System.out.println("test case for split Array");  
       
      
        /**
         * Positive Test case for SpitArray function
         */
       
        assertEquals(3,T.splitArray(new int[]{1, 1, 1, 2, 1}));
        assertEquals(-1,T.splitArray(new int[]{2, 1, 1, 2, 1}));
        assertEquals(1,T.splitArray(new int[]{10, 10}));
       


       /**
         * Negitive Test case for SpitArray function
         */
        AssertionError error = assertThrows(AssertionError.class,()->T.splitArray(new int[]{}));
        assertEquals("Array cannot be empty...!",error.getMessage());
        
    }

}
