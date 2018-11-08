package by.iba.boot_learning.constants.database.book;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:book_fields.properties")
@ConfigurationProperties()
public class BookFields {
    private String bookType;
    private String dateWhenAdded;
    private String editionName;
    private String name;
    private String price;
    private String userEmail;
    private String yearOfEdition;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookType() {
        return bookType;
    }

    public String getDateWhenAdded() {
        return dateWhenAdded;
    }

    public String getEditionName() {
        return editionName;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getYearOfEdition() {
        return yearOfEdition;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setDateWhenAdded(String dateWhenAdded) {
        this.dateWhenAdded = dateWhenAdded;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setYearOfEdition(String yearOfEdition) {
        this.yearOfEdition = yearOfEdition;
    }
}
