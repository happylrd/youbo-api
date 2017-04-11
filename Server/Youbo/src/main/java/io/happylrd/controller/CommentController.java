package io.happylrd.controller;

import io.happylrd.domain.Comment;
import io.happylrd.domain.Result;
import io.happylrd.repository.CommentRepository;
import io.happylrd.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/comments")
    public Result<List<Comment>> listComment() {
        return ResultUtil.success(commentRepository.findAll());
    }

    @PostMapping(value = "/comments")
    public Result<Comment> insertComment(Comment comment) {
        return ResultUtil.success(commentRepository.save(comment));
    }

    @GetMapping(value = "/comments/{id}")
    public Result<Comment> getComment(@PathVariable("id") Integer id) {
        return ResultUtil.success(commentRepository.findOne(id));
    }

    @PutMapping(value = "/comments/{id}")
    public Result<Comment> updateComment(@PathVariable("id") Integer id, Comment comment) {
        return ResultUtil.success(commentRepository.save(comment));
    }

    @DeleteMapping(value = "/comments/{id}")
    public void removeComment(@PathVariable("id") Integer id) {
        commentRepository.delete(id);
    }
}
