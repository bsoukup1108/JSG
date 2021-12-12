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


    document.getElementById('amount').innerHTML = amount;
    document.getElementById('username').innerHTML = username;
    document.getElementById('username1').innerHTML = username1;
    document.getElementById('message').innerHTML = message;

});



const Home = () => { 
   // console.log(amount);


    return (
        <><body>


            <h2>Public Transactions</h2>
            <div>


                <h3 class="form"><b> <span id="username1"></span></b> paid <span id="username"></span> $<span id="amount"></span> Message: <span id="message"> </span></h3>
            </div>

        </body><br></br><br></br></>
            
    )
};



//Step 3 
export default Home; //equivallent to "public" in java
