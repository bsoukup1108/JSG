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
        return(
            <div>
                Welcome {username}!
            </div>
        );
    }
    return (
        <div class="links">
        <form id="loginForm" action=""> 
            <h1>Login:</h1>
            <div class="tab">Login to Account
                <p><input placeholder="username" input type="username" value={username} onInput={e => Username(e.target.value)}>
                    </input>
                    </p>
            
                <p><input placeholder="password" input type="password" value={password} onInput={e => Password(e.target.value)}>
                </input>
                </p>
            </div>
            <div>
                <button onClick={handleLogin}>Login</button>
            </div>
            
        
        </form>
        </div>
    );
};

//Step 3 
export default Login; //equivallent to "public" in java