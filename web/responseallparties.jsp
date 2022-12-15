<%@page  import="org.json.JSONObject" %>
<%@page  import="java.util.Map" %>
<% 
        Map<String,String> parties=(Map<String,String>)request.getAttribute("parties");
        JSONObject json=new JSONObject();
        
        if(parties == null)
        {
            json.put("allparties","<span>As of now, there are no parties taking part in the election.<span>");
        }  
        else
        {
            StringBuffer displayBlock=new StringBuffer();        

            for(Map.Entry<String,String> party : parties.entrySet())
            {
                displayBlock.append("<div class='party'>"
                        + "<div class='name'>"+party.getKey()+"</div>"
                        + "<div class='img'> <img src='data:image/png;base64,"+party.getValue()+"'> </div>"
                        + " </div>");
            }
            json.put("allparties", displayBlock.toString());
        }
        
        out.println(json);
%>