/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.Session;

/**
 *
 * @author gutsman214
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable
{   short customerId;
    DataModel CustomerInfo;
    CustomerHelper helper;
    
    private Customer current;
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController()
    {
        helper = new CustomerHelper();
        customerId =1;
    }
     public CustomerController(short customerId)
    {
        helper = new CustomerHelper();
        this.customerId = customerId;
    }
     
     public Customer getSelected() {
        if (current == null) {
            current = new Customer();
        }
        return current;
    }
     
     public DataModel getCustomersByID() {
        if (CustomerInfo == null) {
            CustomerInfo = new ListDataModel(helper.getCustomersByID(customerId));
        }
        return CustomerInfo;
    }

    void recreateModel() {
        CustomerInfo = null;
    }
    
    // Retrieves details about the customer
    public Payment getPayment() {
        //int paymentID = current.getPaymentsByPaymentId().getPaymentId().intValue();
        Payment payment = helper.getPaymentbyID(customerId);
        return payment;
    }
    
    public String getRental(){
    List rentals = (List) helper.getRentalByID(current.getCustomerId());
    StringBuffer rentalHistory = new StringBuffer();
    for (int i = 0; i < rentals.size(); i++) {
            Rental rental = (Rental) rentals.get(i);   
        }
    return rentalHistory.toString();
    }
    
/*    public WishList getWishList(){
      List wishlist = (List) helper.getWishListbyID(current.getCustomerId());
    StringBuffer wishList = new StringBuffer();
    for int i = 0; i < wishlist.size(); i++){
            WishList wishlist = (WishList) wishlist.get(i);
    }
    return wishList.toString();
    }
*/    
}
