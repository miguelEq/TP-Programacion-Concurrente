import countdown.Countdown;
import latinWorker.Matriz;
import threadPool.ThreadPool;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
       URL url = ClassLoader.getSystemResource("inputs-ejemplo");
       File file = new File(url.toURI());
       FileReader fr = new FileReader(file);
       BufferedReader reader = new BufferedReader(fr);

       //tamaño de archivo
       Integer tamaño = Integer.parseInt(reader.readLine());
       ThreadPool tp= new ThreadPool(100,10);
       List<Matriz> matrices = new ArrayList<>();
       for(int i =1 ; i <= tamaño; i++){
           String line = reader.readLine();
           Integer tamMat = Integer.parseInt(line.substring(0,2).trim());
           line = line.substring(2,line.length()).trim();
           line = line.replace(" ", ",");
           String str[] = line.split(",");
           List<String> mat = new ArrayList<>();
           mat = Arrays.asList(str);
           List<Integer> matriz = listStringToInteger(mat);
           Matriz createdMatriz= new Matriz(i,tamMat,matriz);
           matrices.add(createdMatriz);
       }

       for(Matriz m : matrices){
           tp.launch(m);
       }
       tp.getCount().zero();
       tp.stop();
       List<Integer> list = tp.getList().getList();
       System.out.println(list);

    }

    private static List<Integer> listStringToInteger(List<String> mat) {
        List<Integer> favList = new ArrayList<>();
        for(String s : mat){
            favList.add(Integer.parseInt(s));
        }
        return favList;
    }

}
