package by.iba.boot_learning.entity.book;

import by.iba.boot_learning.entity.book.type.BookType;


public class Book {

    private long id;
    private String name;
    private String editionName;
    private int yearOfEdition;
    private BookType bookType;
    private int price;
    private String dateWhenAdded;
    private String userEmail;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public int getYearOfEdition() {
        return yearOfEdition;
    }

    public void setYearOfEdition(int yearOfEdition) {
        this.yearOfEdition = yearOfEdition;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateWhenAdded() {
        return dateWhenAdded;
    }

    public void setDateWhenAdded(String dateWhenAdded) {
        this.dateWhenAdded = dateWhenAdded;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
