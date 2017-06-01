import calcmod.DijkstrasAlg;
import calcmod.FilterAlg;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class TestCalcMod {
    private DijkstrasAlg alg;

    private int[][] array ={{0, 1, 2, 3},
                            {1, 0, 6, 7},
                            {2, 6, 0, 9},
                            {3, 7, 9, 0}};
    @Before
    public void getObj() {
        alg=new DijkstrasAlg();
    }
    @After
    public void clean(){
        alg=null;
    }
    @Ignore
    public void TestAlg() throws IOException {
        alg.balanceMatrix(array,1,3);
    }
    @Test
    public void TestFilterAlg() throws SQLException, ParseException {
        FilterAlg fa=new FilterAlg();
        System.out.println(fa.getOptimalCostPath(1, 5));
        FilterAlg fa2=new FilterAlg();
        System.out.println(fa2.getOptimalTimePath(1,5));
    }

}
