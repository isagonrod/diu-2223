import axios from "axios";

export default axios.create({
  baseURL: "http://tut-igr.us-east-1.elasticbeanstalk.com",
  headers: {
    "Content-type": "application/json"
  }
});