package threadPool;

public class PoisonPill implements Runnable{

    public PoisonPill(){}

    public void run(){
        throw new PoisonPillException();
    }
}
