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

    public List<Comment> getImageComments(Integer imageId) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i", Comment.class);
            List<Comment> resultList = query.getResultList();
            List<Comment> returnList = new ArrayList<Comment>();
            for(Comment comment : resultList){
                if(comment.getImage().getId() == imageId){
                    returnList.add(comment);
                }
            }
            return returnList;
        } catch (NoResultException nre) {
            return null;
        }
    }
    }


