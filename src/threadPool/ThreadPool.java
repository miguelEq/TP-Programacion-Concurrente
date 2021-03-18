package threadPool;

import buffer.Buffer;
import countdown.Countdown;
import latinWorker.LatinWorker;
import sortedList.SortedList;

public class ThreadPool {

    private Buffer buffer;
    private int workersTotales;
    private SortedList list;
    private Countdown count;

    public ThreadPool(int cantCandidatos,int cantidadWorkers){
        SortedList sortedList = new SortedList();
        list = sortedList;
        Countdown countdown = new Countdown(cantCandidatos);
        count = countdown;
        buffer = new Buffer(cantCandidatos);
        this.workersTotales = cantidadWorkers;
        for (int i = 0; i < workersTotales ;i++){
            new LatinWorker(this.buffer,sortedList,countdown).start();
        }

        }

        public void launch(Runnable tarea){
            this.buffer.write(tarea);
        }

        public void stop(){

            for(int i = 0; i <this.workersTotales;i++){
                this.buffer.write(new PoisonPill());
            }
        }

        public Countdown getCount(){return this.count;}
        public SortedList getList(){return this.list;}
}

