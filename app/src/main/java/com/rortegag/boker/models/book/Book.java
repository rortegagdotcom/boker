package com.rortegag.boker.models.book;

public class Book {
    private String title;
    private String isbn;
    private String genre;
    private String author;
    private String synopsis;

    public Book() {}

    public Book(String name, String isbn, String genre, String author, String synopsis) {
        this.title = name;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
