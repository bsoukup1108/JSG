// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element

window.addEventListener('load', () => {
    const amount = localStorage.getItem('amount')
    const username = localStorage.getItem('username')
    const username1 = localStorage.getItem('username1')

    document.getElementById('amount').innerHTML = amount;
    document.getElementById('username').innerHTML = username;

    document.getElementById('username1').innerHTML = username1;

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
            </div></>
  
  <div>
      <h1>Public Transactions</h1>
      <div class="paid"> 
      
      <h1 span id ="username1" > </h1>
      <h1> paid </h1>
      <h1>User : <span id="username"> </span> </h1>

      <h1>Amount : $<span id="amount"> </span> </h1>
      
      </div>
  </div>
            </body> 
    )
};



//Step 3 
export default Home; //equivallent to "public" in java
