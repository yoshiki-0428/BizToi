import Repository from "../plugins/Repository";

const resource = "/auth";
export default {
  getTokenCode(code) {
    return Repository.get(`${resource}/token`, {
      params: {
        code: code,
        redirect_uri: `${window.location.origin}/top`
      }
    });
  }
}