package B_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import EntityManager.MemberEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ECommerce_SecurityChallengeServlet extends HttpServlet {

    @EJB
    private SystemSecurityBeanLocal systemSecurityBean;
    private String result;
    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session;
            session = request.getSession();
            String URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                URLprefix="";
            }
            String email = request.getParameter("email");

            boolean ifExist = accountManagementBean.checkMemberEmailExists(email);
            if (ifExist) {                
                MemberEntity member = accountManagementBean.getMemberByEmail(email);
                session.setAttribute("memberForgetPassword", member);
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "forgotPasswordSecurity.jsp?email=" +email);
            } else {
                result = "?errMsg=Account does not exist.";
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "forgotPassword.jsp" + result);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.close();
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
