package web.servlets;

import dbmod.UsersTable;
import usermod.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends HttpServlet {

    private static boolean isError;
    private static String name;
    private static String login;
    private static String password;
    private static float money;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.getContentType());
        req.setCharacterEncoding("UTF-8");
        System.out.println(resp.getContentType());
        //add value to variable
        name=req.getParameter("nameR");
        login=req.getParameter("loginR");
        password=req.getParameter("passwordR");
        Pattern p=Pattern.compile("[0-9]+");
        Matcher n = p.matcher(req.getParameter("moneyR"));
        if(n.matches()==false){
            money = -404;

        }
        else {
            money = Float.parseFloat(req.getParameter("moneyR"));
        }
        //validates

        if(money!=-404) {

            User user=new User();
            user.setFio(name);
            user.setLogin(login);
            user.setPass(password);
            user.setMoney(money);
            user.setRole(1);

            try {
                UsersTable utt=new UsersTable();
                utt.addUser(user);
                utt.closeConnection();
                isError=false;
                req.setAttribute("isError2",isError);
                req.getRequestDispatcher("index.jsp").forward(req, resp);

            } catch (SQLException e) {
                e.printStackTrace();
                isError=true;
                req.setAttribute("isError2",isError);
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
            }


        }
        else{
                isError=true;
                req.setAttribute("isError2",isError);
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }

}
