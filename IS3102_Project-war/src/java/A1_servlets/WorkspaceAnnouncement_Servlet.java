/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1_servlets;

import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neo
 */
public class WorkspaceAnnouncement_Servlet extends HttpServlet {

    @EJB
    private WorkspaceBeanLocal workspaceBeanLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String result;
        PrintWriter out = response.getWriter();
        try {
            String sender = request.getParameter("sender");
            String title = request.getParameter("title");
            String message = request.getParameter("message");
            Long expiryDateLong = Date.parse(request.getParameter("expiryDate"));
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(expiryDateLong);
            Date expiryDate = cal.getTime();
            if (workspaceBeanLocal.makeAnnouncement(sender, title, message, expiryDate)) {
                result = "?errMsg=Announcement broadcasted.";
                response.sendRedirect("A1/workspace_BroadcastAnnouncement.jsp" + result);
            } else {
                result = "?errMsg=Failed to broadcast announcement.";
                response.sendRedirect("A1/workspace_BroadcastAnnouncement.jsp" + result);
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
