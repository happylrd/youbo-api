package io.happylrd.youbo.controller.deprecated;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.deprecated.Comment;
import io.happylrd.youbo.repository.deprecated.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/comments")
    public ServerResponse<List<Comment>> list() {
        return ServerResponse.createBySuccess(commentRepository.findAll());
    }

    @PostMapping(value = "/comments")
    public ServerResponse<Comment> save(Comment comment) {
        return ServerResponse.createBySuccess(commentRepository.save(comment));
    }

    @GetMapping(value = "/comments/{id}")
    public ServerResponse<Comment> get(@PathVariable("id") Long id) {
        return ServerResponse.createBySuccess(commentRepository.findOne(id));
    }

    @PutMapping(value = "/comments/{id}")
    public ServerResponse<Comment> update(@PathVariable("id") Long id,
                                          @RequestBody Comment comment) {
        return ServerResponse.createBySuccess(commentRepository.save(comment));
    }

    @DeleteMapping(value = "/comments/{id}")
    public void remove(@PathVariable("id") Long id) {
        commentRepository.delete(id);
    }
}
