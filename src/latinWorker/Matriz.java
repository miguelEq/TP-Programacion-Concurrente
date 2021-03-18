package latinWorker;

import java.util.List;

public class Matriz implements Runnable{
     private int nroLinea;
     private int tamanio;
     private List<Integer> filasYcolumnas;

     public Matriz(int nroLinea,int tamanio,List<Integer> filasYcolumnas){
              this.nroLinea=nroLinea;
              this.filasYcolumnas=filasYcolumnas;
              this.tamanio=tamanio;

     }


    public int getNroLinea() {
        return nroLinea;
    }

    public int getTamanio() {
        return tamanio;
    }

    public List<Integer> getFilasYcolumnas() {
        return filasYcolumnas;
    }

    @Override
    public void run() {
       System.out.println("procesando");
    }
}
