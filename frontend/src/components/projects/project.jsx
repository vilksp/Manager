import React from "react";
import SimpleTable from "./table.component";
import axios from "axios";

class Project extends React.Component {
  constructor() {
    super();
    this.state = {
      projects: [
        {
          id: "",
          name: "",
          status: "",
          address: "",
          duration: "",
          deadline: "",
          description: "",
          priority: "",
          start_date: "",
          end_date: "",
        },
      ],
    };
  }

  getData = () => {
    const token = window.sessionStorage.getItem("token");

    this.api = "http://localhost:8080/api/v1/projects?page=0&size=15";
    axios
      .get(this.api, { headers: { Authorization: token } })
      .then((response) => {
        this.setState({ projects: response.data });
      })
      .catch((error) => console.log(error));
  };

  componentDidMount() {
    this.getData();
  }

  render() {
    const { proj } = this.state.projects;
    return (
      <div>
        <SimpleTable projects={this.state.projects} />
      </div>
    );
  }
}
export default Project;
