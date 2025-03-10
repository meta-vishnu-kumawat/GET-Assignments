public class StringOperation {
    /**
     * @param String str1 as first input String
     * @param String str2 as second input String
     * @return 1 if both equls otherwise 0
     */
    public boolean isEquals(String str1, String str2) {
        return str1.equals(str2);
    }

    /**
     * @param String str1 as input String
     * @return reverse String of str1
     */
    public String revseString(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            s.append(str.charAt(i));
        }
        s.reverse();
        return s.toString();
    }

    /**
     * @param String str1 as input String
     * @return modified String
     */
    public String replaceChar(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + ('a' - 'A'));
                s.append(ch);
            } else if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - ('a' - 'A'));
                s.append(ch);
            } else {

                s.append(ch);
            }

        }
        return s.toString();
    }

    public String longestWord(String str) {
        int maxLength = 0;
        int currLength = 0;
        StringBuilder s = new StringBuilder();
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                if (maxLength <= currLength) {
                    ans = s.toString();
                    s = new StringBuilder("");
                    maxLength = currLength;
                    System.out.println(maxLength + " " + currLength + " " + ans);
                    currLength = 0;
                } else {
                    s = new StringBuilder("");
                    currLength = 0;
                }

            } else {
                s.append(ch);
                currLength++;
            }
        }
        if (maxLength <= currLength) {
            ans = s.toString();
            s = new StringBuilder("");
        }
        return ans;
    }
}
