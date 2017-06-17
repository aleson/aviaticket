import dbmod.AeroportsTable;
import dbmod.FlightsTable;
import dbmod.ReservTripTable;
import dbmod.UsersTable;
import org.junit.Ignore;
import org.junit.Test;
import usermod.User;

import java.sql.SQLException;
import java.text.ParseException;


public class TestDatabase {

    @Ignore
    public void TestCitiesFilght() throws SQLException, ParseException {
        FlightsTable ft=new FlightsTable();
        System.out.println("2,5="+ft.getIdFlightByCities(2, 5));
    }

    @Ignore
    public void TestUsersTable() throws Exception{
        UsersTable con =new UsersTable();
        System.out.println(con.getAllUsersFromDB());
        con.closeConnection();

        System.out.println("-----");

        UsersTable table=new UsersTable();
        User obj=new User();
        obj.setFio("Ibragim Rudolfovich");
        obj.setLogin("Ibrom");
        obj.setPass("007");
        obj.setMoney(1492);
        obj.setRole(1);
        table.addUser(obj);
        table.closeConnection();

        obj.info();
        UsersTable test=new UsersTable();
        test.deleteUserByID(4);
        test.deleteUserByID(5);
        System.out.println(test.getAllUsersFromDB());
        test.closeConnection();
        System.out.println("-----");
        UsersTable tablet=new UsersTable();
        System.out.println(tablet.getAllUsersFromDB());
        tablet.closeConnection();
        UsersTable tab2=new UsersTable();
        tab2.editUser(3,"Ibragim Rudolfovich",1493,"Ibrom","1234",1);
        tab2.closeConnection();
        UsersTable tab3=new UsersTable();
        System.out.println(tab3.getAllUsersFromDB());
        table.closeConnection();

    }
    @Ignore
    public void TestAeroportsTable() throws Exception{
        AeroportsTable con =new AeroportsTable();
        System.out.println(con.getAllAeroportsFromDB());
        con.closeConnection();
        AeroportsTable con2 =new AeroportsTable();
        con2.addAeroport("Magnitogorsk");
        System.out.println(con2.getAllAeroportsFromDB());
        con2.closeConnection();
        AeroportsTable con3=new AeroportsTable();
        con3.deleteAeroportByID(6);

        System.out.println(con3.getAllAeroportsFromDB());
        con3.closeConnection();
        AeroportsTable con4=new AeroportsTable();
        con4.editAeroport(5,"Minsk");
        System.out.println(con4.getAllAeroportsFromDB());
        con4.closeConnection();


    }
    @Ignore
    public void TestForRole() throws Exception{
        UsersTable con2 =new UsersTable();
        User u=con2.getUserById(1);
        con2.closeConnection();
        u.info();
        System.out.println(u.getRole());

    }
    @Ignore
    public void TestReservTripTable() throws Exception{
        ReservTripTable rtt=new ReservTripTable();
        rtt.addOrder(3,4);
        rtt.closeConnection();
        ReservTripTable rtt2=new ReservTripTable();
        System.out.println(rtt2.getAllOrdersFromDB());
        rtt2.closeConnection();
        ReservTripTable rtt3=new ReservTripTable();
        rtt3.deleteOrderByID(1);
        rtt3.closeConnection();
        ReservTripTable rtt4=new ReservTripTable();
        System.out.println(rtt4.getAllOrdersFromDB());
        rtt4.closeConnection();
    }
    @Ignore
    public void TestFlightsTable() throws Exception{
        FlightsTable flightsTable=new FlightsTable();
        System.out.println(flightsTable.getAllFlightsFromDB());
        flightsTable.closeConnection();

        FlightsTable ft1=new FlightsTable();
        ft1.addFlight("AIRBUS",1,2,170000,ft1.setDate("11-02-2001"),20,48,900);
        ft1.closeConnection();

        FlightsTable flightsTable2=new FlightsTable();
        System.out.println(flightsTable2.getAllFlightsFromDB());
        flightsTable2.closeConnection();

        FlightsTable ft2=new FlightsTable();
        ft2.deleteFlightByID(8);
        ft2.closeConnection();

        FlightsTable flightsTable3=new FlightsTable();
        System.out.println(flightsTable3.getAllFlightsFromDB());
        flightsTable3.closeConnection();

    }


}