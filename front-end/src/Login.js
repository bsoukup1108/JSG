// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const Login = () => {
    
    const [username1, Username] = React.useState('');
    const [password, Password] = React.useState('');
    const [result, setResult] = React.useState(null);

    const handleLogin = () => {
        console.log('User clicked login', username1, password);
        const body = {
            username: username1,
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
         localStorage.setItem("username1", "waiting");
        localStorage.setItem("username1", username1);
    };

    if(result !== null && result.validUsername && result.validPassword){
        window.location.href = "/sign-up"
        return(
            <div>
                Welcome {username1}!
            </div>
        );
    }
    else if(result !== null && result.validUsername == false && result.validPassword == false){
        window.location.href ="/"

        //window.location.href = "/Home.js"
        //window.print("Invalid username, please try again")
        //The above statement actually gets you to print the webpage, please don't use that
        alert("Invalid username, please try again")
    }
    return (
        <body>
        <div> 
            
        <h2>Login Here</h2>
        <br></br>
        <div class="form"> 
        <div>
            <b>Username</b><br></br>
        <input placeholder="Email or Username" value={username1} 
                onChange={e => Username(e.target.value)}/>

        </div>
        <br></br>
        <div>
            <b>Password</b><br></br>
                <input placeholder="Password" type="password" value={password}
                onChange={e => Password(e.target.value)}/>
            </div>
            <br></br>
            <div>
                <button onClick={handleLogin} >Login</button>
            </div>
            </div>
    </div>
    </body>
    );
    
};


//Step 3 
export default Login; //equivallent to "public" in java
