package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Comment> getListComment() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Comment> query = session.createQuery("from Comment", Comment.class);
		
		List<Comment> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public void saveComment(Comment comment) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(comment);
		
	}

	@Transactional
	@Override
	public Comment getComment(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Comment comment = session.get(Comment.class, id);
		
		return comment;
	}

	@Transactional
	@Override
	public void deleteComment(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Comment where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

	@Transactional
	@Override
	public long getNumComment() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("select count(*) from Comment");
		
		return (long) query.uniqueResult();
		
	}

	@Transactional
	@Override
	public void enableComment(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Comment comment = session.get(Comment.class, id);
		
		comment.setIsEnabled(!comment.getIsEnabled());
		
		session.saveOrUpdate(comment);
		
	}

	@Transactional
	@Override
	public List<Comment> getEnabledComments() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Comment> query = session.createQuery("select c from Comment c where c.enabled=1");
		
		List<Comment> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public List<Comment> getCommentsForPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Comment> query = session.createQuery("select c from Comment c where c.post.id=:id AND c.enabled=1");
		query.setParameter("id", id);
		
		List<Comment> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public long getCommentsCounter(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("select count(*) from Comment c where c.post.id=:id");
		query.setParameter("id", id);
		
		
		return (long) query.uniqueResult();
	}

}
