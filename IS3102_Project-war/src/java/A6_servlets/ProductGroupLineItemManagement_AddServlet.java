package A6_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.ProductGroupLineItemEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductGroupLineItemManagement_AddServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal ItemManagementBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String productGroupId = request.getParameter("id");
            String sku = request.getParameter("sku");
            String percent = request.getParameter("percent");

            ProductGroupLineItemEntity productGroupLineItemEntity = ItemManagementBean.createProductGroupLineItem(sku, Double.parseDouble(percent));
            boolean canUpdate = false;
            if (productGroupLineItemEntity != null) {
                canUpdate = ItemManagementBean.addLineItemToProductGroup(Long.parseLong(productGroupId), productGroupLineItemEntity.getId());
            }
            if (!canUpdate) {
                result = "?errMsg=Product group or SKU not found.&id=" + productGroupId;
                response.sendRedirect("A6/productGroupManagement_AddLineItem.jsp" + result);
            } else {
                result = "?errMsg=Line item added successfully.&id=" + productGroupId;
                response.sendRedirect("ProductGroupLineItemManagement_Servlet" + result);
            }

        } catch (Exception ex) {
            out.println(ex);
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