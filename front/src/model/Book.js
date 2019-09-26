class Book {
  constructor(volumeInfo) {
    if (volumeInfo && volumeInfo.industryIdentifiers)
      this.isbn = volumeInfo.industryIdentifiers.identifier;
    if (volumeInfo && volumeInfo.imageLinks)
      this.pictureUrl = volumeInfo.imageLinks.thumbnail;
    this.title = volumeInfo.title;
    this.detail = volumeInfo.description;
    this.linkUrl = volumeInfo.infoLink;
  }
}

export default Book