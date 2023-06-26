package Interfaces;

public class Duck {
    private Flyable fly;
    private Quackable quack;
    public Duck(Quackable quack, Flyable fly){
        this.quack = quack;
        this.fly = fly;
    }
    public void duck(){
        fly.fly();
        quack.quacks();
    }
//    public void duck_a(){
//        quack.quacks();
//    }
    public Flyable getFly() {
        return fly;
    }
    public void setFly(Flyable fly) {
        this.fly = fly;
    }
    public Quackable getQuack() {
        return quack;
    }
    public void setQuack(Quackable quack) {
        this.quack = quack;
    }
}
