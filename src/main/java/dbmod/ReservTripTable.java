package dbmod;

import privatecabinetmod.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ReservTripTable extends DBconnect{

    public ReservTripTable() throws SQLException {
    }

    private int id,idflights,idusers;

    //from DB select all trip-orders
    public Vector getAllOrdersFromDB() throws SQLException
    {
        //Variable with result
        Vector res = new Vector();

        String query = SELECT_ALL_RESERVETRIPS;
        // Create query
        ResultSet resultSet = st.executeQuery(query);

        while(resultSet.next())
        {
            Vector element = new Vector();

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
        st.close();
        conn.close();
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
    public void addOrder(int userId,int flightsId) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO RESERVTRIP (RESERVTRIP.IDFLIGHT,RESERVTRIP.ID,RESERVTRIP.IDUSER) VALUES (?,?,?)");
        ps.setInt(1, flightsId);
        ps.setInt(2, getMaxId(SELECT_ALL_RESERVETRIPS)+1);
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