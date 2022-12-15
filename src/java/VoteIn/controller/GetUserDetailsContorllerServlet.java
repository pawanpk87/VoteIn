/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoteIn.controller;

import VoteIn.dao.UserDAO;
import VoteIn.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class GetUserDetailsContorllerServlet extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rqd=null;
        PrintWriter out=response.getWriter();
        HttpSession sess=request.getSession();
        
        String userid=(String)sess.getAttribute("userid");
        
        if(userid == null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        
        try{
            String uid=request.getParameter("uid");
            UserDetails user=UserDAO.getUserDetailsById(uid);
             JSONObject json=new JSONObject();
            if(user == null)
            {
                json.put("username","wrong");
                out.println(json);
                return;
            }
           
            json.put("username", user.getUsername());
            json.put("email", user.getEmailId());
            json.put("mobile",user.getMobileNumber());
            json.put("address",user.getAddress());
            json.put("city", user.getCity());
            
            out.println(json);
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
