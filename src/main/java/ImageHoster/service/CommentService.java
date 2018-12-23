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

    public List<Comment> getAllImageComments(Image imageid){


        return commentRepository.getComments_of_Image(imageid);

    }
    public void createComment (Comment newComment){
        newComment.setCreatedDate(new Date());
        commentRepository.createComment(newComment);
        System.out.println("Comments"+newComment);
    }
    public Comment getComment(Integer Id) {
        return commentRepository.getComment(Id);
    }
}
