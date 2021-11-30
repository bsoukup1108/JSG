// Step 1 : Import React
import React from 'react'; // single quotes are preference

//Step 2 : Create a component function that returns an element
const SignUp = () => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [result, setResult] = React.useState(null);

    const handleSignUp = () => {
        console.log('User clicked sign up', username, password);
        const body = {
            username: username,
            password: password,
        };
        //make an http call to java
        const settings = {
            method: 'post',
            body: JSON.stringify(body),
        };
        fetch('/api/sign-up', settings) //built in
        .then(res => res.json())
        .then(data =>{
            console.log(data)
            setResult(data);
        })
        .catch(console.log);
    };

    if(result !== null && result.isSuccess){
        return(
            <div>
                Welcome {username}!
            </div>
        );
    }
    return (
        <form id="signupForm" action=""> 
            <h1>Sign Up:</h1>
            <div class="tab">Create an Account
                <p><input placeholder="username" input type="username" value={username} onChange={e =>setUsername(e.target.value)}>
                    </input>
                    </p>
            
                <p><input placeholder="password" input type="password" value={password} onChange={e =>setPassword(e.target.value)}>
                </input>
                </p>
            </div>
            <div>
                <button onClick={handleSignUp}>Sign Up</button>
            </div>
            
        
        </form>
    );
};


//Step 3 
export default SignUp; //equivallent to "public" in java