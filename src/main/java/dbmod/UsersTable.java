package dbmod;

import usermod.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UsersTable extends DBconnect {


    private String name,login,pass;
    private Integer id;
    private float money;
    private int role;

    public UsersTable() throws SQLException {

    }


    //from DB select all users
    public Vector getAllUsersFromDB() throws SQLException
    {
        //Variable with result
        Vector result = new Vector();

        String query = SELECT_ALL_USERS;
        //Running query
        ResultSet resultSet = st.executeQuery(query);

        while(resultSet.next())
        {
            // Add new list-variable <id, name,money,login,pass>
            Vector element = new Vector();


            id = resultSet.getInt("ID");//3
            name = resultSet.getString("NAME");//1
            money=resultSet.getFloat("MONEY");//2
            login=resultSet.getString("LOGIN");//4
            pass=resultSet.getString("PASSWORD");//5
            role=resultSet.getInt("ROLE");//6
            // Add to list
            element.add(id);
            element.add(name);
            element.add(money);
            element.add(login);
            element.add(pass);
            element.add(role);


            // Add list to result
            result.add(element);
        }

        //flush resources
        resultSet.close();

        return result;
    }

    //Select user into database by his id
    public User getUserById(int ids) throws SQLException{
        ResultSet rs = st.executeQuery("SELECT * FROM Tow.USERS WHERE USERS.ID="+ids);
        User u=new User();
        while(rs.next())
        {
            u.setFio(rs.getString("NAME"));
            u.setId(rs.getInt("ID"));
            u.setMoney(rs.getFloat("MONEY"));
            u.setLogin(rs.getString("LOGIN"));
            u.setPass(rs.getString("PASSWORD"));
            u.setRole(rs.getInt("ROLE"));

        }
        rs.close();
        return u;
    }

    //Add user in database
    public void addUser(User user) throws SQLException{
        user.setId(getMaxId(SELECT_ALL_USERS)+1);//increment counter maximal column's index
        count=0;

        PreparedStatement ps = conn.prepareStatement("INSERT INTO USERS (USERS.NAME,USERS.MONEY,USERS.ID,USERS.LOGIN,USERS.PASSWORD,USERS.ROLE) VALUES (?,?,?,?,?,?)");
        ps.setString(1, user.getFio());
        ps.setFloat(2, user.getMoney());
        ps.setInt(3, getMaxId(SELECT_ALL_USERS) + 1);
        ps.setString(4, user.getLogin());
        ps.setString(5, user.getPass());
        ps.setInt(6, user.getRole());
        ps.executeUpdate();
        ps.close();
    }

    //Delete user from database by his id
    public void deleteUserByID(int ids) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM USERS WHERE USERS.ID=?");
        ps.setInt(1, ids);
        ps.executeUpdate();
        ps.close();

    }

    //Edit user into database by his id
    public void editUser(int userId,String fio,float money,String login,String pass,int _role) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE USERS SET NAME=?, MONEY=?, LOGIN=?, PASSWORD=?, ROLE=? WHERE ID=?");
        ps.setString(1,fio);
        ps.setFloat(2,money);
        ps.setString(3,login);
        ps.setString(4,pass);
        ps.setInt(5,_role);
        ps.setInt(6, userId);
        ps.executeUpdate();
        ps.close();
    }


}
