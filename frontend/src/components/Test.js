import React, { useEffect, useState } from "react";
import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";
import axios from "axios";
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

const BASE_URL = "http://localhost:8080";
const API = "/api/v1/test";
const PIC_BASE_URL = "http://localhost:8080/api/v1/files";
const PIC_BASE_DOWNLOAD= "http://localhost:8080/api/v1/downloadFile/";

export default function Test() {
  const [data, setData] = useState([]);
  const [picData, setPicData] = useState([]);
  const [pictureID, setPictureID] = useState([]);
  const [displedPicture, setDispledPicture] = useState(['blank']);

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
    console.log("Geting picture data")
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
    setPicData(response.data)

  })
  .catch((error) => {
    console.log(error);
  });
  }


  const downloadPicture = () => {
    axios({
      url: PIC_BASE_DOWNLOAD + pictureID,
      method: 'GET',
      responseType: 'blob', // important
      headers: { Authorization: token}
    }).then((response) => {
      console.log("Geting picture data")
      console.log(response.data)
      const url = window.URL.createObjectURL(new Blob([response.data], {type: response.data.type}  ))
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', response.data.type);
      document.body.appendChild(link);
      link.click();
    });
    }

    const displayPicture = () => {
      let url = PIC_BASE_DOWNLOAD + pictureID
      console.log(url);
      axios({
        url: url,
        method: 'GET',
        responseType: "ArrayBuffer",
        headers: { Authorization: token}
      }).then(response => {
        console.log("Displaying image")
        let image = Buffer.from(response.data, 'binary').toString('base64')
        setDispledPicture(`data:image/png;base64,${image}`)
      })
        .catch((err) => {
          console.log(err);
        });
      }
  




  return (
    <Container component="main" maxWidth="xs">
      <Paper variant="outlined" square>
        <Container>
          {" "}
          <p>Response code: {data.status}</p>
          <p>Response data: {data.data}</p>
          <p>Token: {token}</p>
          <p>Role: {role}</p>
          {picData.map((element)  => {
            return <div  key={element.id}> 
            <hr/>
              <p>ID: {element.id} </p>
              <p>createDate: {element.createDate} </p>
              <p>imageName: {element.imageName} </p>
              <p>status: {element.status} </p>
              <p>updateDate: {element.updateDate} </p>
            </div>
           })}
        </Container>
      </Paper>
      <br/>
        <Paper>
        Display picture data<br/>
      <Button variant="contained" onClick={() => getAllPicturesData()}>Get Pictures Data</Button>
      <br/>
      <TextField
          id="standard-number"
          label="Number"
          type="number"
          InputLabelProps={{
            shrink: true,
          }}
          onChange={(event) => {
            setPictureID(event.target.value);
          }}

        />       
        <Button variant="contained" onClick={() => downloadPicture()}>Download picture</Button>
        <Button variant="contained" onClick={() => displayPicture()}>Display picture</Button>
        <img scr={displedPicture}/>
        </Paper>
    </Container>
  );
}
