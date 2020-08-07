import React, { useState, useCallback } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Copyright from "../components/Copyright";
import Test from "../components/Test";
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
      <Test />
      <Form>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
          <Form.Text className="text-muted">
            We'll never share your email with anyone else.
    </Form.Text>
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Form.Group controlId="formBasicCheckbox">
          <Form.Check type="checkbox" label="Check me out" />
        </Form.Group>
        <Button variant="primary" type="submit" onClick={(event) => loginClicked(event)}>
          Submit
  </Button>
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
