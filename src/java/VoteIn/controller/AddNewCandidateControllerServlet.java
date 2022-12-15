package VoteIn.controller;

import VoteIn.dao.CandidateDAO;
import VoteIn.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
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

public class AddNewCandidateControllerServlet extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rqd=null;
        try{
            /*                                        parseRequest ask for data 
            data[File item object] --->         /
                                                /
                                             parseRequest gives the File item object which is sent
                                             by the client but parseRequest method belongs to
                                             ServletFileUpload.
                                                    | --> ServletFileUpload knows that if it will parse the data
                                                    |      then data will contain file to cover risk (if file will be vary large)
                                                    |      the it will store the data in secondary memory for that id need
                                                    |      DiskFileItemFactory object
                                                    |     /
                                             file can be very large
                                              
            
            */
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
            ArrayList [ uid, party, cid, cname , city]
            */
            CandidateDTO candidate=new CandidateDTO(alObj.get(2),alObj.get(1),alObj.get(4),alObj.get(0),inp);
            
            if(CandidateDAO.addCandidate(candidate))
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
