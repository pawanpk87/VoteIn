let userid,password;

function connectUser()
{
    userid=$("#userid").val();
    password=$("#password").val();
    
    if(validateUser() == true)
    {
        let data={
            userid:userid,
            password:password
        };
        
        let xhr=$.post("LoginControllerServlet",data,processresponse).fail(handleError);
    }  
}


function processresponse(responseText,textStatus,xhr)
{
    let str=responseText.trim();
    if(str === "error")
    {
        swal("Access Denied","Invalid userid/password","error");
    }
    else if(str.indexOf("jsessionid") !== -1)
    {
        swal("Success","Login successful!","success").then((value)=>{
            window.location=str;
        });        
    }
    else
    {
        swal("Access Denied","Some problem occurred"+str,"error");
    }
}

function handleError(xhr)
{  
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
}


function validateUser()
{
    if(userid === "" || password === "")
    {
        swal("Access Denied","Please enter userid/password","error");
        return false;
    }
    
    return true;
}