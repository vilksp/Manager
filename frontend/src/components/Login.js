import React, { useState, useCallback } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Copyright from "../components/Copyright";
import axios from "axios";
import { Button, Form, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from "../components/auth/Authentication";

const BASE_URL = "http://localhost:8080";

export default function SignIn() {
  const [mail, setMail] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState("");
  const [roles, setRoles] = useState("");

  const loginClicked = (event) => {
    console.log(`password ${password} mail ${mail}`)
    event.preventDefault();
    Auth.executeJwtAuthenticationService(mail, password)
      .then((res) => {
        setToken(res.data.token);
        setRoles(res.data.token);

        console.log(`Roles: ${res.data.roles}`);
        console.log(`Token:  ${res.data.token}`);

        Auth.registerJwtTT(res.data.token);
        Auth.registerUserRole(res.data.roles);
        // push to main page
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const classes = useStyles();
  return (
    <Container component="main" maxWidth="xs">
      <Form>
        <Form.Group >
          <Form.Label>Username</Form.Label>
          <Form.Control type="text" placeholder="Enter username"
            onChange={(event) => {
              setMail(event.target.value);
            }}
          />
          <Form.Text className="text-muted">
            We'll never share your email with anyone else.
    </Form.Text>
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </Form.Group>
        <Form.Group controlId="formBasicCheckbox">
          <Form.Check type="checkbox" label="Check me out" />
        </Form.Group>
        <Button as="input" type="submit" value="Submit" 
         onClick={(event) => loginClicked(event)} />
      </Form>



    </Container>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));
