import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sesoc
 */
public class CartServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private static String LIST_PRODUCT = "cart.jsp";
    private CartDao dao;

        public CartServlet() {
        super();
        dao = new CartDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";

        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equalsIgnoreCase("delete")) {
            int cartItem_id = Integer.parseInt(request.getParameter("cartItem_id"));
            dao.deleteProduct(cartItem_id);
            forward = LIST_PRODUCT;
            //request.setAttribute("products", dao.getAllProducts());
        } else {
            forward = LIST_PRODUCT;
        }

        //fowards it to the specific page
        RequestDispatcher view = request.getRequestDispatcher(forward);

        view.forward(request, response);
    }
}
