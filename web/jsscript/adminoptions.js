function manageuser()
{
//    swal("Admin","Redirecting To User Management!","success").then((value)=>{
//       
//    });
    window.location="manageuser.jsp"; 
}

function managecandidate()
{
//    swal("Admin","Redirecting To Candidate Management!","success").then((value)=>{
//       
//    });
    window.location="managecandidate.jsp";     
}


//----------------------------Add Candidate----------------------------------------------

function showaddcandidateform()
{
    removechandidateform();
    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-main-wrapper");
    newdiv.innerHTML="<div class='formbold-form-wrapper'><h3>Add New Candidate</h3><br><br>  <form enctype='multipart/form-data' id='fileUploadForm' method='POST'>  <div class='formbold-mb-5'>  <label for='cid' class='formbold-form-label'>  Candidate Id  </label>  <input type='text' name='cid' id='cid' placeholder='Enter Candidate Id' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='uid' class='formbold-form-label'> User Id </label>  <input type='text' name='uid' id='uid' onkeypress='return getdetails(event)' placeholder='Enter User Id' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='cname' class='formbold-form-label'>  Candidate Name  </label>  <input type='cname' name='cname' id='cname' placeholder='Enter Candidate Name' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='city' class='formbold-form-label'> City </label>  <select id='city' class='formbold-form-input'>  <option value='Bhopal'>Bhopal</option>  </select>  </div>  <div class='formbold-mb-5'>  <label for='party' class='formbold-form-label'> Party </label>  <input type='party' name='party' id='party' placeholder='Enter Party' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='files' class='formbold-form-label'> Image </label>  <input type='file' name='files' id='files' placeholder='Select Party Image' class='formbold-form-input' />  </div>  <div>  <span id='addcandidate' onclick='addcandidate()' class='formbold-btn'>  Add Candidate  </span>  </div>  </form>  <br /> <span id='addresp'></span></div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);
    let data={id:"getid"};
    $.post("AddCondidateControllerServlet",data,(responseText)=>{
        $("#cid").val(responseText);
        $("#cid").prop("disabled",true);
        location.href = "#result";        
    });
}

function addcandidate(event)
{
    var form=$("#fileUploadForm")[0];
    
    var data=new FormData(form);
    
    alert(data);
    
    var cid=$("#cid").val();
    var cname=$("#cname").val();
    var city=$("#city").val();
    var party=$("#party").val();
    var uid=$("#uid").val();
    
    data.append("cid",cid);
    data.append("cname",cname);
    data.append("city",city);
    data.append("party",party);
    data.append("uid",uid);
        
    $.ajax({
        type:"POST",
        enctype:"multipart/form-data",
        url:"AddNewCandidateControllerServlet",
        data:data,
        processData:false,
        contentType:false,
        chache:false,
        timeout:600000,
        success:function(data){
          str=data+"...";
          swal("Admin!",str,"success").then((value)=>{
              showaddcandidateform();
          });
        },
        error:function(error){
            swal("Admin!",error,"error");
        }
    });
}

function getdetails(event)
{
    if(event.keyCode === 13)
    {
        event.preventDefault();   
        let data={
            uid:$("#uid").val()
        };        
        $.post("AddCondidateControllerServlet",data,(responseText)=>{               
            const details=JSON.parse(responseText);            
            let city=details.city;
            let username=details.username;
            if(username == "wrong")
            {
                swal("Wrong Adhar Number!", "Adhar number is invalid!","error");
            }
            else
            {
                $("#cname").val(username);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled",true);
            }
        }).fail((xhr)=>{
            swal("Error!","Problem in server communication:"+xhr.statusText,"error");
        });
    }
}


