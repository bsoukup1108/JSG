import './App.css';

import { Link, Switch, Route } from 'react-router-dom';

import Home from './Home'; //relative Paths
import SignUp from './SignUp'; 
import Login from './Login';

function App() {
  return (
    <div>
  <div class="split left">
  <div class="centered">
  <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/sign-up">Sign Up</Link></li>
        </ul>
      <Switch>
        <Route path="/sign-up" component={SignUp} />
        <Route path="/login" component={Login} />
        <Route path="/">
        <Home/>
        </Route>
      </Switch>
  </div>
</div>
       
    </div>

    
  );
}

export default App;
