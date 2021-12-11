// Step 1 : Import React
import React from 'react'; // single quotes are preference
import Home from './Home';



//Step 2 : Create a component function that returns an element
const Transact = () => {

    const [username, Username] = React.useState('');
    const [amount, Amount] = React.useState('');
    const [password, Password] = React.useState('');
    const [result, setResult] = React.useState(null);
    const [message, Message] = React.useState('');

    const handleTransaction = () => {
        console.log('User clicked Send Transaction', username, password);
        const body = {
            username: username,
            amount: amount,
            password: password,
        };
        //make an http call to java
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

            setResult(data);
        })

        .catch(console.log);

        localStorage.setItem("username","Waiting");
        localStorage.setItem("amount",0);
        localStorage.setItem("amount", amount);
        localStorage.setItem("username", username);
        localStorage.setItem("message", message);
        
    };
    if(result !== null && result.validUsername){
        window.location.href ="/Home.js"
         return(
             <div>
                 Your transaction has been completed {username} !
             </div>
         );
     }
    return (
        <body>
        <div> 
        <h2>Make a Transaction</h2>
        
    
        <h4>Who Are You Sending This To?</h4>
        
        <div>
        <input placeholder="username" value={username} 
                onChange={e => Username(e.target.value)}/>

        </div>
    
        <h4>Payment Type</h4>

        <div class="dropdown">
            <button class="dropbtn">Select Payment</button>
            <div class="dropdown-content">
            <a href="#">Credit</a>
            <a href="#">Debit</a>
            <a href="#">Electronic Bank Transfer</a>
            </div>
        </div>
        <br></br><br></br>
        <input placeholder="card/electronic bank #"/>

        
        <h4>Amount</h4>
        <div>
                <input placeholder="amount in dollars" type="amount" value={amount}
                onChange={e => Amount(e.target.value)}/>
            </div>

            <h4>Message</h4>
            <input placeholder="send a message" type="message" onChange={e => Message(e.target.value)}/>

            <h4>Your Password to Confirm Transaction</h4>
            <div>
                <input placeholder="password" type="password" value={password}
                onChange={e => Password(e.target.value)}/>
            </div>
            <br></br>
            <div>
                <button onClick={handleTransaction} method ="POST" >Send Transaction</button>
            </div>
            <h2>Amount Sent: <span id="amount"></span></h2>
            </div>
    
    </body>
    );
}
   

//Step 3 
export default Transact; //equivallent to "public" in java
