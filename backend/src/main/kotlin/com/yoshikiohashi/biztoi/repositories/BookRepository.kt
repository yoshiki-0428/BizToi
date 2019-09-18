package com.yoshikiohashi.biztoi.repositories

import com.yoshikiohashi.biztoi.Tables.BOOK
import com.yoshikiohashi.biztoi.tables.pojos.Book
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class BookRepository(private val ctx: DSLContext) {
    fun findAll(): List<Book> = ctx
            .select()
            .from(BOOK)
            .fetch()
            .into(Book::class.java)

    fun findIsbn(isbn: String): Book = ctx
            .select()
            .from(BOOK)
            .where(BOOK.ISBN.eq(isbn))
            .fetchOne()
            .into(Book::class.java)

    fun findId(id: Int): Book = ctx
            .select()
            .from(BOOK)
            .where(BOOK.ID.eq(id))
            .fetchOne()
            .into(Book::class.java)

    fun create(book: Book): Book = ctx
            .insertInto(BOOK, BOOK.ID, BOOK.TITLE, BOOK.DETAIL, BOOK.ISBN, BOOK.LINK_URL, BOOK.PICTURE_URL)
            .values(book.id, book.title, book.detail, book.isbn, book.linkUrl, book.pictureUrl)
            .returning()
            .fetchOne()
            .into(Book::class.java)
}