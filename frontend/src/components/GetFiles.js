import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { FaTrash } from "react-icons/fa";
import { useParams } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/v1";

const GetFiles = () => {
  // saving value to state
  const { id } = useParams();
  const [images, setImages] = useState([]);
  const [token] = useState(sessionStorage.getItem("token"));
  const [role] = useState(sessionStorage.getItem("role"));

  useEffect(() => {
    axios
      .get(BASE_URL + "/files", { headers: { Authorization: token } })
      .then((result) => {
        console.log(`Token here:  ${token}`);
        console.log(`result here:  ${result}`);
        console.log(result);
        setImages(result.data);
      });
  }, []);

  // useEffect(() => {
  //   axios({
  //     url: `http://localhost:8000/api/v1/downloadFile/${id}`,
  //     method: "GET",
  //     responseType: "blob",
  //     headers: {
  //       Authorization: token,
  //     },
  //   }).then((response) => {
  //     var fileURL = window.URL.createObjectURL(new Blob([response.data]));
  //     var fileLink = document.createElement("a");

  //     fileLink.href = fileURL;
  //     fileLink.setAttribute("download", "file.jpg");
  //     document.body.appendChild(fileLink);

  //     fileLink.click();
  //     setImagess(response.data);
  //   });
  // }, [id]);

  // const download = (e) => {
  //   axios
  //     .get(BASE_URL + "/downloadImage/" + id, {
  //       responseType: "arraybuffer",
  //       headers: {
  //         Authorization: token,
  //       },
  //     })
  //     .then((response) => {
  //       const url = window.URL.createObjectURL(new Blob([response.data]));
  //       const link = document.createElement("a");
  //       link.href = url;
  //       link.setAttribute("download", "file.jpg"); //or any other extension
  //       document.body.appendChild(link);
  //       link.click();
  //       console.log(`Token here:  ${token}`);
  //       console.log(`result here:  ${response}`);
  //       console.log(response);
  //     })
  //     .catch((e) => console.log(e));
  // };


  const imageDisplay = (url) => {
    console.log(url);
    axios({
      url: url,
      method: 'GET',
      responseType: 'arraybuffer',
      headers: { Authorization: token}
    })
      .then((response) => {
        let blob = new Blob(
          [response.data], 
          { type: response.headers['content-type'] }
        )
        let image = URL.createObjectURL(blob)
        return image
      })
      .catch((err) => {
        console.log(err);
      });
  };


  const download = (e, url) => {
    console.log(url);
    axios({
      url: url,
      method: 'GET',
      responseType: 'blob', // important
      headers: { Authorization: token}
    })
      .then((response) => {
        console.log("Geting picture data")
        const url = window.URL.createObjectURL(new Blob([response.data], {type: response.data.type}  ))
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', response.data.type);
        document.body.appendChild(link);
        link.click();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <table className="table ">
      <thead>
        <tr>
          <th className="text-center">Nr.</th>
          <th className="text-center">Image preview</th>
          <th className="text-center">Image name</th>
          <th className="text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        {images.map((image) => (
          <tr key={image.id}>
            <td className="text-center">{image.id}</td>

            <td className="text-center">
              <a href={BASE_URL + `/downloadFile/` + image.id}>
                <img
                  style={{ width: "100px" }}
                  src={imageDisplay(BASE_URL + `/downloadFile/` + image.id)}
                />
              </a>
            </td>
            <td className="text-center">
              {image.imageType === "application/pdf" ? (
                <span>{image.imageName}</span>
              ) : (
                <a
                href="#"
                  onClick={(e) => download(e, `${BASE_URL}/downloadFile/${image.id}`)}
                >
                  {image.imageName}
                </a>
              )}
            </td>
            <td>
              <Link
                to={`/delete/${image.id}`}
                className="btn d-flex justify-content-center"
              >
                <FaTrash />
              </Link>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default GetFiles;
