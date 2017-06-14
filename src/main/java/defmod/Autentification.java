package defmod;

import dbmod.UsersTable;
import usermod.User;

import java.sql.SQLException;

//this class work with servlet. When class find user he send User to servlet
public class Autentification {
    public UsersTable utt;
    private User user;
    private boolean exist;
    private static int user_count;

    public User findUser(String login, String password) {
        try {
            UsersTable utt = new UsersTable();
            user_count=utt.getAllUsersFromDB().size();
            utt.closeConnection();
            for(int i=1;i<=user_count;i++)
            {
                UsersTable utt2 = new UsersTable();
                user = utt2.getUserById(i);
                utt2.closeConnection();
                if(user.getLogin().equals(login) && user.getPass().equals(password))
                {
                    return user; //return link to User
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Get user by id exception");

            }
        return null;
    }

    public User getUser(String login,String password){
        user=findUser(login,password);
        return user;
    }
}
