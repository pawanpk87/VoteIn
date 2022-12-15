package VoteIn.controller;

import VoteIn.dao.CandidateDAO;
import VoteIn.dto.CandidateDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShowCandidateControllerServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rqd=null;        
        HttpSession sess=request.getSession();        
        String userid=(String)sess.getAttribute("userid");        
        if(sess == null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }     
        try{
            String data=request.getParameter("data");  
            if("cid".equalsIgnoreCase(data))
            {                
                ArrayList<String> candidateIdList=CandidateDAO.getCandidateIds();
                request.setAttribute("candidateid", candidateIdList);
                request.setAttribute("result","candidatelist");
            }
            else if("detailsforupdate".equalsIgnoreCase(data))
            {
                CandidateDetails cd=CandidateDAO.getDetailsById(request.getParameter("id"));                
                request.setAttribute("candidate", cd);
                request.setAttribute("result","detailsforupdate");        
            }
            else
            {
                CandidateDetails cd=CandidateDAO.getDetailsById(data);
                request.setAttribute("candidate", cd);
                request.setAttribute("result","details");
            }
            rqd=request.getRequestDispatcher("adminshowcandidate.jsp");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();           
            rqd=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception", ex);
        }
        finally{
            if(rqd != null)
                rqd.forward(request, response);
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
