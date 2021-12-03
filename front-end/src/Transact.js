// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const Transact = () => {

    const [username, Username] = React.useState('');
    const [amount, Amount] = React.useState('');
    const [password, Password] = React.useState('');

    const handleTransaction = () => {

    };
    return (
        <body>
        <div> 
        <h1>Make a Transaction</h1>
        
    
        <h2>Who Are You Sending This To?</h2>
        
        <div>
        <input placeholder="username" value={username} 
                onChange={e => Username(e.target.value)}/>

        </div>
    
        <h2>Amount</h2>
        <div>
                <input placeholder="amount in dollars" type="amount" value={amount}
                onChange={e => Amount(e.target.value)}/>
            </div>
            
            <h2>Your Password to Confirm Transaction</h2>
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
