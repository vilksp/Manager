import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import axios from "axios";

const DeleteImage = (props) => {
  const { id } = useParams();
  const [image, setImage] = useState();
  const [token] = useState(sessionStorage.getItem("token"));
  const [role] = useState(sessionStorage.getItem("role"));

  useEffect(() => {
    axios.get(`http://localhost:8080/files/${id}`,
    { headers: { Authorization: token } })
    .then((result) => {
      setImage(result.data);
    });
  }, [id]);

  const handleRemoveImage = () => {
    axios.delete(`http://localhost:8080/files/${id}`,
    { headers: { Authorization: token } })
    .then((result) => {
      props.history.push("/image");
    });
  };

  return (
    <div className="delete-modal">
      <h2>You you want to delete this image?</h2>
      <div className="overlay"></div>
      <br />
      <div className="btn-group">
        <Link to="/image" className="btn btn-primary">
          Cancel
        </Link>
        <button onClick={handleRemoveImage} className="btn btn-danger">
          Delete
        </button>
      </div>
      <div></div>
    </div>
  );
};

export default DeleteImage;
