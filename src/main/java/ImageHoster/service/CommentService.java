package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(){


        return commentRepository.getAllComments();

    }

    public List<Comment> getImageComments(Integer imageId) {
        List<Comment> results = commentRepository.getImageComments(imageId);
        return results;
    }



    public Comment createComment (Comment newComment){
        newComment.setCreatedDate(new Date());
        return commentRepository.createComment(newComment);

    }

}
