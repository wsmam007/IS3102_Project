package A3_servlets;

import SCM.RetailProductsAndRawMaterialsPurchasing.RetailProductsAndRawMaterialsPurchasingBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseOrderManagement_AddServlet extends HttpServlet {

    @EJB
    private RetailProductsAndRawMaterialsPurchasingBeanLocal retailProductsAndRawMaterialsPurchasingBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String supplierId = request.getParameter("supplier");
            String destinationId = request.getParameter("destination");
            String expectedDate = request.getParameter("expectedDate");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(expectedDate);

//            if (supplierId != null && destinationId != null) {
//                boolean canUpdate = retailProductsAndRawMaterialsPurchasingBean.createPurchaseOrder(Long.parseLong(supplierId), Long.parseLong(destinationId), date);
//                if (!canUpdate) {
//                    result = "?errMsg=Type already exist. Please try again.";
//                    response.sendRedirect("A3/transferOrderManagement_Add.jsp" + result);
//                } else {
//                    result = "?errMsg=Storage Bin added successfully.&id=" + warehouseEntity.getWarehouseName();
//                    response.sendRedirect("TransferOrderManagement_Servlet" + result);
//                }
//
//            }
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
