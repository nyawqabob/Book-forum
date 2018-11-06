package by.iba.boot_learning.dao.no_sql.comment.v1;


import by.iba.boot_learning.dao.exception.DaoException;
import by.iba.boot_learning.dao.no_sql.comment.CommentDao;
import by.iba.boot_learning.entity.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Comment> findByAuthorName(String authorName) {
       List<Comment> comments = mongoOperations.find(Query.query(Criteria.where("author_name").is(authorName)), Comment.class);
       if(comments.isEmpty()){
           throw new DaoException("Comments of author "+authorName+" was not found. ");
       }
       return comments;
    }

    @Override
    public List<Comment> findAll() {
        return mongoOperations.findAll(Comment.class);
    }

    @Override
    public void insert(Comment comment) {
        mongoOperations.insert(comment);
    }


}
