package web.servlets;

import dbmod.AeroportsTable;
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

public class Adminorder extends HttpServlet {

    private List arrayorders;
    private int flightid;
    private int userid;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            ReservTripTable rtt=new ReservTripTable();
            arrayorders=rtt.getAllOrdersFromDB();
            rtt.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size4",arrayorders.size());
        req.setAttribute("arrayorders",arrayorders);
        req.getRequestDispatcher("adminpanel4.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String str4=req.getParameter("x4");
        String but14=req.getParameter("addorder");
        String but34=req.getParameter("delorder");
        if(str4!=null) {
            req.setAttribute("ordernum", Integer.parseInt(str4));
        }
        try {
            ReservTripTable rtt=new ReservTripTable();
            arrayorders=rtt.getAllOrdersFromDB();
            rtt.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size4",arrayorders.size());
        req.setAttribute("arrayorders",arrayorders);
        if(str4!=null) {
            if (but34 != null) { //delete orders
                try {
                    AeroportsTable at=new AeroportsTable();
                    ReservTripTable rtt=new ReservTripTable();
                    rtt.deleteOrderByID(Integer.parseInt(str4));
                    rtt.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(req.getParameter("=2")!=null && req.getParameter("=3")!=null) { //edit orders
            userid= Integer.parseInt(req.getParameter("=2"));
            flightid= Integer.parseInt(req.getParameter("=3"));
        }
        if(but14!=null){ //add order
            try{
                ReservTripTable rtt=new ReservTripTable();
                rtt.addOrder(userid,flightid);
                rtt.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("adminpanel4.jsp").forward(req, resp);
    }
}
