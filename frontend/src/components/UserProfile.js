import React, { useState, useEffect } from "react";
import axios from "axios";

const UserProfile = () => {
  const [data, setData] = useState([]);
  const [token] = useState(sessionStorage.getItem("token"));
  const [role] = useState(sessionStorage.getItem("role"));
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/username`, {
        headers: { Authorization: token },
      })
      .then((result) => {
        setData(result.data);
      });
  }, []);

  return (
    <div>
      <h1>
        Hello {data.username}
        <br />
        <small>Your role is {role} </small>
      </h1>
      <p>Your email is {data.email}</p>
      <p>Your first name is {data.firstname}</p>
      <p>Your last name is {data.lastname}</p>
    </div>
  );
};

export default UserProfile;