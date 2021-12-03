// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const Login = () => {
    const [username, Username] = React.useState('');
    const [password, Password] = React.useState('');
    const [result, setResult] = React.useState(null);

    const handleLogin = () => {
        console.log('User clicked login', username, password);
        const body = {
            username: username,
            password: password,
        };
        //make an http call to java
        const settings = {
            method: 'post',
            body: JSON.stringify(body),
        };
        fetch('/api/login', settings) //built in
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
        <h1>Login</h1>
        <div>
        <input placeholder="username" value={username} 
                onChange={e => Username(e.target.value)}/>

        </div>
        <div>
                <input placeholder="password" type="password" value={password}
                onChange={e => Password(e.target.value)}/>
            </div>
            <br></br>
            <div>
                <button onClick={handleLogin} >Login</button>
            </div>
    </div>
    );
};

//Step 3 
export default Login; //equivallent to "public" in java
