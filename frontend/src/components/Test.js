import React, { useEffect, useState } from "react";
import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";
import axios from "axios";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";

const BASE_URL = "http://localhost:8080";
const API = "/api/v1/test";
const PIC_BASE_URL = "http://localhost:8080/api/v1/files";
const PIC_BASE_DOWNLOAD = "http://localhost:8080/api/v1/downloadFile/";

export default function Test() {
  const [data, setData] = useState([]);
  const [picData, setPicData] = useState([]);
  const [pictureID, setPictureID] = useState([]);
  const [displedPicture, setDispledPicture] = useState(["blank"]);

  const [token] = useState(sessionStorage.getItem("token"));
  const [role] = useState(sessionStorage.getItem("role"));

  useEffect(() => {
    axios
      .get(BASE_URL + API, { headers: { Authorization: token } })
      .then((response) => {
        console.log(response.data);
        console.log(response.status);
        console.log(response.statusText);
        console.log(response.headers);
        console.log(response.config);
        setData(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const getAllPicturesData = () => {
    axios
      .get(PIC_BASE_URL, { headers: { Authorization: token } })
      .then((response) => {
        console.log("Geting picture data");
        console.log(response.data);
        console.log(response.status);
        console.log(response.statusText);
        console.log(response.headers);
        console.log(response.config);
        setPicData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Container component="main" maxWidth="xs">
      <Paper variant="outlined" square>
        <Container>
          {" "}
          <p>Response code: {data.status}</p>
          <p>Response data: {data.data}</p>
          <p>Token: {token}</p>
          <p>Role: {role}</p>
        </Container>
      </Paper>
      <br />
    </Container>
  );
}
