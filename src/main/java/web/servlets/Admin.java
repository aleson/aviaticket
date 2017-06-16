package web.servlets;
import dbmod.ReservTripTable;
import dbmod.UsersTable;
import usermod.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Admin extends HttpServlet {

    private User user;
    private String name;
    private List<User> array;

    private int userid; //fot clients
    private String editname;
    private float editmoney;
    private String editlogin;
    private String editpassword;
    private int role;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UsersTable ut=new UsersTable();
            array=ut.getAllUsersFromDB();
            ut.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size",array.size());
        req.setAttribute("array",array);
        req.getRequestDispatcher("adminpanel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //====================to clients==========================================
        //========================================================================
        //========================================================================

        String str=req.getParameter("x");
        String but1=req.getParameter("del");
        if(str!=null) {
            req.setAttribute("ednum", Integer.parseInt(str));
        }
        try {
            UsersTable ut=new UsersTable();
            array=ut.getAllUsersFromDB();
            ut.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size",array.size());
        req.setAttribute("array",array);

        if(str!=null) {
            if (but1 != null) { //delete user
                if (Integer.parseInt(str) > 1) { //1 - admin number
                    try {
                        UsersTable ut = new UsersTable();
                        ut.deleteUserByID(Integer.parseInt(str));
                        ut.closeConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String buf=req.getParameter("re");
        if(buf!=null)
        {

            userid=Integer.parseInt(req.getParameter("1"));
            editname=req.getParameter("2");
            editmoney=Float.parseFloat(req.getParameter("3"));
            editlogin=req.getParameter("4");
            editpassword=req.getParameter("5");
            role=Integer.parseInt(req.getParameter("6"));

            try{
                UsersTable ut = new UsersTable();
                //id fio money login pass role
                if(role>0 && role<3) {
                    ut.editUser(userid, editname, editmoney, editlogin, editpassword, role);
                }
                ut.closeConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("adminpanel.jsp").forward(req, resp);

    }
}
