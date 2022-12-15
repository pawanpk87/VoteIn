<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   boolean result=(boolean)request.getAttribute("result");
   boolean userFound=(boolean)request.getAttribute("userFound");
   if(userFound == true)
        out.println("uap");
   else if(result == true)
        out.println("success");
   else
        out.println("failure");
%>