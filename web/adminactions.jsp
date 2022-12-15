<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="jsscritp/jquery.js"></script>        
        <script src="jsscript/adminoptions.js"></script>
        <link href="stylesheet/admin.css" rel="stylesheet" />
        <title>Admin Actions </title>
    </head>
    <body>        
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
         <div class="page-sidebar">
            <div class="page-sidebar-main">
                <div>
                    <div>
                        <div>
                            <a href="index.html">
                                <img src="images/voteicon.png" style="width: 100px; height: 100px" />
                            </a>
                        </div>
                        <div>
                            <h1>VoteIn</h1>
                        </div>
                    </div>
                </div>
                
                <div class="created-request-post-card-sidebar-page">
                    <div id="first"></div>
                    <div id="logoutbtn" onclick="logout()">
                        Logout 
                    </div>
                </div>
               
                <h3>Parties</h3>
                <div class="addparty">

                </div>
            </div>
        </div>
      
        <%
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
        %>
    </body>
                                                                                                               
</html>
