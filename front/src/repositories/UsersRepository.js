import Repository from "../plugins/Repository";

const resource = "/users";
export default {
  get() {
    return Repository.get(`${resource}/me`);
  }
}