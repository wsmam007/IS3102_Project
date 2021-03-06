package B_servlets;
//###-->
import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ECommerce_MinusFurnitureToListServlet extends HttpServlet {

    private String URLprefix = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("ECommerce_MinusFurnitureToListServlet");
        try {
            Cookie[] cookies = request.getCookies();
            String email = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("memberId")) {
                        System.out.println("Cookie value : " + cookie.getValue());
                        email = cookie.getValue();
                    }
                }
            }

            HttpSession session = request.getSession();
            URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
            }

            String SKU = request.getParameter("SKU");
            System.out.println("SKU is : " + SKU);
            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");
            if (shoppingCart == null) {
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error reducing item quantity.");
            } else {
                for (ShoppingCartLineItem item : shoppingCart) {
                    System.out.println("SKU in shoppingcart: " + item.getSKU());
                    if (item.getSKU().equals(SKU)) {
                        System.out.println("item quantity: " + item.getQuantity());
                        if (item.getQuantity() > 1) {
                            System.out.println("quantity > 1: " + item.getQuantity());
                            item.setQuantity(item.getQuantity() - 1);
                        } else {
                            System.out.println("quantity <= 1: " + item.getQuantity());
                            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error. Quantity cannot be less than 1.");
                            return;
                        }
                        break;
                    }

                }
            }
            session.setAttribute("shoppingCart", shoppingCart);
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?goodMsg=Item quantity reduced successfully!");

        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error reducing item quantity.");
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
        processRequest(request, response);
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
