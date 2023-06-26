package contacts;

public class Testing {
    public static void main(String[] args) {
        String[] arr = {"alic3", "4", "bob", "3", "00000"};
        String[] arr1 = {"01", "001", "1", "0001"};
        System.out.println(checkStringLength(arr1));
    }
    public static int checkStringLength(String [] strings){
        int number = 0;
        for (int index = 0; index < strings.length; index++) {
            String max ="";
                if (strings[index].matches("[0-9]")){
                    max = strings[index];
                    if (Integer.parseInt(max) > number) number = Integer.parseInt(max);
                }
            for (int i = 0; i < strings[index].length(); i++) {
                for (Character numb : strings[index].toCharArray()) {
                    if (!Character.isDigit(numb)) if (strings[index].length() > number)
                        number = strings[index].length();
                }
            }
        }
        return number;
    }
}
