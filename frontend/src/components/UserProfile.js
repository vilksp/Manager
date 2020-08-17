import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import EditIcon from "@material-ui/icons/Edit";
import Divider from "@material-ui/core/Divider";
import CardHeader from "@material-ui/core/CardHeader";
import Avatar from "@material-ui/core/Avatar";
import IconButton from "@material-ui/core/IconButton";
import { blueGrey } from "@material-ui/core/colors";

const useStyles = makeStyles((theme) => ({
  root: {
    maxWidth: 645,
    marginLeft: "30px",
  },
  avatar: {
    backgroundColor: blueGrey[500],
    width: theme.spacing(7),
    height: theme.spacing(7),
  },
  cardheader: {
    background: "#ebe9e7",
  },
  TypographyHeaders: {
    marginTop: "20px",
  },
}));

const UserProfile = () => {
  const { username } = useParams();
  const [data, setData] = useState([]);
  const [token] = useState(sessionStorage.getItem("token"));
  const classes = useStyles();
  const [role] = useState(sessionStorage.getItem("role"));
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/username`, {
        headers: { Authorization: token },
      })
      .then((result) => {
        setData(result.data);
        console.log(result.data);
      });
  }, [username]);

  return (
    <div>
      <Card className={classes.root}>
        <CardHeader
          avatar={
            <Avatar aria-label="photo" className={classes.avatar}>
              JD
            </Avatar>
          }
          action={
            <IconButton
              aria-label="edit"
              component={Link}
              to={`/update/${data.username}`}
              style={{ color: "#555" }}
              title="Edit"
            >
              <EditIcon />
            </IconButton>
          }
          className={classes.cardheader}
        />
        <CardContent>
          <Typography variant="h3" gutterBottom>
            Hello {data.firstname} {data.lastname} !
          </Typography>
          <Divider />
          <Typography variant="h6" className={classes.TypographyHeaders}>
            First name:
          </Typography>
          <Typography>{data.firstname}</Typography>
          <Typography
            variant="h6"
            gutterBottom
            className={classes.TypographyHeaders}
          >
            Last name:
          </Typography>
          <Typography gutterBottom>{data.lastname}</Typography>
          <Typography
            variant="h6"
            gutterBottom
            className={classes.TypographyHeaders}
          >
            Username:
          </Typography>
          <Typography gutterBottom>{data.username}</Typography>
          <Typography
            variant="h6"
            gutterBottom
            className={classes.TypographyHeaders}
          >
            Email:
          </Typography>
          <Typography gutterBottom>{data.email}</Typography>
          <Typography
            variant="h6"
            gutterBottom
            className={classes.TypographyHeaders}
          >
            About me:
          </Typography>
          <Typography paragraph>{data.description}</Typography>
        </CardContent>
      </Card>
    </div>
  );
};

export default UserProfile;
