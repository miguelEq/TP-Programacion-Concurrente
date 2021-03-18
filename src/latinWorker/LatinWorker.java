package latinWorker;

import buffer.Buffer;
import countdown.Countdown;
import sortedList.SortedList;
import threadPool.PoisonPillException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LatinWorker extends Thread {
    private Buffer buffer;
    private SortedList list;
    private Countdown count;

    public LatinWorker(Buffer buffer, SortedList list, Countdown count){
        this.buffer = buffer;
        this.list = list;
        this.count = count;
    }

    public void run(){
        try {
            while (true) {
                 Runnable task = (Runnable) this.buffer.read();
                 task.run();
                 Matriz matriz=(Matriz) task;
                if (cuadradoLatino(matriz.getTamanio(), matriz.getFilasYcolumnas())) {
                    this.list.add(matriz.getNroLinea());
                }
                count.dec();
            }
        }
        catch (PoisonPillException p){
            System.out.println("finalizo el LatinWorker");
        }
    }

    public boolean cuadradoLatino(int n, List<Integer> cuadrado){
         if(0==n){
             return false;
         }
         if(1==n){
             return true;
         }
         if(noHayMayoresAN(n,cuadrado)&&noHayFilasRepetidas(n,cuadrado)&&noHayColumnasRepetidas(n,cuadrado)){
              return true;
         }else {
             return false;
         }

    }

    private boolean noHayMayoresAN(int n , List<Integer> cuadrado){
        for(Integer c : cuadrado){
            if(n < c){
                return false;
            }
        }
        return true;
    }

    private boolean noHayColumnasRepetidas(int n, List<Integer> cuadrado) {
           int indice=0;
           while(indice<n && noHayRepetidos(n,obtenerColumna(indice,n,cuadrado))){
               indice++;
           }
         return n <= indice;
    }

    private List<Integer> obtenerColumna(int i, int n, List<Integer> cuadrado) {
       /*3*3     123 213 321
              1     aux=0;
              2     aux=3;
              3     aux=6;
                    aux=9
        */


        int aux=i;
        List<Integer> result= new ArrayList<>();
         while(aux < n*n){
             result.add(cuadrado.get(aux));
             aux=aux+n;
         }
         return result;
    }

    private boolean noHayFilasRepetidas(int n, List<Integer> cuadrado) {

        int indice=n;
        List<Integer> fila=cuadrado.subList(0,indice);
        while(noHayRepetidos(n,fila) && indice < n*n){
            fila = cuadrado.subList(indice,indice+(n));
            indice = indice+n;
        }
       return indice >= n*n;


    }

    private boolean noHayRepetidos(int tamanioMatriz, List<Integer> fila) {
        HashSet<Integer> set= new HashSet<>(fila);
        return set.size() == fila.size();
    }

}
