import './App.css';

import { Link, Switch, Route } from 'react-router-dom';

import Home from './Home'; //relative Paths
import SignUp from './SignUp'; 
import Login from './Login';

function App() {
  return (
    
    
    <body>

      <div class="header">
      <h1>JSG Payments</h1>
      <p>A payment management site created by JSG.</p>
    </div>
    
    <div class="navbar">
      <ul>
        <a><li><Link to="/">Home</Link></li></a>
        <a><li><Link to="/login">Login</Link></li></a>
        <a><li><Link to="/sign-up">Sign Up</Link></li></a>
        <a><li><Link to="makeTransaction">Make Transaction</Link></li></a>
        <a class="right">
        <li><Link to="/">Transactions</Link></li>
        </a>
        </ul>
      </div>

      <div class="links">
      <Switch>
          <Route path="/sign-up" component={SignUp} />
          <Route path="/login" component={Login} />
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        </div>

      ]

      <div class="footer">
      <h2>Java Supremacy Gang</h2>
      </div>
      
       <br></br>
        </body>
       
        

    
  );
  }

export default App;
