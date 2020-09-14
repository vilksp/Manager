import React, { useState, useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import SaveIcon from "@material-ui/icons/Save";
import CancelIcon from "@material-ui/icons/Cancel";
import TextField from "@material-ui/core/TextField";
import Alert from "@material-ui/lab/Alert";

export default function PasswordChange(props) {
  const BASE_URL = "http://localhost:8080/api/v1";
  const { usr } = useParams();
  const { register, handleSubmit } = useForm();
  const [token] = useState(sessionStorage.getItem("token"));
  const classes = useStyles();
  const [username, setUsername] = useState("");
  const [error, setError] = useState([{ displayError: false, text: "" }]);
  const [submitMessage, setSubmitMessage] = useState([
    { disply: false, text: "", severity: "" },
  ]);
  const MIN_PASS_LENGTH = 6;

  useEffect(() => {
    axios
      .get(BASE_URL + `/users/${usr}`, {
        headers: { Authorization: token },
      })
      .then((result) => {
        let userName = result.data.username;
        setUsername(userName);
      });
  }, []);

  const validate = (data) => {
    if (data.newPassword !== data.confirmPassword) {
      setError({ displayError: true, text: "Password don't match" });
      return false;
    }
    if (
      data.newPassword.lenght < MIN_PASS_LENGTH ||
      data.confirmPassword.length < MIN_PASS_LENGTH
    ) {
      setError({
        displayError: true,
        text: `More then ${MIN_PASS_LENGTH} char long `,
      });
      return false;
    }
    setError({ displayError: false });
    return true;
  };

  const onSubmit = (data) => {
    const data_ = { ...data, username };
    let validated = validate(data);
    if (validated) {
      axios
        .post(BASE_URL + `/changePassword`, data_, {
          headers: { Authorization: token },
        })
        .then((result) => {
          setSubmitMessage({
            disply: true,
            text: "Password changed",
            severity: "success",
          });
        })
        .catch((error) => {
          if (error.response) {
            setSubmitMessage({
              disply: true,
              text: `${error.response.status} ${error.response.data}`,
              severity: "error",
            });
          }
        });
    }
  };

  return (
    <>
      <Card className={classes.root}>
        <form onSubmit={handleSubmit(onSubmit)} autoComplete="off">
          <CardContent>
            <Typography
              className={classes.title}
              color="textSecondary"
              gutterBottom
            >
              Change your password
            </Typography>
            {submitMessage.disply && (
              <Alert
                className={classes.errorMessage}
                severity={submitMessage.severity}
              >
                {submitMessage.text}
              </Alert>
            )}

            <div>
              <TextField
                className={classes.input}
                variant="outlined"
                label="Current password"
                type="password"
                name="currentPassword"
                inputRef={register}
                required
              />
            </div>
            <div>
              <TextField
                className={classes.input}
                label="New password"
                variant="outlined"
                type="password"
                name="newPassword"
                inputRef={register}
                required
                error={error.displayError}
                helperText={error.text}
              />
              <div>
                <TextField
                  className={classes.input}
                  label="Confirm password"
                  type="password"
                  name="confirmPassword"
                  variant="outlined"
                  inputRef={register}
                  required
                  error={error.displayError}
                  helperText={error.text}
                />
              </div>
            </div>
          </CardContent>
          <CardActions>
            <Button
              variant="contained"
              color="primary"
              size="small"
              className={classes.button}
              startIcon={<CancelIcon />}
            >
              <Link to="/profile" className={classes.cancel}>
                Cancel
              </Link>
            </Button>
            <Button
              variant="contained"
              color="primary"
              size="small"
              className={classes.button}
              startIcon={<SaveIcon />}
              type="submit"
            >
              Set new password
            </Button>
          </CardActions>
        </form>
      </Card>
    </>
  );
}

const useStyles = makeStyles((theme) => ({
  root: {
    maxWidth: "60%",
    margin: "20px",
  },
  input: {
    margin: theme.spacing(1),
    width: "90%",
    marginBottom: "20px",
  },
  title: {
    margin: theme.spacing(1),
    fontSize: 26,
  },
  pos: {
    marginBottom: 12,
  },
  cancel: {
    color: "#fff",
    "&:hover": {
      color: "#fff",
      textDecoration: "none",
    },
  },
  errorMessage: {
    marginLeft: 10,
    margin: 5,
  },
  button: {
    marginLeft: "18px",
    marginBottom: "20px",
  },
}));
