import dbmod.ReservTripTable;
import dbmod.UsersTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

public class TestWeb {

    @Before
    public void init(){

    }

    @Ignore
    public void UAC(){
        try {
            UsersTable ut=new UsersTable();
            System.out.println(ut.getAllUsersFromDB());
            ut.editUser(1, "Administrator", 12, "admin", "admin", 2);
            ut.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestOrders(){
        try {
            ReservTripTable rtt=new ReservTripTable();
            System.out.println(rtt.getAllOrdersFromDB());
            rtt.closeConnection();


            //id-user id-flight for method addOrder(1,2)
                ReservTripTable rtt2=new ReservTripTable();
                rtt2.deleteOrderByID(4);
                rtt2.closeConnection();



            ReservTripTable rtt3=new ReservTripTable();
            System.out.println(rtt3.getAllOrdersFromDB());
            rtt3.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void flush(){

    }
}
