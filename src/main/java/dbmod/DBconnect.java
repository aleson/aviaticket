package dbmod;


import java.sql.*;
import java.util.Locale;

public class DBconnect {
    protected static String username="Tow";//Login and password connecting with database
    protected static String password="1234";
    protected static Connection conn;
    protected static Statement st;

    //In constructor creating connection with database
    public DBconnect() throws SQLException
    {
        if(username==null || password==null || (username==null && password==null))
            throw new IllegalArgumentException("Exception enter data into DB");

        Locale.setDefault(Locale.ENGLISH);
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);//authorisation
        st = conn.createStatement();//install connection
    }

    //send command to db - universal procedure
    public void command(String command) throws SQLException{
        st.executeUpdate(command);
    }

    //close connection
    public void closeConnection() throws SQLException{
        if(st != null){
            st.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    //MAIN COMMANDS:
    String SELECT_ALL_USERS = "SELECT * FROM Tow.USERS";
    String SELECT_ALL_AEROPORTS="SELECT * FROM Tow.AEROPORTS";
    String SELECT_ALL_FLIGHTS="SELECT * FROM Tow.FLIGHT";
    String SELECT_ALL_RESERVETRIPS="SELECT * FROM Tow.RESERVTRIP";
    protected int count;//counter existing entry's

    //tool to calculate maximal existing id in database:
    protected int getMaxId(String query) throws SQLException
    {
        ResultSet resultSet=st.executeQuery(query);
        while(resultSet.next()) {
            count++;    //calculate id count
        }
        resultSet.close();
        return count;
    }
}
