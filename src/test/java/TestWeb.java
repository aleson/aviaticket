import dbmod.AeroportsTable;
import dbmod.ReservTripTable;
import dbmod.UsersTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestWeb {

    ArrayList cabarraycities;
    ArrayList onlycities=new ArrayList();
    ArrayList onlycities2=new ArrayList();

    @Before
    public void init(){


    }

    @Ignore
    public void uac(){
        try {
            UsersTable ut=new UsersTable();
            System.out.println(ut.getAllUsersFromDB());
            ut.editUser(1, "Administrator", 12, "admin", "admin", 2);
            ut.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void testOrders(){
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

    @Test
    public void View(){
        try {
            AeroportsTable at=new AeroportsTable();
            cabarraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<cabarraycities.size();i++){

            onlycities = (ArrayList)cabarraycities.get(i);
            onlycities2.add(onlycities.get(1));

        }
        System.out.println(onlycities2);
    }

    @After
    public void flush(){

    }
}