////----------------------------Update Candidate----------------------------------------------
function showupdatecandidateform()
{ 
    removechandidateform();    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-form-wrapper");    
    newdiv.innerHTML="<div class='formbold-mb-5'> <h3>Update Candidate</h3><br><br>  <label for='cid' class='formbold-form-label'> Candidate Id </label>  <select id='cid' class='formbold-form-input'>  </select>  </div>  <div id='addresp'></div></div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);
    
    let data={data:"cid"};
    
    $.post("ShowCandidateControllerServlet",data,(responseText)=>{
        let candidateIdList=JSON.parse(responseText);
        $("#cid").append(candidateIdList.cid);
        location.href = "#result";
    });
    
    $("#cid").change(()=>{
        var cid=$("#cid").val();
        if(cid === " ")
        {
            swal("No Selection!","Please select an id","error");
        }
        else
        {
            data={data:"detailsforupdate",id:cid}; 
            console.log(data);
            $.post("ShowCandidateControllerServlet",data,(responseText)=>{
                cleartext();
                let details=JSON.parse(responseText);
                $("#addresp").append(details.subdetails);
                location.href = "#addresp";
            });
        }
    });
}


function updatecandidate()
{
    var form=$("#fileUploadForm")[0];
    
    var data=new FormData(form);
     
    var cid=$("#cid").val();
    var cname=$("#cname").val();
    var city=$("#city").val();
    var party=$("#party").val();
    var uid=$("#uid").val();
    
    data.append("cid",cid);
    data.append("cname",cname);
    data.append("city",city);
    data.append("party",party);
    data.append("uid",uid);
      
    $.ajax({
        type:"POST",
        enctype:"multipart/form-data",
        url:"UpdateCandidateControllerServlet",
        data:data,
        processData:false,
        contentType:false,
        chache:false,
        timeout:600000,
        success:function(data){
          str=data+"...";
          swal("Admin!",str,"success").then((value)=>{
              showaddcandidateform();
          });
        },
        error:function(error){
            swal("Admin!",error,"error");
        }
    });
}


////----------------------------Remove Candidate----------------------------------------------
function showuremovecandidateform()
{ 
    removechandidateform();    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-form-wrapper");    
    newdiv.innerHTML="<div class='formbold-mb-5'> <h3>Remove Candidate</h3><br><br>  <label for='cid' class='formbold-form-label'> Candidate Id </label>  <select id='cid' class='formbold-form-input'>  </select>  </div>  <div id='addresp'></div></div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);
    
    let data={data:"cid"};
    
    $.post("ShowCandidateControllerServlet",data,(responseText)=>{
        let candidateIdList=JSON.parse(responseText);
        $("#cid").append(candidateIdList.cid);
        location.href = "#result";
    });
    
    $("#cid").change(()=>{
        var cid=$("#cid").val();
        if(cid === " ")
        {
            swal("No Selection!","Please select an id","error");
        }
        else
        {
            swal({
            title: "Are you sure?",
            text: " ",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {                
                data={data: cid};             
                $.post("DeleteCandidateControllerServlet",data,(responseText)=>{    
                    console.log("response for delete user is:");
                    
                    console.log(responseText);
                    if(responseText.trim() == "success")
                    {
                         swal("Deleted successfully", {
                            icon: "success",
                        });
                    }
                    else
                    {
                        swal("Error!","Problem in server communication","error");
                    }                   
                }).fail((xhr)=>{
                    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
                });                
             } 
          });             
        }
    });
}




//----------------------------Show Candidate----------------------------------------------

function showcandidate()
{ 
    removechandidateform();    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-form-wrapper");    
    newdiv.innerHTML="<div class='formbold-mb-5'> <h3>Show Candidate</h3><br><br>  <label for='cid' class='formbold-form-label'> Candidate Id </label>  <select id='cid' class='formbold-form-input'>  </select>  </div>  <div id='addresp'></div></div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);
    
    let data={data:"cid"};
    
    $.post("ShowCandidateControllerServlet",data,(responseText)=>{
        let candidateIdList=JSON.parse(responseText);
        $("#cid").append(candidateIdList.cid);
        location.href = "#result";
    });
    
    $("#cid").change(()=>{
        var cid=$("#cid").val();
        if(cid === " ")
        {
            swal("No Selection!","Please select an id","error");
        }
        else
        {
            data={data:cid}; 
            
            $.post("ShowCandidateControllerServlet",data,(responseText)=>{
                cleartext();
                let details=JSON.parse(responseText);
                $("#addresp").append(details.subdetails);
                location.href = "#addresp";
            });
        }
    });
}



