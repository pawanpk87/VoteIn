package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class managecandidate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\" />\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("        <script src=\"jsscritp/jquery.js\"></script>\n");
      out.write("        <script src=\"jsscript/adminoptions.js\"></script>\n");
      out.write("        <link href=\"stylesheet/admin.css\" rel=\"stylesheet\" />\n");
      out.write("        <title>Admin Options</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

        String userid=(String)session.getAttribute("userid");
        if(userid == null)
        {
            response.sendRedirect("accessdenied.html");
            return;
        }
       
        StringBuffer displayBlock=new StringBuffer("<p><div class='page-sidebar'>  <div class='page-sidebar-main'>  <div class='user-achievents-card-sidebar-page'>  <div class='user-details-page-sidebar'>  <div id='img'>  </div>  <div id='room'>Room</div>  </div>  <div id='ach'>  <div id='first'>Pawan</div>  <div id='second'>  <div id='project'>  <span id='title'>Projects</span>  <span id='no'>asdf</span>  </div>  <div id='articles'>  <span id='title'>Post</span>  <span id='no'>{userData.articles.length}</span>  </div>  </div>  </div>  <div id='saved-local-st'>  <div>Total Votes</div>  <div>Note all</div>  </div>  </div>  <div class='page-sidebar-signup-btn'>  <button class='signup'>Vote</button>  </div>  <div class='created-request-post-card-sidebar-page'>  <div id='first'></div>  <div id='second'>Project Request</div>  </div>  <div class='created-request-post-card-sidebar-page'>  <div id='first'></div>  <div id='second'>Write Article</div>  </div>  <div class='created-request-post-card-sidebar-page'>  <div id='first'></div>  <div id='second'>Write Article</div>  </div>  </div>  </div></p>");
        
        displayBlock.append("<p><div class='admin-options'>  <div class='header-admin-options'>  <h2>Manage Candidate</h2>  </div>  <div class='options-admin'>  <div class='card' id='div1' onclick='showaddcandidateform()'>  <h1>Add Candidate</h1>  </div>  <div class='card' id='div2' onclick='showupdatecandidateform()'>  <h1>Update Candidate</h1>  </div>  <div class='card' id='div2' onclick='showdeletecandidateform()'>  <h1>Delete Candidate</h1>  </div>  <div class='card' id='div3' onclick='showcandidate()'>  <h1>Show Candidate</h1>  </div>  </div>  </div></p>");
        
        displayBlock.append("<div id='result'></div>");
        
        out.println(displayBlock);
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("    \n");
      out.write("                                                                                                               \n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
