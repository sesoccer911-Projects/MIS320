/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dvdrental.FilmController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ben
 */
public class CartAddServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartAddServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FilmController f = new FilmController();
        String s = request.getParameter("output");
        String title = request.getParameter("title");
        try {
            System.out.println(s);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "nbuser");
            PreparedStatement pr = connection.prepareStatement("select customer_id from customer where username = \"" + s + "\"");
            ResultSet rs = pr.executeQuery();
            int custId = 0;
            while (rs.next()) {
                custId = rs.getInt("customer_id");             
            }
            System.out.println(custId);
            custId++;
            PreparedStatement pre = connection.prepareStatement("select cart_id from cart where customer_id = " + custId);
            ResultSet r = pre.executeQuery();
            int cartId = 0;
            while(r.next()){
                cartId = r.getInt("cart_id");
            }
            System.out.println(cartId);
            System.out.println(title);
            //Film add = f.prepareFilm();
            //PreparedStatement p = connection.prepareStatement("insert into cartofitems(film_id) VALUES(" +  add.getFilmId() + ")");
            //System.out.println(add.getTitle());
            PreparedStatement prep = connection.prepareStatement("select film_id from film where title = \"" + title + "\"");
            ResultSet result = prep.executeQuery();
            int filmId = 0;
            while(result.next()){
                filmId = result.getInt("film_id");
            }
            PreparedStatement p = connection.prepareStatement("insert into cartofitems(cart_id, film_id) VALUES(\"" + cartId + "\" , \"" + filmId + "\")");
            p.executeUpdate();
            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
