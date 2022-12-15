<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,VoteIn.dto.CandidateInfo" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="jsscritp/jquery.js"></script>
        <script src="jsscript/vote.js"></script>
        <link href="stylesheet/showcandidate.css" rel="stylesheet" />
        <title>Voting</title>
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
        
        StringBuffer displayBlock=new StringBuffer();
        
        displayBlock.append("<div class='admin-options'>  <div class='header-admin-options'>  "
                + "<h2>Voting</h2>  "
                + "</div>  <div class='options-admin'>  <div class='card' id='div1'>  "
                + "<h1>Whom do you want to Vote</h1>  "
                + "</div>  </div>  </div>");
                
        ArrayList<CandidateInfo> candidateList=(ArrayList<CandidateInfo>)request.getAttribute("candidatelist");
        
        for(CandidateInfo c : candidateList)
        {
            displayBlock.append("<div class='user-requested-card'>  <div class='user-details'>  <div class='img'>  "
                    + "<img src='data:image/png;base64,"+c.getSymbol()+"' alt='symbol' />  <br />  "
                    + "<h4>"+c.getCandidateName()+"</h4>  </div>  <br /> "
                    + " <small style='color: rgb(131, 131, 131);'>"+c.getCandidateId()+"</small>  </div>  "
                    + "<div class='user-requested-card-project-details'>  <h2>"+c.getParty()+"</h2>  <br /> "
                    + " <button class='cVote' id='"+c.getCandidateId()+"' onclick='addvote(this.id)' >Vote</button>  </div>  </div></p>");
        }
        
        out.println(displayBlock);
        %>
    </body>
</html>