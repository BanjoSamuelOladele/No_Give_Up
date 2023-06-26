package run;

public class classPathhh {
    public static void main(String[] args) {
        System.out.println(maxNumber("129998"));
    }
    private static String maxNumber(String number){
        int max = 0;
        int realNumber = Integer.parseInt(number);
        for (int index = 0; index < number.length(); index++) {
            if (realNumber % 2 != 0) if (realNumber > max) max = realNumber;
            realNumber /= 10;
        }
        String maxx = String.valueOf(max);
        if (maxx.equals("0")) maxx = "";
        return maxx;
    }
}
