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
        
        String result=(String)request.getAttribute("result");
        
        StringBuffer displayBlock=new StringBuffer();
        
        JSONObject json=new JSONObject();
        
        if("candidatelist".equalsIgnoreCase(result))
        {
            ArrayList<String> candidateId=(ArrayList<String>)request.getAttribute("candidateid");
            displayBlock.append("<option value=' '>Chose Id</option>");
                        
            for(String cid : candidateId)
            {
                displayBlock.append("<option value='"+cid+"'>"+cid+"</option>");
            }   
            
            json.put("cid", displayBlock.toString());
        }
        else if("detailsforupdate".equalsIgnoreCase(result))
        {
            CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
                                  
            String image="<image src='data:image/png;base64,"+cd.getSymbol()+"' style='width:300px;height:200px'/>";
            
            displayBlock.append("<form enctype='multipart/form-data' id='fileUploadForm' method='POST'>");
            
            displayBlock.append("<div class='formbold-mb-5'>  <label for='uid' class='formbold-form-label'> User Id </label>  <input type='text' name='uid' id='uid' class='formbold-form-input' disabled value='"+cd.getUserId()+"'/>  </div>");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='cname' class='formbold-form-label'>  Candidate Name  </label>  <input type='cname' name='cname' id='cname' class='formbold-form-input'disabled value='"+cd.getUsername()+"'/>  </div>  ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='party' class='formbold-form-label'> Party </label>  <input type='party' name='party' id='party' class='formbold-form-input' value='"+cd.getParty()+"'/>  </div> ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='city' class='formbold-form-label'> City </label>  <input type='city' name='city' id='city' class='formbold-form-input' value='"+cd.getCity()+"'/>  </div> ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='files' class='formbold-form-label'> Image </label>"+image+"</div>");
            displayBlock.append("<div class='formbold-mb-5'>  "
                    + "<label for='files' class='formbold-form-label'> Image </label>  "
                    + "<input type='file' name='files' id='files' placeholder='Update Party Image' class='formbold-form-input' />  "
                    + "</div>  "
                    + "<div>  <span id='updatecandidate' onclick='updatecandidate()' class='formbold-btn'>  Update Candidate  </span>  </div>  "
                    + "</form>  <br /> <br>");            
                                  
            json.put("subdetails", displayBlock.toString()); 
        }
        else if("details".equalsIgnoreCase(result))
        {
            CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
            
            String image="<image src='data:image/png;base64,"+cd.getSymbol()+"' style='width:300px;height:200px'/>";
            
            displayBlock.append("<div class='formbold-mb-5'>  <label for='uid' class='formbold-form-label'> User Id </label>  <input type='text' name='uid' id='uid' class='formbold-form-input' disabled  value='"+cd.getUserId()+"'/>  </div>");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='cname' class='formbold-form-label'>  Candidate Name  </label>  <input type='cname' name='cname' id='cname' class='formbold-form-input' disabled value='"+cd.getUsername()+"'/>  </div>  ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='city' class='formbold-form-label'> City </label>  <input type='city' name='city' id='city' class='formbold-form-input' disabled value='"+cd.getCity()+"'/>  </div> ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='party' class='formbold-form-label'> Party </label>  <input type='party' name='party' id='party' class='formbold-form-input' disabled value='"+cd.getParty()+"'/>  </div> ");
            displayBlock.append("<div class='formbold-mb-5'>  <label for='files' class='formbold-form-label'> Image </label>"+image+"</div>");
                                   
            json.put("subdetails", displayBlock.toString());
        }
        
        out.println(json);
%>
