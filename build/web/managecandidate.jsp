<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="jsscritp/jquery.js"></script>        
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/index.js"></script>
        <link href="stylesheet/admin.css" rel="stylesheet" />
        <title>Manage Candidate</title>
    </head>
    <body>
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
       
        StringBuffer displayBlock=new StringBuffer("<div class='admin-options'>  "
                + "<div class='header-admin-options'>  <h1><strong> Manage Candidate</strong></h1>  </div>  "
                + "<div class='options-admin'>  "
                + "<div class='card' id='div1' onclick='showaddcandidateform()'>  <img src='images/addcandidate.svg' />  <h3>Add Candidate</h3></div>"
                + "<div class='card' id='div1' onclick='showupdatecandidateform()'>  <img src='images/updatecandidate.svg' />  <h3>Update Candidate Details</h3></div> "
                + "<div class='card' id='div1' onclick='showuremovecandidateform()'>  <img src='images/removecandidate.svg' />  <h3>Remove Candidate</h3></div> "
                + "<div class='card' id='div1' onclick='showcandidate()'>  <img src='images/showcandidates.svg' />  <h3>Show Candidate</h3></div> "
                + "</div></div>");
        
        displayBlock.append("<div id='result'></div><br><br>");
        
        out.println(displayBlock);
        %> 
        
    </body>    
                                                                                                               
</html>
