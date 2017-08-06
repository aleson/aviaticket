package web.servlets;

import dbmod.AeroportsTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Airadmin extends HttpServlet {

    private List arraycities;
    private int cityid; //for cities
    private String cityname;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AeroportsTable at=new AeroportsTable();
            arraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size2",arraycities.size());
        req.setAttribute("arraycities",arraycities);
        req.getRequestDispatcher("adminpanel2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String str2=req.getParameter("x2");
        String but12=req.getParameter("addcity");
        String but32=req.getParameter("delcity");
        if(str2!=null) {
            req.setAttribute("citynum", Integer.parseInt(str2));
        }
        try {
            AeroportsTable at=new AeroportsTable();
            arraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size2",arraycities.size());
        req.setAttribute("arraycities",arraycities);
        if(str2!=null) {
            if (but32 != null) { //delete airports
                try {
                    AeroportsTable at=new AeroportsTable();
                    at.deleteAeroportByID(Integer.parseInt(str2));
                    at.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(req.getParameter("_1")!=null && req.getParameter("_2")!=null)
        cityid=Integer.parseInt(req.getParameter("_1"));
        cityname=req.getParameter("_2");
        if(req.getParameter("_1")!=null && req.getParameter("_2")!=null){ //edit airports
            try{
                AeroportsTable at=new AeroportsTable();
                at.editAeroport(cityid,cityname);
                at.closeConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(but12!=null){ //add airports
            try{
                AeroportsTable at=new AeroportsTable();
                at.addAeroport(cityname);
                at.closeConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("adminpanel2.jsp").forward(req, resp);
    }
}
