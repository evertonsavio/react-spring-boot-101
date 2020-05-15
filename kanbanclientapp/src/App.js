//yarn add redux@4.0.0 react-redux@5.0.7 redux-thunk@2.3.0
//yarn add axios@0.18.0
//yarn add classnames

import React, {Component} from 'react';
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import AddProject from './components/Project/AddProject';
import {Provider} from 'react-redux';
import store from './store';
import UpdateProject from './components/Project/UpdateProject';

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addProject" component={AddProject} />
            <Route exact path="/updateProject/:id" component={UpdateProject} />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
