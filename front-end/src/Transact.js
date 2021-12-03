// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const Transact = () => {

    const [username, Username] = React.useState('');
    const [amount, Amount] = React.useState('');
    const [password, Password] = React.useState('');
    const [result, setResult] = React.useState(null);


    const handleTransaction = () => {
        console.log('User clicked login', username, password);
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
            setResult(data);
        })
        .catch(console.log);
    };

    return (
        <body>
        <div class="scroll"> 
        <h1>Make a Transaction</h1>
        
    
        <h4>Who Are You Sending This To?</h4>
        
        <div>
        <input placeholder="username" value={username} 
                onChange={e => Username(e.target.value)}/>

        </div>
    
        <h4>Amount</h4>
        <div>
                <input placeholder="amount in dollars" type="amount" value={amount}
                onChange={e => Amount(e.target.value)}/>
            </div>
            
            <h4>Your Password to Confirm Transaction</h4>
            <div>
                <input placeholder="password" type="password" value={password}
                onChange={e => Password(e.target.value)}/>
            </div>
            <br></br>
            <div>
                <button onClick={handleTransaction} >Send Transaction</button>
            </div>
            </div>
    
    </body>
    );
}
   

//Step 3 
export default Transact; //equivallent to "public" in java
