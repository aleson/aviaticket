package web.servlets;

import dbmod.AeroportsTable;
import dbmod.FlightsTable;
import dbmod.UsersTable;
import privatecabinetmod.Airport;
import privatecabinetmod.PrivateCabinet;
import usermod.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Cabinet extends HttpServlet {




    private int array[]=new int[2];//for cities
    private int cuid;//id user
    private int cfid;//id flight
    private boolean isError;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList cabarraycities=null;
        ArrayList onlycities=new ArrayList();
        req.setCharacterEncoding("UTF-8");
        try {
            AeroportsTable at=new AeroportsTable();
            cabarraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList tmp;
        for(int i=0;i<cabarraycities.size();i++){
            tmp = (ArrayList)cabarraycities.get(i);
            onlycities.add(tmp.get(1));
        }
        HttpSession session = req.getSession(true);
        session.getAttribute("id_user");
        cuid=Integer.parseInt(session.getAttribute("id_user").toString());
        User user=null;
        float money=0;
        try {
            UsersTable ut=new UsersTable();
            user=ut.getUserById(cuid);
            ut.closeConnection();
            money=user.getMoney();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("xcumoney", money);//refresh balance
        req.setAttribute("cabarraycities",onlycities);
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList cabarraycities=null;//variable create locale, that list cities not added yourself
        ArrayList onlycities=new ArrayList();

        req.setCharacterEncoding("UTF-8");

        try {
            AeroportsTable at=new AeroportsTable();
            cabarraycities=at.getAllAeroportsFromDB();
            at.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList tmp;
        for(int i=0;i<cabarraycities.size();i++){
            tmp = (ArrayList)cabarraycities.get(i);
            onlycities.add(tmp.get(1));
        }
        req.setAttribute("cabarraycities",onlycities);
        String depart=req.getParameter("depart");//city depart
        String arrive=req.getParameter("arrive");//city arrive
        String butcab=req.getParameter("create_order");//create orders

        String str=depart;
        for(int i=0; i<2;i++){
            for(int j=0;j<onlycities.size();j++)
            {
                if(onlycities.get(j).equals(str)){
                    System.out.println(str);
                    array[i]=j+1;
                }
            }
            str=arrive;
        }
        System.out.println("array[1,2]="+array[0]+","+array[1]);
        if(array[0]>array[1]) //this IF is born that do not create reverse flight
        {
            int temp=array[0];
            array[0]=array[1];
            array[1]=temp;
        }

        HttpSession session = req.getSession(true);
        session.getAttribute("id_user");
        cuid=Integer.parseInt(session.getAttribute("id_user").toString());

        User user=null;
        float money=0;
        try {
            UsersTable ut=new UsersTable();
            user=ut.getUserById(cuid);
            ut.closeConnection();
            money=user.getMoney();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("xcumoney", money);

        if(array.length==2)
        try {
            FlightsTable ft = new FlightsTable();
            cfid=ft.getIdFlightByCities(array[0],array[1]);
            ft.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }

        array[0]=0;// not "FOR" - for faster speed
        array[1]=0;

        System.out.println("cfid="+cfid+" cuid="+cuid);//cfid!=-1 -isError
        isError=false;


        if(user!=null && cfid!=-1 && butcab!=null) {  //create order for direct flight between two cities
            PrivateCabinet pc = new PrivateCabinet(user);
            if(pc.createStandartOrder(cfid)==false){
                isError=true;
            }

        }else isError=true;

        if(isError==false){

        }
        req.setAttribute("caberror",isError);
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }
}
