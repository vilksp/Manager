import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./components/Login";
import Test from "./components/Test";
import ImageDropZone from "./components/ImageDropZone";
import Dashboard from "./components/dashboard/Dashboard";
import GetFiles from "./components/GetFiles";
import DeleteImage from "./components/DeleteImage";
import UpdateUser from "./components/UpdateUser";

import UserProfile from "./components/UserProfile";
import Authentication from "./components/auth/Authentication";
import PasswordChange from "./components/PasswordChange";

export default function App() {
  const isUserLoggedIn = Authentication.isUserLoggedIn();

  return (
    <Router>
      {!isUserLoggedIn && (
        <div>
          <Route path={["/", "/login"]}>
            <Login />
          </Route>
        </div>
      )}
      {isUserLoggedIn && (
        <div>
          <Dashboard>
            <Switch>
              <Route path="/about">
                <About />
              </Route>
              <Route path="/profile">
                <UserProfile />
              </Route>
              <Route path="/test">
                <Test />
              </Route>
              <Route path="/image">
                <ImageDropZone />
                <GetFiles />
              </Route>
              <Route path="/delete/:id" exact component={DeleteImage}></Route>
              <Route
                path="/update/:username"
                exact
                component={UpdateUser}
              ></Route>
              <Route
                path="/password/:usr"
                exact
                component={PasswordChange}
              ></Route>
              <Route path="/home">
                <Home />
              </Route>
            </Switch>
          </Dashboard>
        </div>
      )}
    </Router>
  );
}

function About() {
  return <h2>About</h2>;
}

function Home() {
  return <h2>Welcome to home page</h2>;
}
