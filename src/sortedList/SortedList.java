package sortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedList {
    private List<Integer> list;

    public SortedList(){
        this.list = new ArrayList<Integer>();
    }

    public synchronized void add(Integer i){
        this.list.add(i);
        Collections.sort(this.list);
    }

    public List<Integer> getList(){
        return this.list;
    }
}
