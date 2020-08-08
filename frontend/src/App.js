import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Login from "./components/Login";
import Test from "./components/Test";
import ImageDropZone from "./components/ImageDropZone";
import Dashboard from "./components/dashboard/Dashboard";
import GetFiles from "./components/GetFiles";
import DeleteImage from "./components/DeleteImage";
import NavigationBar from "./components/NavigationBar"

export default function App() {
  return (
    <Router>

     <NavigationBar/>

        <Switch>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/users">
            <Users />
          </Route>
          <Route path="/test">
            <Test />
          </Route>
          <Route path="/image">
            <ImageDropZone />
            <GetFiles />
          </Route>
          <Route path="/delete/:id" exact component={DeleteImage}></Route>
          <Route path="/">
            <Login />
          </Route>
        </Switch>
      
    </Router>
  );
}

function About() {
  return <h2>About</h2>;
}

function Users() {
  return <h2>Users</h2>;
}
