package dbmod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class AeroportsTable extends DBconnect {
    public AeroportsTable() throws SQLException {

    }

    private String city;
    private String id;


    //Select all airports
    public Vector getAllAeroportsFromDB() throws SQLException
    {
        //Variable-list
        Vector res = new Vector();

        String query = SELECT_ALL_AEROPORTS;
        //Running query
        ResultSet resultSet2 = st.executeQuery(query);
        //ResultSet resultSet2 = pst.executeQuery(query);

        while(resultSet2.next())
        {
            //Create new List-variable
            Vector element = new Vector();

            id = resultSet2.getString("ID"); //1-Since an SQLException: "Fail to convert to internal representation" appears
            city=resultSet2.getString("CITY"); //2-then id have type String
            // Добавляем по порядку
            element.add(id);
            element.add(city);


            //Add list to result
            res.add(element);
        }
        //Free resources
        resultSet2.close();
        st.close();
        //pst.close();
        conn.close();
        return res;
    }

    //Add airport in database
    public void addAeroport(String airport) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO AEROPORTS (AEROPORTS.ID,AEROPORTS.CITY) VALUES (? , ?)");
        ps.setString(1, Integer.toString(getMaxId(SELECT_ALL_AEROPORTS)+1));
        ps.setString(2, airport);
        ps.executeUpdate();
        ps.close();
    }
    //Delete airport from database by his id
    public void deleteAeroportByID(int ids) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM AEROPORTS WHERE AEROPORTS.ID=?");
        ps.setInt(1, ids);
        ps.executeUpdate();
        ps.close();
    }

    //Edit airport into database by his id
    public void editAeroport(int airportId,String city) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE AEROPORTS SET CITY=? WHERE ID=?");
        ps.setString(1,city);
        ps.setInt(2,airportId);
        ps.executeUpdate();
        ps.close();
    }

}
