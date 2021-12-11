// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const username  = null;
const password  = null;
const amount  = null;


const body = {
    username: username,
    amount: amount,
    password: password,
};

const settings = {
    method: 'post',
    body: JSON.stringify(body),
};

fetch('/api/Transact', settings) //built in
.then(res => res.json())
.then(data =>{
    console.log(data)
    //var data1 = data;
    console.log("This is the amount " + data);
    //let amount2 = data;
  //  document.write(amount2);
 // document.getElementById('amount').innerHTML = amount;
  //sessionStorage.setItem("Amount",amount);
})
 

window.addEventListener('load', () => {
    const amount = localStorage.getItem('amount')
    const username = localStorage.getItem('username')
    const username1 = localStorage.getItem('username1')
    const message = localStorage.getItem('message')

    //These are the sources of the innerHTML error we keep getting
    //They're coming back as null so we need to find a way to think of these
    if(document.getElementById('amount').innerHTML != null){
        document.getElementById('amount').innerHTML = amount;
    }
    if(document.getElementById('username').innerHTML != null){
        document.getElementById('username').innerHTML = username;
    }
    if(document.getElementById('username1').innerHTML != null){
        document.getElementById('username1').innerHTML = username1;
    }
    if(document.getElementById('message').innerHTML != null){
        document.getElementById('message').innerHTML = message;
    }
});



const Home = () => { 
   // console.log(amount);


    return (
       <body>
        <><div>
        
        </div><div >
                <h2>What You Can Do</h2>
                <h5>Create an Account</h5>
                <h5>Login to an Existing Account</h5>
                <h5>Make a Transaction</h5>
                <h5>Look at Public Transactions</h5>
            </div></><br></br>
  
  <div>
      <h2>Public Transactions</h2>
      <div> 
      

      <h3 class = "box form" ><b> <span id="username1"></span></b> paid <span id="username"></span> $<span id="amount"></span> Message: <span id="message"> </span></h3>
      </div>
      
  </div>
  <br></br>
  <br></br>
            </body> 
    )
};



//Step 3 
export default Home; //equivallent to "public" in java
