package calcmod;
import dbmod.FlightMap;

import java.util.List;


//This class describes Dijkstra’s-algorithm, that needed for calculate optimal flight-paths by cost and flight-time
//And return result at the minimal price or minimal time.
//Additionally class have method to return flight-path - chains of cities into the List,that user chose route
//(if few chains equals one minimal price).
public class FilterAlg extends FlightMap {

    //createArrayForOptimalTime and createArrayForOptimalCost: create special(at the cost or time) arr[][]
    // to ready for Dijkstra-algorithm calculating
    private int[][] createArrayForOptimalTime(){
            int[][] arrayTime = getCreateNavigationArray();

            for (int k = 0; k < arrayCityBegin.size(); k++) {
                //create path between city - if exist path =flight-time,
                // else = 0 (numeration indexes of array with null)
                arrayTime[arrayCityBegin.get(k) - 1][arrayCityEnd.get(k) - 1] =
                        arrayTime[arrayCityEnd.get(k) - 1][arrayCityBegin.get(k) - 1] = this.arrayTime.get(k);
            }
            return arrayTime;
    }

    private float[][] createArrayForOptimalCost() {
        N=getCreateNavigationArray().length;

        float [][] arrayCost = new float[N][N];
        int[][] array=getCreateNavigationArray();
        for (int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                arrayCost[i][j]=array[i][j];

        for(int k=0;k<arrayCityBegin.size();k++) {
            //create path between city - if exist path = cost, else = 0 (numeration indexes of array with null)
            arrayCost[arrayCityBegin.get(k)-1][arrayCityEnd.get(k)-1] =
                    arrayCost[arrayCityEnd.get(k)-1][arrayCityBegin.get(k)-1] = this.arrayCost.get(k);
        }
        return arrayCost;
    }


    //Open method's for client's usable
    public List<Integer> getOptimalTimePath(int startV,int endV) {
        DijkstrasAlg alg=new DijkstrasAlg();
        return alg.run(createArrayForOptimalTime(), startV - 1, endV - 1);
    }
    public List<Integer> getOptimalCostPath(int startV,int endV) {
        float[][] array=createArrayForOptimalCost();
        int[][] tmp=new int[array.length][array.length];

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++)
                tmp[i][j]=Math.round(array[i][j]);//rounding of number
        }
        DijkstrasAlg alg=new DijkstrasAlg();
        return alg.run(tmp, startV - 1, endV - 1);
    }
}
