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


    //This method gets all the comments of the image with imageId as the parameter and calls the commentRepository
    public List<Comment> getImageComments(Integer imageId) {
        List<Comment> results = commentRepository.getImageComments(imageId);
        return results;
    }


     //This method creates a new  comment using commentRepository
    public Comment createComment (Comment newComment){
        newComment.setCreatedDate(new Date());
        return commentRepository.createComment(newComment);

    }

}
