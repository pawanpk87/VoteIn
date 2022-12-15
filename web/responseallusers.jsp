<%@page  import="org.json.JSONObject" %>
<%@page  import="java.util.ArrayList" %>
<%@page  import="VoteIn.dto.UserDetails" %>
<% 
        ArrayList<UserDetails> users=(ArrayList<UserDetails>)request.getAttribute("users");
        JSONObject json=new JSONObject();        
        if(users == null)
        {
            json.put("allUsers","<span>As of now, there are no registered in the election.<span>");
        }  
        else
        {
            StringBuffer displayBlock=new StringBuffer();   
            displayBlock.append("<table id='users'>");
            displayBlock.append("<tr>"
                    + "<th>User Id</th>"
                    + "<th>Name</th>"
                    + "<th>City</th>"
                    + "<th>Email Id</th>"
                    + "<th>Mobile No.</th>"
                    + "</tr>");
            for(int i=0; i<users.size(); i++)
            {
                UserDetails user=users.get(i);
                displayBlock.append("<tr>"
                        + "<td>"+user.getUserId()+"</td>"
                        + "<td>"+user.getUsername()+"</td>"
                        + "<td>"+user.getCity()+"</td>"
                        + "<td>"+user.getEmailId()+"</td>"
                        + "<td>"+user.getMobileNumber()+"</td>"
                        + "</tr>");                
            }
            displayBlock.append("<br><br>");
            json.put("allUsers", displayBlock.toString());
        }
        out.println(json);
%>