//----------------------------Show  all Users----------------------------------------------
function showAllUsers()
{
    removechandidateform();
    var newdiv=document.createElement("div");     
    let data={
        id:"getallUsersData"
    };
    $.post("GetAllUsersDataServlet",data,(responseText)=>{
        newdiv.innerHTML=JSON.parse(responseText).allUsers;         
        $("#result").append(newdiv);
        location.href = "#result";
    });
}



//----------------------------Remove User----------------------------------------------

function removeuser()
{ 
    document.getElementById("result").innerHTML=" "; 
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-form-wrapper");    
    newdiv.innerHTML="<div class='formbold-mb-5'><h3>Remove User</h3><br><br>  <label for='cid' class='formbold-form-label'> User Id </label>  <select id='cid' class='formbold-form-input'>  </select>  </div>  <div id='addresp'></div></div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);
    
    let data={data:"cid"};
    
    $.post("GetAllUserIDControllerServlet",data,(responseText)=>{
        let candidateIdList=JSON.parse(responseText).userIdList;
        console.log("getalluser  id response:");
        console.log(JSON.parse(responseText).userIdList);
        $("#cid").append(candidateIdList);
        location.href = "#result";
    });
    
    $("#cid").change(()=>{
        var cid=$("#cid").val();
        if(cid === " ")
        {
            swal("No Selection!","Please select an id","error");
        }
        else
        {
            swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to add the user!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {                
                data={adhar_no: cid};             
                $.post("DeleteUserControllerServlet",data,(responseText)=>{                   
                    if(responseText == "success")
                    {
                         swal("Deleted successfully", {
                            icon: "success",
                        });
                    }
                    else
                    {
                        swal("Error!","Problem in server communication","error");
                    }                   
                }).fail((xhr)=>{
                    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
                });                
             } 
          });            
        }
    });
}

//----------------------------Update User----------------------------------------------

function showupdateuserform()
{
    removechandidateform();
    document.getElementById("result").innerHTML=" "; 
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("class","formbold-main-wrapper");
    newdiv.innerHTML="<div class='formbold-form-wrapper'><h3>Update User</h3><br><br>  <form>  <div class='formbold-mb-5'>  <label for='uid' class='formbold-form-label'>  User ID  </label>  <input type='text' name='uid' id='uid' onkeypress='return getuserdetails(event)' placeholder='Enter UserId and press enter for details' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='uname' class='formbold-form-label'>  User Name  </label>  <input type='text' name='uname' id='uname' placeholder='Enter User Name' class='formbold-form-input' />  </div> <div class='formbold-mb-5'>  <label for='email' class='formbold-form-label'>  Email  </label>  <input type='email' name='email' id='email' placeholder='Enter email' class='formbold-form-input' />  </div> <div class='formbold-mb-5'>  <label for='mobile' class='formbold-form-label'>  Mobile Number  </label>  <input type='text' name='mobile' id='mobile' placeholder='Enter mobile number' class='formbold-form-input' />  </div> <div class='formbold-mb-5'>  <label for='address' class='formbold-form-label'>  Address  </label>  <input type='text' name='address' id='address' placeholder='Enter Address' class='formbold-form-input' />  </div>  <div class='formbold-mb-5'>  <label for='city' class='formbold-form-label'>  City Name  </label>  <input type='text' name='city' id='city' placeholder='Enter City Name' class='formbold-form-input' />  </div>     <div>  <span id='updateuserdetails' onclick='updateuserdetails()' class='formbold-btn'>  Update User  </span>  </div>  </form>  <br /> </div>";
    var addcand=$("#result")[0];    
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2000);    
    location.href = "#result";
}

