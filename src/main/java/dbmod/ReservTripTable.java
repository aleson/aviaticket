package dbmod;

import privatecabinetmod.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class ReservTripTable extends DBconnect{

    private int valuser;
    private int  valflt;
    private UsersTable ut =new UsersTable();
    private FlightsTable ft=new FlightsTable();

    public ReservTripTable() throws SQLException {

        valuser=ut.getAllUsersFromDB().size();
        try {
            valflt=ft.getAllFlightsFromDB().size();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private int id,idflights,idusers;

    //from DB select all trip-orders
    public ArrayList getAllOrdersFromDB() throws SQLException
    {
        //Variable with result
        ArrayList res = new ArrayList();

        String query = SELECT_ALL_RESERVETRIPS;
        // Create query
        ResultSet resultSet = st.executeQuery(query);

        while(resultSet.next())
        {
            ArrayList element = new ArrayList();

            id = resultSet.getInt("ID");//2
            idflights=resultSet.getInt("IDFLIGHT");//1
            idusers=resultSet.getInt("IDUSER");//3
            //Add data to variable
            element.add(id);
            element.add(idusers);
            element.add(idflights);
            //Add list to result
            res.add(element);
        }
        //flush resources
        resultSet.close();
        return res;
    }

    //Select order into database by his id
    public Order getOrderById(int ids) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM Tow.RESERVTRIP WHERE RESERVTRIP.ID="+ids);
        Order order=new Order();
        while(rs.next())
        {
            order.setId(rs.getInt("ID"));
            order.setIdflight(rs.getInt("IDFLIGHT"));
            order.setIduser(rs.getInt("IDUSER"));
        }
        rs.close();
        return order;
    }

    //Add trip-order into database
    public void addOrder(int userId,int flightsId) throws SQLException {
        if( !(userId>0 && userId<=valuser) || !(flightsId>0 && flightsId<=valflt)) {throw new IllegalAccessError("incorrect id user or flight");}
            PreparedStatement ps = conn.prepareStatement("INSERT INTO RESERVTRIP (RESERVTRIP.IDFLIGHT,RESERVTRIP.ID,RESERVTRIP.IDUSER) VALUES (?,?,?)");
            ps.setInt(1, flightsId);
            ps.setInt(2, getMaxId(SELECT_ALL_RESERVETRIPS) + 1);
            ps.setInt(3, userId);
            ps.executeUpdate();
            ps.close();


    }
    //Delete trip-order from database
    public void deleteOrderByID(int ids) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM RESERVTRIP WHERE RESERVTRIP.ID=?");
        ps.setInt(1, ids);
        ps.executeUpdate();
        ps.close();
    }


}
