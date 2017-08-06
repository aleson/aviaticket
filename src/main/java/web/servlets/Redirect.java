package web.servlets;


import defmod.Autentification;
import usermod.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Redirect extends HttpServlet{

    private static int count=0;//counter requests for page
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
        //data transport
        if(user!=null){
            if(user.getLogin().equals(login) && user.getPass().equals(password)) {
                HttpSession session = req.getSession(true);

                session.setAttribute("current_count", count);
                session.setAttribute("name",user.getFio());
                session.setAttribute("money",user.getMoney());
                session.setAttribute("role",user.getRole());
                session.setAttribute("id_user",user.getId());


                session.setAttribute("sessionIdx", login);//main session create from login
                session.setMaxInactiveInterval(5*60);//5 minutes
                isError=false;
                req.getRequestDispatcher("under.jsp").forward(req, resp);
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
