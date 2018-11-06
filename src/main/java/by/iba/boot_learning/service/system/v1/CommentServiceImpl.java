package by.iba.boot_learning.service.system.v1;

import by.iba.boot_learning.dao.no_sql.comment.v1.CommentDaoImpl;
import by.iba.boot_learning.entity.comment.Comment;
import by.iba.boot_learning.service.system.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDaoImpl commentDao;

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public void insert(Comment comment) {
        commentDao.insert(comment);
    }

    @Override
    public List<Comment> findByAuthorName(String authorName) {
        return commentDao.findByAuthorName(authorName);
    }
}
