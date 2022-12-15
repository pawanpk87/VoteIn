<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="org.json.JSONObject" %>
<%@page  import="VoteIn.dto.CandidateDetails" %>
<%@page  import="java.util.ArrayList" %>
<%
        String userid=(String)session.getAttribute("userid");

        if(userid == null)
        {
            response.sendRedirect("accessdenied.html");
            return;
        }
        
        StringBuffer displayBlock=new StringBuffer();
        
        JSONObject json=new JSONObject();
        
        ArrayList<String> userId=(ArrayList<String>)request.getAttribute("userIdList");
        displayBlock.append("<option value=''>Chose Id</option>");

        for(String cid : userId)
        {
            displayBlock.append("<option value='"+cid+"'>"+cid+"</option>");
        }   

        json.put("userIdList", displayBlock.toString());
          
        out.println(json);
%>
