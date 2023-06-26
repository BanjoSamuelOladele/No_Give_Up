package Interfaces.Payment;

public class QuickTeller implements Payable{
    @Override
    public void pay(){
        System.out.println("Paying via interswitch...");
    }
}
