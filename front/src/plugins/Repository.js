import axios from 'axios';

const baseUrl = process.env.VUE_APP_API_BASE_URL;
const bearer = `Bearer ${localStorage.getItem("idToken")}`;

const http = axios.create({
  baseURL: baseUrl,
  headers: {
    "Authorization": bearer
  }
});

http.interceptors.response.use(
    response => response,
    error => {
      if (error.response) {
        console.log(error.response);
        // 認証エラー
        if (error.response.status === 401) {
          location.href = `${process.env.VUE_APP_API_BASE_URL}auth/login`;
        }
      }
});

export default http