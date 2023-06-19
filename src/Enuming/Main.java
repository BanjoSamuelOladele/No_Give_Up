package Enuming;

public class Main{
    public static void main(String[] args) {
        for (Records records : Records.values()){
            System.out.println(records +", "+ records.getTitle() +", "+ records.getPublishedYear());
        }
    }
}
