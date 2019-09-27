import {apiAxios} from "../plugins/Repository";

const resource = "/users";
export default {
  get() {
    return apiAxios.get(`${resource}/me`);
  }
}