public class Recursion {

    public int findHCF(int x, int y) {
        if (y == 0) {
            return x;
        }
        return findHCF(y, x % y);
    }

    public int findLCF(int x, int y) {
        int hcf = findHCF(x, y);
        return (x * y) / hcf;
    }


    public static void main(String[] args) {
        Recursion r = new Recursion();
        Search s =  new Search();
        System.out.println(s.binarySearch(new int[]{10,25,26,37,45,65,98}, 0,6 ,46));
    }
}