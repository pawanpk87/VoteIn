<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.Map" %>
<%@page  import="org.json.JSONObject" %>
<%@page  import="org.json.JSONArray" %>
<%@page import="VoteIn.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList,VoteIn.dto.CandidateInfo" %>
<%
    String userid=(String)session.getAttribute("userid");

    if(userid == null)
    {
        response.sendRedirect("accessdenied.html");
        return;
    }

    StringBuffer displayBlock=new StringBuffer();

    Map<CandidateDetails,Integer> resultDetails=(Map<CandidateDetails,Integer>)request.getAttribute("result");

    int votecount=(int)request.getAttribute("votecount");

    displayBlock.append("<div class='admin-options'>  "
            + "<div class='header-admin-options'>  <h2>Voting Result</h2> </div> "
            + "<br><br>"
            + "<div id='piechart'></div><br><br>"
            + "</div>");
       
    ArrayList<String> candidateName=new ArrayList<>();
    ArrayList<Integer> voteCount=new ArrayList<>();
    
    for(Map.Entry<CandidateDetails,Integer> candidate : resultDetails.entrySet())
    {
         CandidateDetails c=candidate.getKey();
         
         int totalVotes=candidate.getValue();
         
         candidateName.add(c.getUsername());
         voteCount.add(totalVotes);
         
         displayBlock.append("<div class='user-requested-card'>  <div class='user-details'>  <div class='img'>  "
                + "<img src='data:image/png;base64,"+c.getSymbol()+"' alt='symbol' />  <br />  "
                + "<h4>"+c.getUsername()+"</h4>  </div>  <br /> "
                + " <small style='color: rgb(131, 131, 131);'>"+c.getCandidatId()+"</small>  </div>  "
                + "<div class='user-requested-card-project-details'>  <h2>"+c.getParty()+"</h2>  <br /> "
                + " <button class='cVote' id='totalvotes' >"+totalVotes+" votes</button>  </div>  </div></p>");
    }
    
    JSONObject json=new JSONObject();
    
    JSONArray cname=new JSONArray(candidateName);
    JSONArray vcount=new JSONArray(voteCount);
    
    json.put("htmlResult", displayBlock.toString());
    json.put("candidateName", cname);
    json.put("voteCount", vcount);
    
    
    out.println(json);
%>
