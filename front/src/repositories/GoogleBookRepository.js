import {googleApi} from "../plugins/Repository";

export default {
  getBooks(word) {
    return googleApi.get("volumes", {
      params: {
        q: word,
        Country: "JP",
        maxResults: 20,
        orderBy: "relevance"
      }
    });
  }
}