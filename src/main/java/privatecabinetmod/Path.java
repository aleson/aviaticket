package privatecabinetmod;

import calcmod.FilterAlg;

import java.util.ArrayList;
import java.util.List;


public class Path {
    FilterAlg alg=new FilterAlg();
    List<Integer> list=new ArrayList<Integer>();

    public void getPath() throws Exception {
        list=alg.getOptimalTimePath(5,1);
        System.out.println(list);
    }



}
