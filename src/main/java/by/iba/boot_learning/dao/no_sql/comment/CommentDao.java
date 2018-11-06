package by.iba.boot_learning.dao.no_sql.comment;

import by.iba.boot_learning.entity.comment.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

    List<Comment> findAll();

    void insert(Comment comment);

    List<Comment> findByAuthorName(String authorName);
}
