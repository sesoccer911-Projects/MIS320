


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sesoc
 */
public class CartDao {

    private Connection connection;

    public CartDao() {
        connection = DbConnectionUtil.getConnection();
    }

    public void deleteProduct(int cartItem_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM cartOfItems WHERE cartItem_id=?");

            String cartItem = "" + cartItem_id;
            preparedStatement.setString(1, cartItem);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Film> getAllProducts() {
//        List<Film> films = new ArrayList<Product>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM product");
//            while (rs.next()) {
//                Film film = new Product();
//                film.getFilmId(rs.getInt("order_num"));
//                film.getLanguageByLanguageId(rs.getInt("Customer_id"));
//                film.setProdID(rs.getInt("Product_id"));
//                film.setQuantity(rs.getInt("Quantity"));
//                film.setShippingCost(rs.getInt("Shipping_Cost"));
//                film.setSalesDate(rs.getDate("Sales_date"));
//                film.setShippingDate(rs.getDate("Shipping_Date"));
//                films.add(film);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return films;
//    }
}
