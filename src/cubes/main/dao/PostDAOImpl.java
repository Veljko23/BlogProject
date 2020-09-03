package cubes.main.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Post;
import javafx.geometry.Pos;
@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	@Override
	public List<Post> getListPost() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post", Post.class);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public void savePost(Post post) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(post);
		
	}


	@Transactional
	@Override
	public Post getPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		return post;
	}


	@Transactional
	@Override
	public void deletePost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		session.delete(post);
		
	}


	@Transactional
	@Override
	public void enablePost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		post.setIsEnabled(!post.getIsEnabled());
		
		session.saveOrUpdate(post);
		
	}


	@Transactional
	@Override
	public void makeImportantPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		post.setIsImportant(!post.getIsImportant());
		
		session.saveOrUpdate(post);
	}


	@Transactional
	@Override
	public List<Post> getPostsList(int orderBy) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query;
		
		if (orderBy==1) {
			query = session.createQuery("from Post p order by p.title asc", Post.class);
		}
		else if (orderBy==2) {
			query = session.createQuery("from Post p order by p.author.name asc", Post.class);
		}
		else if(orderBy==3) {
			query = session.createQuery("from Post p where p.enabled=1 order by p.id", Post.class);
		}
		else if(orderBy==4) {
			query = session.createQuery("from Post p where p.enabled=0 order by p.id", Post.class);
		}
		else if(orderBy==5) {
			query = session.createQuery("from Post p order by p.id", Post.class);
		}
		else if(orderBy==6) {
			query = session.createQuery("from Post p order by p.numViews desc", Post.class);
		}
		else if(orderBy==7) {
			query = session.createQuery("from Post p order by p.numComments desc", Post.class);
		}
		else {
			query = session.createQuery("from Post p order by p.id desc", Post.class);
		}
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getLastThreePost() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p order by p.id desc");
		query.setMaxResults(3);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getLatestPost() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p order by p.id desc");
		query.setMaxResults(12);
		
		List<Post> list = query.getResultList();
		
		
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getThreeImportantPostForMainPage() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p where p.important=1 order by p.id desc");
		query.setMaxResults(3);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getPostListByCategory(int id) {
		
		Session session = sessionFactory.getCurrentSession();
				
		Query<Post> query = session.createQuery("from Post p where p.category.id=:id");
		query.setParameter("id", id);
		
		List<Post> list = query.getResultList();
		
//		if(list.size()==0)
//			list = new ArrayList<Post>();
		
		System.out.println(list.toString());
		return list;
	}


	@Transactional
	@Override
	public Post getPostWithTag(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		Hibernate.initialize(post.getTags());
		
		return post;
	}


	@Transactional
	@Override
	public List<Post> getPreviousPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Post post = new Post();
		
		List<Post> lista = new ArrayList<Post>();
		
		Query<Post> query = session.createQuery("from Post where ( id = IFNULL((select min(id) from Post where id > :id),0) or  id = IFNULL((select max(id) from Post where id < :id),0))", Post.class);
		query.setParameter("id", id);
		List<Post> list = query.getResultList();
		
//		post.setId(id-1);
//		if(post.getAuthor()==null && post.getText()==null)
//			list.remove(list.indexOf(post));
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getPostListByTag(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select distinct p from Post p join p.tags t where t.id IN (:id) order by p.id desc");
		query.setParameter("id", id);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getPostListByAuthor(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("select p from Post p where p.author.username=:username");
		query.setParameter("username", username);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getSearchPost(String search) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p where p.author.name LIKE '%"+search+"%' OR p.text LIKE '%"+search+"%' OR p.title LIKE '%"+search+"%' OR p.category.name LIKE :search ");
		query.setParameter("search", search);
		
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getListPostForBlogPage() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post order by id desc", Post.class);
		
		List<Post> list = query.getResultList();
		
		return list;
	}


	@Transactional
	@Override
	public List<Post> getPostsListForBlogPage(int orderBy) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query;
		
		if (orderBy==1) {
			query = session.createQuery("from Post p order by p.numViews desc", Post.class);
		}
		else if (orderBy==2) {
			query = session.createQuery("from Post p order by p.numComments desc", Post.class);
		}
		else {
			query = session.createQuery("from Post p order by p.id desc", Post.class);
		}
		
		List<Post> list = query.getResultList();
		
		return list;
	}
	
	

}
