import React, { useState, useEffect } from "react";
import axios from "axios";

const GetFiles = () => {
  // saving value to state
  const [images, setImages] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/files").then((result) => {
      setImages(result.data);
    });
  }, []);

  return (
    <table className="table ">
      <thead>
        <tr>
          <th className="text-center">Nr.</th>
          <th className="text-center">Image name</th>
          <th className="text-center">Image preview</th>
        </tr>
      </thead>
      <tbody>
        {images.map((image) => (
          <tr key={image.id}>
            <td className="text-center">{image.id}</td>
            <td className="text-center">
              {image.imageType === "application/pdf" ? (
                <span>{image.imageName}</span>
              ) : (
                <a href={`http://localhost:8080/downloadFile/` + image.id}>
                  {image.imageName}
                </a>
              )}
            </td>
            <td className="text-center">
              <a href={`http://localhost:8080/downloadFile/` + image.id}>
                <img
                  style={{ width: "100px" }}
                  src={`http://localhost:8080/downloadFile/` + image.id}
                />
              </a>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default GetFiles;
