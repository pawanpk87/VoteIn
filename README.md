### VoteIn
```
VoteIn is an election management web application where voting will be handled by an admin who will add 
candidates for election. To vote for their favorite leaders, users need to register. Once signed up, 
the user will be able to cast a vote for their favorite leader.
```
###  Tech Stack
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)

![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)

![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)

![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)

![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

### Project Details
https://buildcode.org/projects/completed/VoteIn

Admin
- User(voters) Management
- Candidate(Leaders) Management
- Election Result

Voters
- Registration   
- Voting

```
VoteIn application will be handled by one admin so it's important to understand the admin module before voting. 
Every user either voters or candidates will have to first register as voters, now the admin is the one who is 
responsible for adding the user to candidate list.

Example:
  Name: Rahul Gandhi
  Adhar number:...

now Rahul Gandhi wants to become the candidate for the election then admin will add Rahul Gandhi to the Candidate list.
```

Admin Privileges:-
- User(voters) Management
  1.) Show Users
  2.) Remove Users
  3.)  Update Users
- Candidate Management
  1.) Add Candidate
  2.) Update Candidate Details
  3.) Remove the Candidate 
  4.) Show the Candidate
- Election Result

```
To vote, voters must first register using their adhar number. Users (voters) will be redirected to the login page after 
registering, and once they log in, they will be directed to the voting page where all candidates in the same city as the 
user (voter) will be displayed, so now the user can vote for anyone.
```

### Registration Module:-
```
    ____________________________
       
        registeration.html    
                              
        registration form     
                              
                              
        Register button       
    _____________________________
                |
                |
                |
       call the addUser() function
       of registeration.js
                |
                |
               \ /  
    _______________________________
                                  
        registeration.js      
                              
        addUser() Functions   
                              
    ______________________________
                |
                |
                |
       make post request to
       RegistrationControllerServlet
                |
                |
               \ / 
    ___________________________________________________
                                               
      RegistrationControllerServlet.java   
                                          
        1.) fetch the form data            
        2.) checks the user in             
            database                           
              a.) if a user is not present 
                  in database then         
                  register                 
              b.) if user is already       
                  present then response    
                  user is already          
                  registered               
                                           
    __________________________________________________
                        |
                        |
       RegistrationControllerServlet call
       registerUser() Functions of register the 
       user 
                        |
                        |
                       \ / 
             ___________________________
              
                RegistrationDAO.java  
                                      
                registerUser()        
                Functions register    
                user                 
             ___________________________     
                        |
                        |
       register user sends the response to the
       RegistrationControllerServlet and
       RegistrationControllerServlet forward 
       the request for the registration response.jsp 
       to generate response
                        |
                        |
                       \ / 
           finally registrationresponse.jsp
           send the response to the client
```


<!-- CONTACT -->
## Contact

Pawan Kumar Mehta - arowpk@gmail.com

Project Link: [https://github.com/pawanpk87/VoteIn](https://github.com/pawanpk87/VoteIn)
