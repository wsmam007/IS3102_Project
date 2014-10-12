package A6_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.SupplierEntity;
import EntityManager.Supplier_ItemEntity;
import SCM.SupplierManagement.SupplierManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SupplierItemInfoManagement_Servlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;
    @EJB
    private SupplierManagementBeanLocal supplierManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String errMsg = request.getParameter("errMsg");
            String goodMsg = request.getParameter("goodMsg");
            List<Supplier_ItemEntity> listOfSupplierItemInfo = itemManagementBean.listAllSupplierItemInfo();
            session.setAttribute("listOfSupplierItemInfo", listOfSupplierItemInfo);
            List<String> listOfSKUs = itemManagementBean.listAllItemsSKU();
            session.setAttribute("listOfSKUs", listOfSKUs);
            List<SupplierEntity> listOfSuppliers =  supplierManagementBean.viewAllSupplierList();
            session.setAttribute("listOfSuppliers", listOfSuppliers);

            if (errMsg == null && goodMsg == null) {
                response.sendRedirect("A6/supplierItemInfoManagement.jsp");
            } else if ((errMsg != null) && (goodMsg == null)) {
                if (!errMsg.equals("")) {
                    response.sendRedirect("A6/supplierItemInfoManagement.jsp?errMsg=" + errMsg);
                }
            } else if ((errMsg == null && goodMsg != null)) {
                if (!goodMsg.equals("")) {
                    response.sendRedirect("A6/supplierItemInfoManagement.jsp?goodMsg=" + goodMsg);
                }
            }
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
            response.sendRedirect("A6/supplierItemInfoManagement.jsp?errMsg=An error has occured, please try again.");
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