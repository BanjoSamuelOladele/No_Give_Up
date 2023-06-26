package Interfaces;

public class Main {
    static MushinFly mf = new MushinFly();
    static OshodiQuack quackF = new OshodiQuack();
    static Duck duck = new Duck(quackF,mf);
    static ObalendeQuackable obalendeQuackable = new ObalendeQuackable();
    public static void main(String[] args) {
        duck.duck();
        duck.setQuack(obalendeQuackable);
        duck.duck();
    }
}
