package VoteIn.controller;

import VoteIn.dao.RegistrationDAO;
import VoteIn.dto.UserDetails;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rdp=null;
        UserDetails user=new UserDetails();
        
        user.setUserId(request.getParameter("userid"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));
        user.setCity(request.getParameter("city"));
        user.setEmailId(request.getParameter("email"));
        user.setMobileNumber(request.getParameter("mobile"));
        
        try
        {
            boolean result=false, userFound=false;   
            
            if(!RegistrationDAO.searchUser(user.getUserId()))
            {
                result=RegistrationDAO.registerUser(user);
            }
            else
            {
                userFound=true;
            }
            
            request.setAttribute("result", result);
            request.setAttribute("userFound", userFound);
            request.setAttribute("username", user.getUsername());
                       
            rdp=request.getRequestDispatcher("registrationresponse.jsp");
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();           
            rdp=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception", sqlex);
        }
        finally{
            rdp.forward(request, response);
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
