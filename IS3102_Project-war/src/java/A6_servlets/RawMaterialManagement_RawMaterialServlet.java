package A6_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.RawMaterialEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RawMaterialManagement_RawMaterialServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session;
            session = request.getSession();
            String errMsg = request.getParameter("errMsg");
            String goodMsg = request.getParameter("goodMsg");

            List<RawMaterialEntity> rawMaterialList = itemManagementBean.listAllRawMaterials();
            session.setAttribute("rawMaterialList", rawMaterialList);

            if (errMsg == null && goodMsg == null) {
                response.sendRedirect("A6/rawMaterialManagement.jsp");
            } else if ((errMsg != null) && (goodMsg == null)) {
                if (!errMsg.equals("")) {
                    response.sendRedirect("A6/rawMaterialManagement.jsp?errMsg=" + errMsg);
                }
            } else if ((errMsg == null && goodMsg != null)) {
                if (!goodMsg.equals("")) {
                    response.sendRedirect("A6/rawMaterialManagement.jsp?goodMsg=" + goodMsg);
                }
            }
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
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
