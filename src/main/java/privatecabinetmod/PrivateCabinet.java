package privatecabinetmod;

import dbmod.FlightsTable;
import dbmod.ReservTripTable;
import dbmod.UsersTable;
import usermod.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

//This class is a class for controlling the actions of a particular user.
public class PrivateCabinet {

    private User user;
    private Order order;
    private Flight flight;
    protected Path path=new Path();
    private List<ArrayList> arrayOrders=new ArrayList<ArrayList>();//all user's orders

    public List<ArrayList> getArrayOrders() {
        return arrayOrders;
    }

    public PrivateCabinet(User user){
        if(user==null) throw new IllegalArgumentException("PrivateCabinet must be real user");
        this.user=user;
    }

    //By pressing button for create order(to issue tickets)
    public boolean createStandartOrder(int idFlight){
        try {
            FlightsTable ft = new FlightsTable();
            flight=ft.getFlightById(idFlight);
            ft.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           if(Validate.isPay(user,flight)==true){ //if user is payable
               try
               {
                   ReservTripTable rtt= new ReservTripTable(); //add order on user
                   rtt.addOrder(user.getId(), flight.getId()); //create Order in database
                   rtt.closeConnection();
                   UsersTable ut=new UsersTable(); //refresh user
                   ut.editUser(user.getId(),user.getFio(),user.getMoney()-flight.getCost(),user.getLogin(),user.getPass(),user.getRole());
                   return true;
               }
               catch (Exception e) {
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
        List arraypath=null;
        Path path=new Path();
        arraypath=path.buildTimePath(startCity,endCity);//get list between cities
        int cfid=-1;
            for (int i = 0; i < arraypath.size()-1; i++) {
                try {
                    FlightsTable ft = new FlightsTable();
                    int t1=Integer.parseInt(arraypath.get(i).toString());
                    int t2=Integer.parseInt(arraypath.get(i+1).toString());
                    if(t1>t2){
                        int imp=t1;
                        t1=t2;
                        t2=imp;
                    }
                    cfid=ft.getIdFlightByCities(t1, t2);
                    ft.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                if(cfid!=-1){
                    if(!createStandartOrder(cfid))
                    {
                        return false;
                    }
                }else return false;
            }
        return true;
    }

    public boolean createOptimalCostOrder(int startCity,int endCity)
    {
        List arraypath=null;
        Path path=new Path();
        arraypath=path.buildCostPath(startCity, endCity);//get list between cities
        int cfid=-1;
        for (int i = 0; i < arraypath.size()-1; i++) {
            try {
                FlightsTable ft = new FlightsTable();
                int t1=Integer.parseInt(arraypath.get(i).toString());
                int t2=Integer.parseInt(arraypath.get(i+1).toString());
                if(t1>t2){
                    int imp=t1;
                    t1=t2;
                    t2=imp;
                }
                cfid=ft.getIdFlightByCities(Integer.parseInt(arraypath.get(i).toString()), Integer.parseInt(arraypath.get(i + 1).toString()));
                ft.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ParseException e) {
                e.printStackTrace();
            }
            if(cfid!=-1){
                if(!createStandartOrder(cfid))
                {
                    return false;
                }
            }else return false;
        }
        return true;
    }

    public List<Integer> GetOptimalTimePath(int startCity,int endCity){
        return path.buildTimePath(startCity, endCity);
    }

    public List<Integer> GetOptimalCostPath(int startCity,int endCity){
        return path.buildCostPath(startCity, endCity);
    }
}
