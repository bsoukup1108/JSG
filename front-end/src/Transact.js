// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const Transact = () => {
    const [username, Username] = React.useState('');
    const [password, Password] = React.useState('');
    const [result, setResult] = React.useState(null);

    const handleTransact = () => {
        console.log('User clicked makeTransaction', username, password);
        const body = {
            username: username,
            password: password,
        };
        //make an http call to java
        const settings = {
            method: 'post',
            body: JSON.stringify(body),
        };
        fetch('/api/makeTransaction', settings) //built in
        .then(res => res.json())
        .then(data =>{
            console.log(data)
            setResult(data);
        })
        .catch(console.log);
    };

    if(result !== null && result.validUsername){
       window.location.href ="/Home.js"
        return(
            <div>
                Welcome {username}!
            </div>
        );
    }
    return (
        <div> 
        <h1>Transaction</h1>
        <div>
        <input value={username} 
                onChange={e => Username(e.target.value)}/>

        </div>
        <div>
                <input type="password" value={password}
                onChange={e => Password(e.target.value)}/>
            </div>
            <div>
                <button onClick={handleTransact} >Confirm</button>
            </div>
    </div>
    );
};

//Step 3 
export default Transact; //equivallent to "public" in java
