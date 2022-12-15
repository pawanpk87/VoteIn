<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   boolean result=(boolean)request.getAttribute("result");
   if(result == true)
        out.println("success");
   else
        out.println("failure");
%>