import dbmod.FlightsTable;
import dbmod.ReservTripTable;
import dbmod.UsersTable;
import org.junit.Ignore;
import org.junit.Test;
import privatecabinetmod.Flight;
import privatecabinetmod.Order;
import privatecabinetmod.Path;
import privatecabinetmod.Validate;
import usermod.User;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by ????????? on 17.05.2017.
 */
public class TestPrivateCabinetMod {

    @Test
    public void testFlight() throws SQLException, ParseException {

        FlightsTable oft=new FlightsTable();
        System.out.println(oft.getAllFlightsFromDB());
        oft.closeConnection();

        FlightsTable ft=new FlightsTable();
        Flight flight=ft.getFlightById(2);
        ft.closeConnection();
        System.out.println(flight.toString());
    }

    @Ignore
    public void testValidate() throws SQLException, ParseException {
        UsersTable out=new UsersTable();
        System.out.println(out.getAllUsersFromDB());
        out.closeConnection();

        UsersTable ut=new UsersTable();
        User user= ut.getUserById(2);
        ut.closeConnection();
        FlightsTable ft=new FlightsTable();
        Flight flight = ft.getFlightById(5);
        ft.closeConnection();
        System.out.println("User may buy this flight? "+ Validate.isPay(user, flight));//checking money

    }

    @Ignore
    public void testOrder() throws SQLException {
        ReservTripTable rtt=new ReservTripTable();
        rtt.addOrder(3,1);
        rtt.closeConnection();

        ReservTripTable rtt2=new ReservTripTable();
        Order order=rtt2.getOrderById(1);
        rtt2.closeConnection();

        System.out.println("Order="+ order.toString());

        ReservTripTable rtt3=new ReservTripTable();
        rtt3.deleteOrderByID(1);
        rtt3.closeConnection();

    }


    @Ignore
    public void testPath() throws Exception{
        Path path=new Path();
        path.getPath();
    }

}
