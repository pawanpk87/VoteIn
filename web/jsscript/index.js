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
