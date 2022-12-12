package com.rortegag.boker.models.listbook;

import com.rortegag.boker.models.book.Book;
import com.rortegag.boker.models.user.User;

import java.util.List;

public class ListBook {
    private User user;
    private List<Book> book;

    public ListBook() {}

    public ListBook(User user, List<Book> book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
