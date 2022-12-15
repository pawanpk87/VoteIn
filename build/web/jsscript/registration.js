let username, password, cpassword, city, address, adhar, email, mobile;

function addUser()
{   
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    
    if(validateUser())
    {
        if(password !== cpassword)
        {
            swal("Error!","Password do not match","error");
            return;
        }
        else
        {
            if(checkEmail() === false)
                return;
            
            if(checkMobile() === false)
                return;
            
            let data={
                username:username,
                password:password,
                city:city,
                address:address,
                userid:adhar,
                email:email,
                mobile:mobile
            };
            
            let xhr=$.post("RegistrationControllerServlet",data,processresponse).fail(handleError);
        }
    }
}

function processresponse(responseText,textStatus,xhr)
{
    let str=responseText.trim();
    if(str === "success")
    {
        swal("Success!","Registration done successfully! You can now login","success");
        
        setTimeout(()=>{
            window.location="login.html";
        },3000);
    }
    else if(str === "uap")
    {
        swal("Error!","Sorry! the userid is already present","error");
    }
    else
    {
        swal("Error!","Registration Failed! Try again","error");
    }
}

function handleError(xhr)
{  
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
}

function validateUser()
{
    if(username === "" || password === "" || 
       cpassword === "" || city === "" || 
       address === "" || adhar === "" || 
       email === "" || mobile === "")
    {
        swal("Error!","All fields are mandatory","error");
        return false;
    }
    
    return true;
}

function checkMobile()
{
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
        swal("Error!","mobile number should contain only digits","error");
        return false;
    }
    
    if(mobile.length > 10 || mobile.length < 10)
    {
        swal("Error!","Please use a 10 digit mobile number","error");
        return false;
    }
    
    return true;
}

function checkEmail()
{
    let attheratepos=email.indexOf("@");
    let dotpos=email.indexOf(".");
    
    if(attheratepos < 1 || dotpos < (attheratepos+2) || dotpos+2 >= email.length)
    {
        swal("Error!","Please enter a valid email","error");
        return false;
    }
    
    return true;
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

window.onload=addPartiestohomepage;

