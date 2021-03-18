package countdown;

public class Countdown {
    private Integer contador;

    public Countdown(Integer n){
        this.contador = n;
    }

    public synchronized void dec(){
        this.contador--;
        if(contador >= 0){
            notifyAll();
        }
    }

    public synchronized void zero(){
        while(greaterThanZero()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            notifyAll();
        }
    }

    private boolean greaterThanZero() {
        return this.contador > 0;
    }

}
