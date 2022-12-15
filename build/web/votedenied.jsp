<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="VoteIn.dto.CandidateInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/showcandidate.css" rel="stylesheet" />
        <script src="jsscritp/index.js"></script>    
        <title>Voting Details</title>
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
                 session.invalidate();
                 response.sendRedirect("accessdenied.html");
                 return;
             }
             
             CandidateInfo candidate=(CandidateInfo)session.getAttribute("candidate");             
             StringBuffer displayBlock=new StringBuffer();
             
             if(candidate == null)
             {
                 displayBlock.append("<p><div class='admin-options'>  "
                         + "<div class='header-admin-options'>  "
                         + "<h2>Voting Details</h2>  </div>  "
                         + "<div class='options-admin'>  "
                         + "<div class='card' id='div1'>  "
                         + "<h1 style='color:red'>You have already voted!</h1>  "
                         + "</div>  </div>  </div></p>");
             }
             else
             {
                 displayBlock.append("<p><div class='admin-options'>  <div class='header-admin-options'>  "
                         + "<h2>Voting Details</h2>  </div>  "
                         + "<div class='options-admin'>  "
                         + "<div class='card' id='div1'>  "
                         + "<h1>Thank you for voting!</h1>  "
                         + "</div>  </div>  </div></p>");
                 
                 displayBlock.append("<br><div class='user-requested-card'>  <div class='user-details'>  <div class='img'>  "
                    + "<img src='data:image/png;base64,"+candidate.getSymbol()+"' alt='symbol' />  <br />  "
                    + "<small>"+candidate.getCandidateName()+"</small>  </div>  <br /> "
                    + " <small style='color: rgb(131, 131, 131);'>"+candidate.getCandidateId()+"</small>  </div>  "
                    + "<div class='user-requested-card-project-details'>  <h2>"+candidate.getParty()+"</h2>  <br /> "
                    + " <button class='cVoteD' id='"+candidate.getCandidateId()+"'><h2>&#x2714;</h2></button>  </div>  </div></p>");
             }
             
             out.println(displayBlock.toString());
        %>
    </body>
</html>
