package by.iba.boot_learning.controller;

import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.comment.Comment;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.book.v1.BookServiceImpl;
import by.iba.boot_learning.service.system.v1.CommentServiceImpl;
import by.iba.boot_learning.service.user.v1.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    CommentServiceImpl commentService;

    @RequestMapping("/user")
    @ResponseBody
    public void user(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/user/get")
    @ResponseBody
    public User getUser(@RequestParam(value = "userId") long id) {
        return userService.findObjectById(id);
    }

    @RequestMapping("/comment")
    @ResponseBody
    public void comment(@RequestBody Comment comment) {
        commentService.insert(comment);
    }

    @RequestMapping("/comment/get")
    @ResponseBody
    public List<Comment> getComment(@RequestParam(value = "author") String authorName) {
        List<Comment> comment = commentService.findByAuthorName(authorName);
        return comment;
    }

    @RequestMapping("/comment/getAllComments")
    @ResponseBody
    public List<Comment> getComments() {
        return commentService.findAll();
    }

    @RequestMapping("/book")
    @ResponseBody
    public void book(@RequestBody Book book) {
        bookService.insert(book);
    }

    @RequestMapping("/book/getAllBooks")
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.loadAllObjects();
    }


}
