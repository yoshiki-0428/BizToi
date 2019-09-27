import {apiAxios} from "../plugins/Repository";

const resource = "/auth";
export default {
  getTokenCode(code) {
    return apiAxios.get(`${resource}/token`, {
      params: {
        code: code,
        redirect_uri: `${window.location.origin}/top`
      }
    });
  }
}