function getuserdetails(event)
{
    if(event.keyCode === 13)
    {
        event.preventDefault();   
        let data={
            uid:$("#uid").val()
        }; 
        
        $.post("GetUserDetailsContorllerServlet",data,(responseText)=>{               
            const details=JSON.parse(responseText);            
            let city=details.city;
            let username=details.username;
            let address=details.address;
            let mobile=details.mobile;
            let email=details.email;       
            console.log("details is:");
            console.log(details);
            if(username == "wrong")
            {
                swal("Wrong Adhar Number!", "Adhar number is invalid!","error");
            }
            else
            {
                $("#uname").val(username);
                $("#email").val(email);
                $("#mobile").val(mobile);
                $("#address").val(address);
                $("#city").val(city);
                $("#uid").attr("disabled", true);
            }
        }).fail((xhr)=>{
            swal("Error!","Problem in server communication:"+xhr.statusText,"error");
        });        
    }
}


function updateuserdetails()
{          
    var uname=$("#uname").val();
    var uid=$("#uid").val();
    var email=$("#email").val();
    var mobile=$("#mobile").val();
    var address=$("#address").val();
    var city=$("#city").val();
    
    if(uname === "" || uid === "" || 
       email === "" || city === "" || 
       address === "" || mobile === "" || 
       city === "")
    {
        swal("Error!","All fields are mandatory","error");
        return false;
    }
    
    console.log(mobile);
    
    if(checkMobile(mobile) === false)
    {
       return;   
    }
    
    let data={
     uid:uid,
     uname:uname,
     email:email,
     mobile:mobile,
     address:address,
     city:city
   };
    
    console.log("update called....");
    console.log(data);
    
   $.post("UpdateUserDetailsContorllerServlet",data,(responseText)=>{ 
       console.log("called........");
       swal("Updated successfully", { icon: "success",  });
       $("#uid").val("");
       $("#uname").val("");
       $("#email").val("");
       $("#mobile").val("");
       $("#address").val("");
       $("#city").val(""); 
       $("#uid").attr("disabled", false);
    }).fail((xhr)=>{
        swal("Error!","Problem in server communication:"+xhr.statusText,"error");
    });
}

//------------------------------Electiion  Result---------------------------------------------------------


google.charts.load('current', {
  packages: ['corechart']
}).then(function () {
  $("#electionresult").click(function() {      
     let xhr=$.post("ElectionResultControllerServlet",null,(responseText)=>{
        let response=JSON.parse(responseText);
        
        $("#result").html(response.htmlResult);
        
        
        let candidateName=response.candidateName;
        let voteCount=response.voteCount;
        
        let graphData=[];
        
        graphData.push(['Candidate', 'votes']);
        
        for(let i=0; i<candidateName.length; i++)
        {
            graphData.push([candidateName[i], voteCount[i]]);
        }
        
        var data = google.visualization.arrayToDataTable(graphData);

        var options = {'title':'Election Result', 'width':550, 'height':400};

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
        
        location.href = "#result";
     }).fail((xhr)=>{
        swal("Error!","Problem in server communication:"+xhr.statusText,"error");
     });
  });
});


//----------------------------------------------logout-----------------------------------------------

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



//---------------------------------------------------------------------------------------------------

function cleartext()
{
    $("#addresp").html("");
}


function removechandidateform()
{
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv != null)
    {
        $("#candidateform").fadeOut("500");
        contdiv.removeChild(formdiv);
    }
}


function checkMobile(mobile)
{
    console.log("check Mobile");
    console.log(mobile);
    
    let found=true;
    
    for(let i=0; i<mobile.length; i++)
    {
        if((mobile[i] >= '0' && mobile[i] <= '9') ==false)
        {
            found=false;
        }
    }
    
    if(found == false)
    {
        swal("Error!","Mobile number should contain only digits","error");
        return false;
    }
    
    if(mobile.length > 10 || mobile.length < 10)
    {
        swal("Error!","Please use a 10 digit mobile number","error");
        return false;
    }
    
    return true;
}


function addPartiestohomepage()
{    
    $("#candidateform").val(" ");
    var newdiv=document.createElement("div");   
    let data={data:"allcandidate"};
    $.post("GetAllPartiesServlet",data,(responseText)=>{
        newdiv.innerHTML=JSON.parse(responseText).allparties;  
        $(".addparty").append(newdiv);
    });
}

window.onload=addPartiestohomepage;


