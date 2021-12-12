
import './App.css';

import { Link, Switch, Route } from 'react-router-dom';

import Home from './Home'; //relative Paths
import SignUp from './SignUp'; 
import Login from './Login';
import Transact from './Transact';

function App() {
  return (
    <body>
    
      <div class="navbar">
        <ul>
        <a class="logo"><li ><Link to="/"></Link></li></a>
       
    
        <a class="right">
         <a class="link"><li><Link to="/login">Login</Link></li></a>
        <a class="link"><li><Link to="/sign-up">Sign Up</Link></li></a>
        <a class="link"><li><Link to="/makeTransaction">Make a Transaction</Link></li></a>
        
        </a>
        </ul>
      </div>

      
          <div class="header">
            <section id="section01" class="demo">
              <h1>JSG PAYMENTS</h1>
              <h3>A payment managment site created by JSG</h3>
              <a href="#section02" class="scroll"><span> </span></a>
          </section>
          </div>
        

  
       <div class="body" >
<div class="links">
  <section id="section02">
      <Switch>
          <Route path="/sign-up" component={SignUp} />
          <Route path="/login" component={Login} />
          <Route path="/makeTransaction" component={Transact} />
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        </section>
        </div>
        </div>
        
        <div >
            <section id="section03" class="demo">
              <h1 class="sec03h1">Developed By:</h1>
              <h3 class="chris">Chris Solorzano</h3>
              <h3 class="jacob">Jacob Zaionz</h3>
              <h3 class="terry">Terry Yoon</h3>
              <h3 class="brianna">Brianna Soukup</h3>
              {/* <h3>A payment managment site created by JSG</h3>
              <a href="#section02" class="scroll"><span> </span></a> */}
          </section>
          </div>

        <footer class="footer">
      <p class="foot">Java Supremacy Gang</p>
      </footer>
    </body>
  );
  }

export default App;