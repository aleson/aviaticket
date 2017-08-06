package privatecabinetmod;

import calcmod.FilterAlg;
import dbmod.FlightMap;
import dbmod.FlightsTable;
import dbmod.ReservTripTable;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Path extends FlightMap{

    private List<Flight> pathlist=new ArrayList<Flight>();//array of flights
    private List<Integer> list=new ArrayList<Integer>();
    private List<Integer> listId=new ArrayList<Integer>();

    public List<Integer> buildTimePath(int start,int end) {
        FilterAlg alg=new FilterAlg();
        return list=alg.getOptimalTimePath(start,end);
    }

    public List<Integer> buildCostPath(int start,int end) {
        FilterAlg alg=new FilterAlg();
        return list=alg.getOptimalCostPath(start, end);
    }

    public List<Flight> getPathlist() {
        return toIdOrders();
    }

    //this method transform path's versh's(begin city and end city) to array of Flight's indexes
    public List toIdOrders(){
        try {
            getCreateNavigationArray();
            for(int i=0;i<list.size();i++)
            {
                for(int j=0;j<arrayId.size();j++) {
                    FlightsTable ft=new FlightsTable();
                    if (((i+1)!=list.size()) && (arrayCityBegin.get(j) == list.get(i) && arrayCityEnd.get(j) == list.get(i + 1))) {
                        listId.add(arrayId.get(j));//add to array index of Order
                        pathlist.add(ft.getFlightById(arrayId.get(j)));
                    }
                    ft.closeConnection();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return pathlist;
        }
    }
}
