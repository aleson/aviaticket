package dbmod;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is build map of flights, it passes data(path's matrix) to FilterAlg
 */

public class FlightMap {

    protected List<Integer> arrayTime=new ArrayList<Integer>();
    protected List<Float> arrayCost=new ArrayList<Float>();
    protected List<Integer> arrayCityBegin=new ArrayList<Integer>();// array need for building flight-paths between cities
    protected List<Integer> arrayCityEnd=new ArrayList<Integer>();// array need for building flight-paths between cities
    protected List<Integer> arrayId =new ArrayList<Integer>(); //array need for class Path

    protected int N;//size to navigation array NxN

    protected FlightMap() {

    }

    protected int[][] getCreateNavigationArray(){
        try {
            return createNavigationArray();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Exception at creating path-matrix(sql)");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Exception at creating path-matrix(parse)");
        }
    }
    //create array NxN connections(flight-paths) between cities
    private int[][] createNavigationArray() throws SQLException, ParseException {
        FlightsTable ft=new FlightsTable();
        ArrayList list=ft.getAllFlightsFromDB();//getting data from DB in our copy-array-list and close connection
        ft.closeConnection();
        AeroportsTable ft2=new AeroportsTable();
        N=ft2.getAllAeroportsFromDB().size();//get size to out arr[][]
        ft2.closeConnection();
        for(int i=0;i<list.size();i++) {
            ArrayList underList = (ArrayList) list.get(i);//numeration with null
            arrayId.add((Integer)underList.get(0));// 1 - number of id
            arrayCost.add((Float) underList.get(4));//4 - number of cost
            arrayTime.add((Integer) underList.get(8));//8 - number of flight-time [Integer type]
            arrayCityBegin.add((Integer)underList.get(2));//add beginning city
            arrayCityEnd.add((Integer)underList.get(3));//add ending city
        }
        int navigationArray[][]=new int[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                navigationArray[i][j]=0;//add array initial values=0

        for(int k=0;k<arrayCityBegin.size();k++) {
            //create path beetween city - if exist path = 1, else = 0 (numeration indexes of array with null)
            navigationArray[arrayCityBegin.get(k)-1][arrayCityEnd.get(k)-1] =
                    navigationArray[arrayCityEnd.get(k)-1][arrayCityBegin.get(k)-1] = 1;
        }
        return navigationArray;
    }
}
