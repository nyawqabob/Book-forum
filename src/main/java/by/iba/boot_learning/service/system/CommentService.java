package by.iba.boot_learning.service.system;

import by.iba.boot_learning.entity.comment.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void insert(Comment comment);

    List<Comment> findByAuthorName(String authorName);
}
