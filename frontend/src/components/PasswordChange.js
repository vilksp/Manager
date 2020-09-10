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

export default function PasswordChange(props) {
  const BASE_URL = "http://localhost:8080/api/v1";
  const { usr } = useParams();
  const { register, handleSubmit, errors } = useForm();
  const [token] = useState(sessionStorage.getItem("token"));
  const classes = useStyles();
  const [currentPassword, setCurrentPassword] = useState("");
  const [username, setUsername] = useState("");

  useEffect(() => {
    axios
      .get(BASE_URL + `/users/${usr}`, {
        headers: { Authorization: token },
      })
      .then((result) => {
        // setCurrentPassword(result.data);
        let userName = result.data.username;
        console.log(`username ${result.data.username}`);
        setUsername(userName);
      });
  }, []);

  const onSubmit = (data) => {
    //e.preventDefault();

    const data_ = {...data, username };
    console.log(data);
    axios
      .post(BASE_URL + `/changePassword`, data_, {
        headers: { Authorization: token },
      })
      .then((result) => {
        props.history.push("/profile");
        console.log(data);
        console.log(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
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

            <div>
              <TextField
                className={classes.input}
                variant="outlined"
                label="Current password"
                type="password"
                name="currentPassword"
                required
                inputRef={register({
                  // validate: (value) =>
                  //   value === currentPassword.password || "Not match",
                })}
              />
              <div>
                {errors.currentPassword && (
                  <p>{errors.currentPassword.message}</p>
                )}
              </div>
            </div>
            <div>
              <TextField
                className={classes.input}
                label="New password"
                variant="outlined"
                type="password"
                name="newPassword"
                inputRef={register({
                  // validate: (value) =>
                  // value === currentPassword ||
                  // "The new password can not be the same as current one.",
                })}
              />

              <div className={classes.errorMessage}>
                {errors.newPassword && <p>{errors.newPassword.message}</p>}
              </div>
            </div>
            <div>
              <TextField
                className={classes.input}
                label="Confirm password"
                type="password"
                name="confirmPassword"
                variant="outlined"
                inputRef={register({
                  // validate: (value) =>
                  //   value === currentPassword.newPassword ||
                  //   "The confirm password must be the same as new one.",
                })}
              />
              <div className={classes.errorMessage}>
                {errors.confirmPassword && (
                  <p>{errors.confirmPassword.message}</p>
                )}
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
              // component={Link}
              // to="/profile"
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
        <p>{currentPassword.password}</p>
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
    color: "#ff0000",
    marginLeft: 10,
  },
  button: {
    marginLeft: "18px",
    marginBottom: "20px",
  },
}));
