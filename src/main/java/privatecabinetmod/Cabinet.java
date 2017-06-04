package privatecabinetmod;

import dbmod.FlightsTable;
import dbmod.ReservTripTable;
import usermod.User;

import java.sql.SQLException;
import java.util.*;

//This class is a class for controlling the actions of a particular user.
public class Cabinet {

    private User user;
    private Order order;
    private Flight flight;
    protected Path path=new Path();

    private List<ArrayList> arrayOrders=new ArrayList<ArrayList>();//all user's orders

    public List<ArrayList> getArrayOrders() {
        return arrayOrders;
    }

    public Cabinet(User user){
        if(user==null) throw new IllegalArgumentException("Cabinet must be real user");
        this.user=user;
    }

    //By pressing button for create order(to issue tickets)
    public boolean createStandartOrder(int idFlight){

        try {
            FlightsTable ft = new FlightsTable();
            flight=ft.getFlightById(idFlight);
            ft.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           if(Validate.isPay(user,flight)==true){ //if user is payable
               try
               {
                   ReservTripTable rtt= new ReservTripTable();
                   rtt.addOrder(user.getId(), flight.getId()); //create Order in database
                   rtt.closeConnection();

                   return true;
               } catch (Exception e) {
                   e.printStackTrace();
                   return false;
               }
           }
           else return false;
        }
    }
    //next methods need for create optimal orders
    public boolean createOptimalTimeOrder(int startCity,int endCity)
    {
        path.buildTimePath(startCity,endCity);
        return createOptimalOrder();
    }
    public boolean createOptimalCostOrder(int startCity,int endCity)
    {
        path.buildCostPath(startCity,endCity);
        return createOptimalOrder();
    }
    //algorithm for create optimal order
    private boolean createOptimalOrder(){
        path.getPathlist();
        float money=user.getMoney(); //local variable
        int countFlight=0;
                for(int i=0;i<path.getPathlist().size();i++)
                {
                    //validate each flight at path
                    if(Validate.isPay(user, path.getPathlist().get(i)) && money>=path.getPathlist().get(i).getCost())
                    {
                        money=money-path.getPathlist().get(i).getCost();
                        countFlight++;
                    }
                }
        if(countFlight==path.getPathlist().size()){

            try {
                for(int i=0;i<path.getPathlist().size();i++)
                {
                    ReservTripTable rtt = new ReservTripTable();
                    rtt.addOrder(user.getId(),path.getPathlist().get(i).getId());
                    rtt.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }
        else return false;
    }

    //this method return all user's orders
    public List viewUsersOrders(){
        try {
            ReservTripTable rtt= new ReservTripTable();
            ArrayList array=rtt.getAllOrdersFromDB();
            for(int i=0;i<array.size();i++)
            {
                ArrayList underArray=(ArrayList)array.get(i);
                if(underArray.get(1)==user.getId()){
                    arrayOrders.add((ArrayList)array.get(i));
                }
            }
            rtt.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayOrders;
    }



}
