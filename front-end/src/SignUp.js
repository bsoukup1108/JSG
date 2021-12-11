// Step 1 : Import React
import React from 'react'; // single quotes are preference
import Login from './Login';
//import username1 from './Login';
//import handleLogin from './Login';
//import Username from './Login';
//import Password from './Login';

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
        .then(data => {
            console.log(data)
            setResult(data);
        })
        .catch(console.log);
    };

    if(result !== null && result.isSuccess){
        
        return (
        <div>
            
        </div>
            //<body>
           // <div> 
           // <h2>Login</h2>
         //   <div>
          //  <input placeholder="username" value={username1} 
           //         onChange={e => Username(e.target.value)}/>
    
  //          </div>
    //        <div>
      //              <input placeholder="password" type="password" value={password}
        //            onChange={e => Password(e.target.value)}/>
          //      </div>
            //    <br></br>
              //  <div>
             //       <button onClick={handleLogin} >Login</button>
             //   </div>
      ///  </div>
       /// </body>
        );
    }
    return (
       <body>
        <div> 
            <h2>SignUp Here</h2>
            <div class="form"> 
            <div>
            <b>Username</b><br></br>
                <input placeholder="Email or Username" value={username} 
                onChange={e => setUsername(e.target.value)}/>
            </div>
            <br></br>
            <div>
            <b>Password</b><br></br>
                <input placeholder="Password" type="password" value={password}
                onChange={e => setPassword(e.target.value)}/>
            </div>
            <br></br>
            <div>
                <button onClick={handleSignUp} >Sign Up</button>
            </div>
            {(result !== null && !result.isSuccess) && <div>{result.message}</div> }
            </div>
        </div>
        </body>
    );
};

//Step 3 
export default SignUp; //equivallent to "public" in java
