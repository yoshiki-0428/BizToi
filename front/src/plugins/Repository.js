import axios from 'axios';

const baseUrl = process.env.VUE_APP_API_BASE_URL;
const bearer = `Bearer ${localStorage.getItem("idToken")}`;

export const apiAxios = axios.create({
  baseURL: baseUrl,
  headers: {
    "Authorization": bearer
  }
});

apiAxios.interceptors.response.use(
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

export const googleApi = axios.create({
  baseURL: `https://www.googleapis.com/books/v1/`
});