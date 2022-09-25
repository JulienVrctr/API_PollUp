package com.pollup.api.controller;

import com.pollup.api.model.Comment;
import com.pollup.api.model.CommentPostDto;
import com.pollup.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * Read - Get all comments
     * @return - An Iterable object of Comment full filled
     */
    @GetMapping("/comments")
    public Iterable<Comment> getComments() {
        return commentService.getComments();
    }

    /**
     * Read - Get one comment
     * @param idcomments The id of the comment
     * @return An Comment object full filled
     */
    @GetMapping("/comment/{idcomments}")
    public Comment getComment(@PathVariable("idcomments") final Long idcomments) {
        Optional<Comment> comment = commentService.getComment(idcomments);
        if(comment.isPresent()) {
            return comment.get();
        } else {
            return null;
        }
    }

    @PostMapping("add_comment")
    public ResponseEntity<String> postComment(@RequestBody CommentPostDto commentPostDto) {

        commentService.saveComment(commentPostDto);

        return ResponseEntity.ok("");
    }

    /**
     * Delete - Delete a comment
     * @param idcomments - The id of the comment to delete
     */
    @DeleteMapping("/delete_comment/{idcomments}")
    public void deleteComment(@PathVariable("idcomments") final Long idcomments) {
        commentService.deleteComment(idcomments);
    }
}
