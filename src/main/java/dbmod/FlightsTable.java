package dbmod;

import privatecabinetmod.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightsTable extends DBconnect {

    public FlightsTable() throws SQLException {
    }

    private String name;
    private Integer id,citybegid,cityendid,timeflight;
    private float cost;
    private Date date=new Date();

    protected int size=0;


    private int arrhours,arrminutes;

    public String setDate(String str) throws ParseException {
        String sdf = new SimpleDateFormat("dd-MMM-yy").format(new SimpleDateFormat("dd-MM-yyyy").parse(str));
        return sdf;
    }

    //from DB select all flights
    public ArrayList getAllFlightsFromDB() throws SQLException, ParseException {

        //Variable with result
        ArrayList res = new ArrayList();

        String query = SELECT_ALL_FLIGHTS;
        // Create query
        ResultSet resultSet = st.executeQuery(query);


        while(resultSet.next())
        {

            ArrayList element = new ArrayList();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

            id = resultSet.getInt("ID");//5
            citybegid=resultSet.getInt("CITYBEGIN");//2
            cityendid=resultSet.getInt("CITYEND");//3
            name=resultSet.getString("NAME");//1
            cost=resultSet.getFloat("COST");//4
            date = simpleDateFormat.parse(resultSet.getString("BEGININGDAY"));//6
            arrhours= resultSet.getInt("ARRHOURS");//7
            arrminutes= resultSet.getInt("ARRMINUTES");//9
            timeflight=resultSet.getInt("TIMEFLYINGMIN");//8-set flight time (minutes)


            //Add data to variable
            element.add(id);
            element.add(name);
            element.add(citybegid);
            element.add(cityendid);
            element.add(cost);
            element.add(date);
            element.add(arrhours);
            element.add(arrminutes);
            element.add(timeflight);
            size++;
            //Add list to result
            res.add(element);

        }
        //flush resources
        resultSet.close();

        return res;
    }


    //Select flight into database by his id
    public Flight getFlightById(int ids) throws SQLException, ParseException {
        ResultSet rs = st.executeQuery("SELECT * FROM Tow.FLIGHT WHERE FLIGHT.ID="+ids);
        Flight f=new Flight();
        while(rs.next())
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

            f.setName(rs.getString("NAME"));
            f.setId(rs.getInt("ID"));
            f.setCost(rs.getFloat("COST"));
            f.setIdcitybeg(rs.getInt("CITYBEGIN"));
            f.setIdcityend(rs.getInt("CITYEND"));
            f.setHour(rs.getInt("ARRHOURS"));
            f.setMinut(rs.getInt("ARRMINUTES"));
            f.setTime(rs.getInt("TIMEFLYINGMIN"));
            f.setDate(sdf.parse(rs.getString("BEGININGDAY")));

        }
        rs.close();
        return f;
    }


    //Add flight into database
    public void addFlight(String name,int citybegin, int cityend,float cost,String datbegin,
                          int hrs,int mins,int timefl) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO FLIGHT (FLIGHT.NAME,FLIGHT.CITYBEGIN,FLIGHT.CITYEND,FLIGHT.COST," +
                "FLIGHT.ID,FLIGHT.BEGININGDAY,FLIGHT.ARRHOURS,FLIGHT.TIMEFLYINGMIN,FLIGHT.ARRMINUTES) VALUES(?,?,?,?,?,?,?,?,?)");
        ps.setString(1, name);
        ps.setInt(2, citybegin);
        ps.setInt(3, cityend);
        ps.setFloat(4, cost);
        ps.setInt(5, getMaxId(SELECT_ALL_FLIGHTS)+1);
        ps.setString(6, datbegin);
        ps.setInt(7, hrs);
        ps.setInt(8, timefl);
        ps.setInt(9, mins);
        ps.executeUpdate();
        ps.close();
    }

    //Edit flight in database
    public void editFlight(int flightId,String name,int citybegin, int cityend,float cost,String datbegin,
                           int hrs,int mins,int timefl) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE FLIGHT SET FLIGHT.NAME,FLIGHT.CITYBEGIN,FLIGHT.CITYEND,FLIGHT.COST," +
                " FLIGHT.ID,FLIGHT.BEGININGDAY,FLIGHT.ARRHOURS,FLIGHT.TIMEFLYINGMIN,FLIGHT.ARRMINUTES=?,?,?,?,?,?,?,? WHERE ID=?");
        ps.setString(1, name);
        ps.setInt(2, citybegin);
        ps.setInt(3, cityend);
        ps.setFloat(4, cost);
        ps.setString(5, datbegin);
        ps.setInt(6, hrs);
        ps.setInt(7, mins);
        ps.setInt(8, timefl);
        ps.setInt(9, flightId);
        ps.executeUpdate();
        ps.close();
    }

    //Delete flight from database
    public void deleteFlightByID(int ids) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM FLIGHT WHERE FLIGHT.ID=?");
        ps.setInt(1, ids);
        ps.executeUpdate();
        ps.close();
    }
}
