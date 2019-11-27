//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author gutsman214
// */
//public class AdminServlet extends HttpServlet
//{
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        int customerId = request.getParameter("customerId");
//        int storeId = request.getParameter("storeId");
//        String firstName = request.getParameter("first_name");
//        String lastName = request.getParameter("last_name");
//        String email = request.getParameter("email");
//        String username = request.getParameter("username");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "nbuser");
//            PreparedStatement pst = conn.prepareStatement("SELECT customer_id, store_id, first_name, last_name, email, username FROM customer WHERE username=?");
//            pst.setString(1, customerId);
//            pst.setString(2, storeId);
//            pst.setString(3, firstName);
//            pst.setString(4, lastName);
//            pst.setString(5, email);
//            pst.setString(6, username);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                out.println("Correct customer credentials");
//            } else {
//                out.println("Incorrect customer credentials");
//
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            out.println("Database Connection Not Established");
//        }
//    }
//}
