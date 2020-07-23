import React, {useEffect, useState} from 'react'
import Container from "@material-ui/core/Container";
import axios from "axios";


const BASE_URL = "http://localhost:8080";
const API = "/api/v1/user/test";


export default function Test() {
    const [data, setData] = useState([]);
    useEffect(() => {
        axios
          .get(BASE_URL + API, {
            params: {},
            withCredentials: true,
            auth: {
                username: 'user',
                password: 'user'
            }
        })
          .then((response) => {
            console.log(response.data);
            console.log(response.status);
            console.log(response.statusText);
            console.log(response.headers);
            console.log(response.config);
            setData(response)
          })
          .catch((error) => {
            console.log(error);
          });
      }, []);

    return (
        <Container component="main" maxWidth="xs">
        Response code: {data.status}
        </Container>
    )
}
