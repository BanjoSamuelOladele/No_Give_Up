package Interfaces.Payment;

public class Payment {
    private Payable payable;
    public Payment(Payable payable){
        this.payable = payable;
    }

    public Payable getPayable() {
        return payable;
    }

    public void setPayable(Payable payable) {
        this.payable = payable;
    }
}
