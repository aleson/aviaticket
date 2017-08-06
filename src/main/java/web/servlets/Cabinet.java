package web.servlets;

import dbmod.AeroportsTable;
import dbmod.FlightsTable;
import dbmod.ReservTripTable;
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
        req.setCharacterEncoding("UTF-8");
        ArrayList cabarraycities=null;
        ArrayList onlycities=new ArrayList();
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
        if(session.getAttribute("id_user")==null) resp.sendRedirect("index.jsp");
        cuid=Integer.parseInt(session.getAttribute("id_user").toString());

        viewFlightsReserved(onlycities,req);


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

        if(session.getAttribute("id_user")==null) resp.sendRedirect("index.jsp");
        cuid=Integer.parseInt(session.getAttribute("id_user").toString());


        //--------------------------------------------------------------------------------------------------------------
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

        viewFlightsReserved(onlycities,req);


        isError=false;

        String butcab2=req.getParameter("create_optimal_time");
        String butcab3=req.getParameter("create_optimal_cost");
        String butcab4=req.getParameter("create_optimal_order");
        int isOptimal=-1;                                             // -1 -error 0 - time 1 - cost
        if (butcab2 != null) {
            isOptimal=0;
        }
        if(butcab3!=null){
            isOptimal=1;
        }
        //==============================================================================================================

        if(user!=null && cfid!=-1){
            PrivateCabinet pc = new PrivateCabinet(user);
            if(butcab!=null) {                                   //create order for direct flight between two cities
                System.out.println("butcab standart");
                if(pc.createStandartOrder(cfid)==false){
                    isError=true;
                }else isError=false;
            }
            System.out.println("createoptorder (error)="+isError+"array[0], array[1]="+array[0] +" "+ array[1]);
            //---------------------------------------------------Optimal view-------------------------------------------
            if (butcab4!=null) {
                System.out.println("butcab view");
                String viewpath = "";
                List path = null;//for view path
                path = pc.GetOptimalTimePath(array[0], array[1]);
                for (int i = 0; i < path.size(); i++) {
                    viewpath += onlycities.get(Integer.parseInt(path.get(i).toString())-1) + " ";
                    if (i != path.size() - 1) viewpath += "-> ";
                }
                req.setAttribute("path", viewpath);
                String viewpath2 = "";
                List path2 = null;
                path2 = pc.GetOptimalCostPath(array[0], array[1]);
                for (int i = 0; i < path2.size(); i++) {
                    viewpath2 += onlycities.get(Integer.parseInt(path2.get(i).toString())-1) + " ";
                    if (i != path2.size() - 1) viewpath2 += "-> ";
                }
                req.setAttribute("path2", viewpath2);
            }

            if(butcab3!=null) { //branch cost
                System.out.println("butcab cost");
                if (pc.createOptimalCostOrder(array[0], array[1]) == false) {   //create optimal cost order
                    isError = true;
                }else isError=false;
            }
            System.out.println("createoptcost (error)=" + isError + "array[0], array[1]=" + array[0] + " " + array[1]);
            if(butcab2!=null){
                System.out.println("butcab time");
                if (pc.createOptimalTimeOrder(array[0], array[1]) == false) {   //create optimal time order
                    isError = true;
                }else isError=false;
            }
            System.out.println("createopttime (error)=" + isError + "array[0], array[1]=" + array[0] + " " + array[1]);

        }else isError=true;

        //--------------------------------------------------------------------------------------------------------------


        req.setAttribute("caberror",isError);
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }

    private void viewFlightsReserved(ArrayList onlycities,HttpServletRequest req)
    {
        //Get table orders
        //--------------------------------------------------------------------------------------------------------------

        ArrayList listreserves=null;
        ArrayList underreserve=new ArrayList();
        try {
            ReservTripTable rtt=new ReservTripTable();
            listreserves=rtt.getAllOrdersFromDB();
            rtt.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList tmp0;
        for(int i=0;i<listreserves.size();i++)
        {
            tmp0=(ArrayList)listreserves.get(i);
            if(Integer.parseInt(tmp0.get(1).toString())==cuid)//find flights our user
                underreserve.add(tmp0.get(2));
        }
        System.out.println(underreserve);//true
        ArrayList cabarrayorders=new ArrayList();
        ArrayList cabarrayorders0=new ArrayList();
        ArrayList goodorders=new ArrayList();

        try {
            FlightsTable ft=new FlightsTable();
            cabarrayorders0=ft.getAllFlightsFromDB();
            ft.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i=0;i<underreserve.size();i++){
            int tmp=Integer.parseInt(underreserve.get(i).toString());
            cabarrayorders.add(cabarrayorders0.get(tmp-1)); //create list with client flights
        }

        ArrayList tmp2;
        ArrayList x1=new ArrayList();
        ArrayList x2=new ArrayList();
        ArrayList x3=new ArrayList();
        ArrayList x4=new ArrayList();
        for(int i=0;i<cabarrayorders.size();i++){
            tmp2 = (ArrayList)cabarrayorders.get(i);
            int tmp3=Integer.parseInt(tmp2.get(2).toString());
            int tmp4=Integer.parseInt(tmp2.get(3).toString());
            x1.add(tmp2.get(1));//name flight
            x2.add(onlycities.get(tmp3-1));//depart city
            x3.add(onlycities.get(tmp4-1));//arrive city
            x4.add(tmp2.get(8));//time flight
            goodorders.add("Name: "+x1.get(i).toString()+" | " + x2.get(i)+ " -> " + x3.get(i)+ " time: "+x4.get(i).toString());
        }
        System.out.println(goodorders);
        req.setAttribute("cabarrayorders",goodorders);



        //--------------------------------------------------------------------------------------------------------------
    }
}
