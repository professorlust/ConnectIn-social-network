/**
 *
 */
package com.connectin.business.likes.dao;

import com.connectin.business.comments.entity.Comment;
import com.connectin.business.likes.entity.Likes;
import com.connectin.business.likes.repository.LikeRepository;
import com.connectin.business.post.entity.Post;
import com.connectin.business.user.entity.User;
import com.connectin.domain.like.LikeDTO;
import com.connectin.domain.like.LikeType;
import com.connectin.exceptions.ConnectinBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dell
 */
@Repository
@Transactional
public class LikesDaoImpl implements ILikesDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<LikeDTO> getLikesByComments(int commentId) throws ConnectinBaseException {
        List<LikeDTO> likes = new ArrayList<>();
        try {
            likes = (List<LikeDTO>) entityManager
                    .createQuery("select new " + "com.connectin.domain.like.LikeDTO(l.id, l.user.id, "
                            + "l.user.firstName,l.user.lastName,l.user.email,l.createdDate) "
                            + "from likes l where l.comment.id=:commentId and l.type=:likeType")
                    .setParameter("postId", commentId)
                    .setParameter("likeType", LikeType.comment).getResultList();
            return likes;
        } catch (Exception e) {
            throw new ConnectinBaseException("Could not load likes!");

        }
    }

    @Override
    public List<LikeDTO> getLikesbyPost(int postId) throws ConnectinBaseException {
        List<LikeDTO> likes = new ArrayList<>();
        try {
            likes = (List<LikeDTO>) entityManager
                    .createQuery("select new com.connectin.domain.like.LikeDTO(l.id, l.user.id, "
                            + "l.user.firstName,l.user.lastName,l.user.email,l.createdTime, l.type) "
                            + "from likes l where l.postLike.id=:postId and l.type=:likeType")
                    .setParameter("postId", postId)
                    .setParameter("likeType", LikeType.post).getResultList();
            return likes;
        } catch (Exception e) {
            throw new ConnectinBaseException("Could not load likes: " + e.getMessage());

        }
    }

    @Override
    public void like(LikeType type, int id, int userId) throws ConnectinBaseException {
        try {
            Likes like = new Likes();
            if (type == LikeType.comment) {
                Comment comment = new Comment();
                comment.setId(id);
                like.setComment(comment);
            } else {
                Post post = new Post();
                post.setId(id);
                like.setPostLike(post);
            }
            User user = new User();
            user.setId(userId);
            like.setUser(user);
            like.setCreatedTime(new Date());
            like.setType(type);
            entityManager.persist(like);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new ConnectinBaseException("could not like post with id "+ id+" with type "+ type);
        } catch (Exception e) {
            throw new ConnectinBaseException("could not like post with id "+ id+" with type "+ type);
        }

    }
}
