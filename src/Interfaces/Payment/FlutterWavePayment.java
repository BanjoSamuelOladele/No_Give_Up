package Interfaces.Payment;

public class FlutterWavePayment implements Payable{
    @Override
    public void pay(){
        System.out.println("Paying via flutter wave...");
    }
}
