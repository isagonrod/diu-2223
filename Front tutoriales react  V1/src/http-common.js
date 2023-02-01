import axios from "axios";

export default axios.create({
  baseURL: "http://tutorialsisagonzalez-env.eba-z9zamc9j.us-east-1.elasticbeanstalk.com",
  headers: {
    "Content-type": "application/json"
  }
});