function addvote(candidateId)
{
   let data={
     candidateId:candidateId  
   };
   $.post("AddVoteControllerServlet",data,(responseText)=>{
       let response=responseText.trim();
        if(response === "success")
        {
             swal("Success","Voting done","success").then((value)=>{
                window.location="votingresponse.jsp";
            }); 
        }
        else
        {
            swal("Failure","Voting failed","error").then((value)=>{
                window.location="votingresponse.jsp";
            }); 
        }
    });
}

function addPartiestohomepage()
{    
    console.log("addPartiestohomepage called...");
    var newdiv=document.createElement("div");   
    let data={data:"allcandidate"};
    $.post("GetAllPartiesServlet",data,(responseText)=>{
        newdiv.innerHTML=JSON.parse(responseText).allparties;  
        console.log(JSON.parse(responseText).allparties);
        $(".addparty").append(newdiv);
    });
}

function logout()
{
  let xhr=$.post("LogoutServletController",null,(responseText)=>{
      let str=responseText.trim();
      if(str === "success")
      {
          swal("Success","Logout successful!","success").then((value)=>{
                window.location="index.html";
          }); 
      }
      else
      {
          swal("Error!","Problem in server communication:"+xhr.statusText,"error"); 
      }
       
  }).fail((xhr)=>{
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
  });  
}


window.onload=addPartiestohomepage;