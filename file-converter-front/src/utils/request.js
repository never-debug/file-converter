import axios from "axios";

const request = axios.create({
    baseURL: 'http://121.5.146.35:8888',
    withCredentials: true,
})

export default request;