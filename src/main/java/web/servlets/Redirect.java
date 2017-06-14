package web.servlets;


import defmod.Autentification;
import usermod.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Redirect extends HttpServlet{

    static {
        Random rand=new Random(); //for generate id session
        Integer i=rand.nextInt();
        sessionId=i.toString();
    }

    private static int count=0;//counter requests for page
    private static final String sessionId;

    private static String password;
    private static String login;
    private static boolean isError;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add value to variable
        count++;
        login=req.getParameter("username");
        password=req.getParameter("password");
        //validates
            Autentification aut = new Autentification();
            User user = aut.findUser(login, password);
        if(user!=null){
        if(user.getLogin().equals(login) && user.getPass().equals(password)) {

            req.setAttribute("current_count", count);
            req.setAttribute("name",user.getFio());
            req.setAttribute("money",user.getMoney());
            req.setAttribute("sessionId",sessionId);
            req.setAttribute("role",user.getRole());

            isError=false;
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }
        else{
            isError=true;
            req.setAttribute("isError",isError);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        }
        else{
            isError=true;
            req.setAttribute("isError",isError);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }



}
