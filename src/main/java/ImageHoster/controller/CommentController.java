package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    public CommentController() {
    }


    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable(value="imageId") Integer imageId,@RequestParam(value="comment") String text, Comment newComment, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        Image image = imageService.getImage(imageId);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());
        newComment.setText(text);


        commentService.createComment( newComment);

        return "redirect:/images/" + image.getId() + "/" + image.getTitle();
    }





}
