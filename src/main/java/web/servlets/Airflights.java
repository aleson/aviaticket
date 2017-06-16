package web.servlets;
import dbmod.AeroportsTable;
import dbmod.FlightsTable;
import privatecabinetmod.Flight;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Airflights extends HttpServlet{

    private List<Flight> arrayflights;
    private int flightid; //for flights
    private String flightname;
    private int cibegin; //number city
    private int ciend;   //number city
    private float cost;
    private int hrs;
    private Date date;
    private String sdate;
    private int mins;
    private int time;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method");
        req.setCharacterEncoding("UTF-8");

        //====================to clients==========================================
        //========================================================================
        //========================================================================
        List arraycities=null;
        try{

            AeroportsTable at=new AeroportsTable();
            arraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size2",arraycities.size());
        req.setAttribute("arraycities",arraycities);



        String str3=req.getParameter("x3");
        String but13=req.getParameter("addflight");
        String but33=req.getParameter("delflight");
        if(str3!=null) {
            req.setAttribute("flightnum", str3);
        }
        try {
            FlightsTable ft=new FlightsTable();
            arrayflights=ft.getAllFlightsFromDB();
            ft.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size3",arrayflights.size());
        req.setAttribute("arrayflights",arrayflights);

        if(str3!=null) {
            if (but33 != null) { //delete flights
                try {
                    FlightsTable ft=new FlightsTable();
                    ft.deleteFlightByID(Integer.parseInt(str3));
                    ft.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(req.getParameter("+1")!=null){ //edit flights

            flightid = Integer.parseInt(req.getParameter("+1"));
            flightname = req.getParameter("+2");
            cibegin = Integer.parseInt(req.getParameter("+3"));
            ciend = Integer.parseInt(req.getParameter("+4"));
            cost = Float.parseFloat(req.getParameter("+5"));
            sdate = req.getParameter("+6");
            hrs = Integer.parseInt(req.getParameter("+7"));
            mins = Integer.parseInt(req.getParameter("+8"));
            time = Integer.parseInt(req.getParameter("+9"));

            try {
                FlightsTable ft = new FlightsTable();
                ft.editFlight(flightid,flightname,cibegin,ciend,cost,ft.setDate(sdate),hrs,mins,time);
                ft.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(but13!=null){ //add flights
            try{
                FlightsTable ft=new FlightsTable();
                ft.addFlight(flightname,cibegin,ciend,cost,ft.setDate(sdate),hrs,mins,time);
                ft.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("adminpanel3.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get method");

        try {
            FlightsTable ft=new FlightsTable();
            arrayflights=ft.getAllFlightsFromDB();
            ft.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(arrayflights);
        req.setAttribute("list_size3",arrayflights.size());
        req.setAttribute("arrayflights",arrayflights);


        List arraycities=null;
        try{

            AeroportsTable at=new AeroportsTable();
            arraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list_size2",arraycities.size());
        req.setAttribute("arraycities",arraycities);

        req.getRequestDispatcher("adminpanel3.jsp").forward(req, resp);
    }
}
