import dbmod.UsersTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestWeb {

    @Before
    public void init(){

    }
    @Test
    public void UAC(){
        try {
            UsersTable ut=new UsersTable();
            System.out.println(ut.getAllUsersFromDB());
            ut.deleteUserByID(4);
            //ut.deleteUserByID(5);
            System.out.println(ut.getAllUsersFromDB());
            ut.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @After
    public void flush(){

    }
}
