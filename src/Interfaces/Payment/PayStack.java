package Interfaces.Payment;

public class PayStack implements Payable{
    @Override
    public void pay(){
        System.out.println("Paying via paystack...");
    }
}
