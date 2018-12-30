package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {


    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public List<Comment> getAllComments() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c", Comment.class);
            List<Comment> comments = query.getResultList();
            return comments;
        }
        catch (NoResultException nre) {
            return null;
        }

    }

    public Comment createComment(Comment newComment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return  newComment;
    }
    //this method gets the comments of the an image using the imageId
    public List<Comment> getImageComments(Integer imageId) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i", Comment.class);
            //commentList will have all the comments of all the images
            List<Comment> commentList = query.getResultList();
            List<Comment> resultList = new ArrayList<Comment>();
            //this for loop selects all the comments whose imageId equals the comment's imageid
            for(Comment comment : commentList){
                if(comment.getImage().getId() == imageId){
                 //add the comment to the list
                    resultList.add(comment);
                }
            }
            // returns the resultList
            return resultList;
        } catch (NoResultException nre) {
            return null;
        }
    }
    }


