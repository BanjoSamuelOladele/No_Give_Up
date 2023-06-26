package testing;

public class running {
    public static void main(String[] args) {
        System.out.println(unique("leetcode"));
        System.out.println(unique("loveleetcode"));
    }
    private static int unique(String inp){
        int ind= -1;
        for (int i = 0; i < inp.length(); i++) {
            for (int j = i + 1; j < inp.length(); j++) {
                if (inp.charAt(i) == inp.charAt(j)){break;}
                if (j == inp.length() - 1) {
                    ind = i;
                    break;
                }
            }
        }
        return ind;
    }
}
