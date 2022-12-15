package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminactions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\" />\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("        <script src=\"jsscritp/jquery.js\"></script>        \n");
      out.write("        <script src=\"jsscript/adminoptions.js\"></script>\n");
      out.write("        <link href=\"stylesheet/admin.css\" rel=\"stylesheet\" />\n");
      out.write("        <title>Admin Actions </title>\n");
      out.write("    </head>\n");
      out.write("    <body>        \n");
      out.write("        <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n");
      out.write("         <div class=\"page-sidebar\">\n");
      out.write("            <div class=\"page-sidebar-main\">\n");
      out.write("                <div>\n");
      out.write("                    <div>\n");
      out.write("                        <div>\n");
      out.write("                            <a href=\"index.html\">\n");
      out.write("                                <img src=\"images/voteicon.png\" style=\"width: 100px; height: 100px\" />\n");
      out.write("                            </a>\n");
      out.write("                        </div>\n");
      out.write("                        <div>\n");
      out.write("                            <h1>VoteIn</h1>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"created-request-post-card-sidebar-page\">\n");
      out.write("                    <div id=\"first\"></div>\n");
      out.write("                    <div id=\"logoutbtn\" onclick=\"logout()\">\n");
      out.write("                        Logout \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("               \n");
      out.write("                <h3>Parties</h3>\n");
      out.write("                <div class=\"addparty\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("      \n");
      out.write("        ");

        String userid=(String)session.getAttribute("userid");
        if(userid == null)
        {
            response.sendRedirect("accessdenied.html");
            return;
        }
       
        StringBuffer displayBlock=new StringBuffer("<p><div class='admin-options'>  "
                + "<div class='header-admin-options'>  <h1><strong> Admin Options</strong></h1>  </div>  "
                + "<div class='options-admin'>  "
                + "<div class='card' id='div1' onclick='manageuser()'>  <img src='images/manageusers.svg' />  <h3>Manage User</h3>  "
                + "</div>  <div class='card' id='div1' onclick='managecandidate()'>  <img src='images/managecandidate.svg' />  <h3>Manage Candidate</h3>  "
                + "</div>  <div class='card' id='electionresult'>  <img src='images/showresult.svg' />  <h3>Show Result <br /></h3>  "
                + "</div>  </div>  </div></p>");
                
        displayBlock.append("<div id='result'></div>");
        
        out.println(displayBlock);
        
      out.write("\n");
      out.write("    </body>\n");
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
