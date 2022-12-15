<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex=(Exception)request.getAttribute("Exception");
 
    out.println("Some exception occurred"+ex.getMessage());
%>