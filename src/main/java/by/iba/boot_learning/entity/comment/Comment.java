package by.iba.boot_learning.entity.comment;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "comments")
public class Comment {

    private BigInteger id;
    private String author_name;
    private String comment_content;

    public Comment() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
