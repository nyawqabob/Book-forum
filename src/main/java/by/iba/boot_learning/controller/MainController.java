package by.iba.boot_learning.controller;

import by.iba.boot_learning.entity.InsertResult;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.comment.Comment;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.sql.book.v1.BookIServiceImpl;
import by.iba.boot_learning.service.mongo.comment.v1.CommentServiceImpl;
import by.iba.boot_learning.service.sql.user.v1.UserIServiceImpl;
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
    private UserIServiceImpl userService;

    @Autowired
    private BookIServiceImpl bookService;

    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping("/user/insert")
    @ResponseBody
    public InsertResult user(@RequestBody User user) {
        return userService.insert(user);
    }


    @RequestMapping("/user/get")
    @ResponseBody
    public User getUser(@RequestParam(value = "userId") long id) {
        return userService.findObjectById(id);
    }

    @RequestMapping("/user/getAllUsers")
    @ResponseBody
    public List<User> getUsers() {
        return userService.loadAllObjects();
    }

    @RequestMapping("/comment")
    @ResponseBody
    public void comment(@RequestBody Comment comment) {
        commentService.insert(comment);
    }

    @RequestMapping("/comment/get")
    @ResponseBody
    public List<Comment> getComment(@RequestParam(value = "author") String authorName) {
        return commentService.findByAuthorName(authorName);
    }

    @RequestMapping("/comment/getAllComments")
    @ResponseBody
    public List<Comment> getComments() {
        return commentService.findAll();
    }

    @RequestMapping("/book/insert")
    @ResponseBody
    public InsertResult book(@RequestBody Book book) {
        return bookService.insert(book);
    }

    @RequestMapping("/book/get")
    @ResponseBody
    public Book getBook(@RequestParam(value = "bookId") long id) {
        return bookService.findObjectById(id);
    }

    @RequestMapping("/book/getAllBooks")
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.loadAllObjects();
    }


}
