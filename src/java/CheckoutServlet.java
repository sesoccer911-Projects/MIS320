/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brandon
 */
@WebServlet(urlPatterns =
{
    "/CheckoutServlet"
})
public class CheckoutServlet extends HttpServlet
{

    public static java.sql.Timestamp convertToSqlDateTime(Date utilDate)
    {
        return new java.sql.Timestamp(utilDate.getTime());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String paypalID = request.getParameter("pUser");
        String username = request.getParameter("username1");
        System.out.println(paypalID + username);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "nbuser");
            PreparedStatement pr = con.prepareStatement("select customer_id from customer where username = \"" + username + "\"");
            ResultSet rs = pr.executeQuery();
            int custId = 0;
            while (rs.next())
            {
                custId = rs.getInt("customer_id");
            }
            PreparedStatement pre = con.prepareStatement("select cart_id from cart where customer_id = " + custId);
            ResultSet res = pre.executeQuery();
            int cartId = 0;
            while (res.next())
            {
                cartId = res.getInt("cart_id");
            }
            PreparedStatement prep = con.prepareStatement("select film_id from cartofitems where cart_id = " + cartId);
            ResultSet resu = pre.executeQuery();
            List<Integer> cartofitems = new ArrayList<Integer>();
            while (resu.next())
            {
                cartofitems.add(resu.getInt("cart_id"));
            }
            for (int i = 0; i < cartofitems.size(); i++)
            {
                
                String query = "INSERT INTO transaction(paypal_id, customer_id, film_id)"
                        + " values (\"" + paypalID + "\"," + custId + "," + cartofitems.get(i) +")";
                System.out.println(query);
                PreparedStatement preparedStmt = con.prepareStatement(query);
                //preparedStmt.setString(1, paypalID);
                //preparedStmt.setInt(2, custId);
                //preparedStmt.setInt(3, cartofitems.get(i));
                // execute the preparedstatement
                preparedStmt.executeUpdate();
            }
            con.close();

        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            out.println("Database Connection Not Established");
        }
        // if (transactionID != null) {
        //session.setAttribute("userid", user);
        //response.sendRedirect("login.jsp");
        out.print("Checkout Successfull!" + "<a href='index.xhtml'>Go to Search Page</a>");
    }
}

