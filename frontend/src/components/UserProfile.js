import React, { useState, useEffect } from "react";
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

const useStyles = makeStyles((theme) => ({
  root: {
    minWidth: 275,
    margin: "20px",
  },
  input: {
    margin: theme.spacing(1),
    width: "70%",
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
}));

// I am using react-hook-form

const UpdateUser = (props) => {
  const { username } = useParams();
  const { register, handleSubmit, errors, setValue } = useForm();
  const [token] = useState(sessionStorage.getItem("token"));
  const classes = useStyles();

  const BASE_URL = "http://localhost:8080/api/v1";

  useEffect(() => {
    axios
      .get(BASE_URL + `/users/${username}`, {
        headers: { Authorization: token },
      })
      .then((result) => {
        setValue("firstName", result.data.firstName);
        setValue("lastName", result.data.lastName);
        setValue("email", result.data.email);
        setValue("description", result.data.description);
      });
  }, [username]);

  const onSubmit = (data) => {
    axios
      .put(
        BASE_URL + `/users/${username}`,

        data,
        {
          headers: { Authorization: token },
        }
      )
      .then((result) => {
        props.history.push("/profile");
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
              Update your profile
            </Typography>

            <div>
              <TextField
                className={classes.input}
                id="standard-basic"
                variant="outlined"
                label="First Name"
                type="text"
                name="firstName"
                inputRef={register({ required: false })}
              />
            </div>
            <div>
              <TextField
                className={classes.input}
                id="standard-basic"
                label="Last Name"
                variant="outlined"
                type="text"
                name="lastName"
                inputRef={register({ required: false, maxLength: 20 })}
              />
              {/* Example how to put an error message with react-hook-form*/}
              <div className={classes.errorMessage}>
                {errors.lastName && "Last name is too long"}
              </div>
            </div>
            <div>
              <TextField
                className={classes.input}
                id="standard-basic"
                label="Email"
                type="email"
                name="email"
                variant="outlined"
                inputRef={register({ required: false })}
              />
            </div>
            <div>
              <TextField
                className={classes.input}
                id="standard-multiline-static"
                label="Description"
                multiline
                rows={10}
                type="text"
                name="description"
                variant="outlined"
                inputRef={register({ required: false })}
              />
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
              Submit
            </Button>
          </CardActions>
        </form>
      </Card>
    </>
  );
};

export default UpdateUser;