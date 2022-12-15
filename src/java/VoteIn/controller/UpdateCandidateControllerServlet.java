package VoteIn.controller;

import VoteIn.dao.CandidateDAO;
import VoteIn.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class UpdateCandidateControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rqd=null;
        try{            
            DiskFileItemFactory df=new DiskFileItemFactory();
            ServletFileUpload sfu=new ServletFileUpload(df);
            ServletRequestContext src=new ServletRequestContext(request);
            List<FileItem> multisList=sfu.parseRequest(src);
            
            InputStream inp=null;
            ArrayList<String> alObj=new ArrayList<>();            
            for(FileItem ft : multisList)
            {
                if(ft.isFormField())
                {
                    //text data
                    String fname=ft.getFieldName();
                    String vlaue=ft.getString();                    
                    alObj.add(vlaue);
                }
                else
                {
                    // file data
                    inp=ft.getInputStream();
                    
                    String key=ft.getFieldName();
                    String fileName=ft.getString();          
                }
            }
            /*
            ArrayList [party, city, cid, cname]
            */
                     
            CandidateDTO candidate=new CandidateDTO(alObj.get(2),alObj.get(0),alObj.get(1),alObj.get(6),inp);
            
            if(CandidateDAO.updateCandidate(candidate))
            {
                rqd=request.getRequestDispatcher("success.jsp");
            }                        
            else
            {
                rqd=request.getRequestDispatcher("failure.jsp");
            }
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
