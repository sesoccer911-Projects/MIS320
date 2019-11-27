
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gutsman214
 */
public class CustomerUpdateServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("output");
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "nbuser");
            String s = "update customer set first_name = \""+ fName + "\", last_name = \"" + lName +"\", email = \"" + email +"\", password = \"" + password+
                    "\" WHERE username= \"" + username + "\"";
            System.out.println(s);
            PreparedStatement pst = conn.prepareStatement("update customer set first_name = \""+ fName + "\", last_name = \"" + lName +"\", email = \"" + email +"\", password = \"" + password+
                    "\" WHERE username= \"" + username + "\"");
            int rs = pst.executeUpdate();
            if (rs > 0) {
                response.sendRedirect("account.jsp");
            } else {
                out.println("Incorrect credentials");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Database Connection Not Established");
        }
    }
    
    
}
