package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Author;
import cubes.main.entity.Role;

@Repository
public class AuthorDAOImpl implements AuthorDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Author> getListAuthor() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Author> query = session.createQuery("from users", Author.class);
		
		List<Author> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public void saveAuthor(Author author) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(author);
		
	}

	@Transactional
	@Override
	public Author getAuthorByUsername(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Author author = session.get(Author.class, username);
		
		return author;
	}

	@Transactional
	@Override
	public void deleteAuthor(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Author author = session.get(Author.class, username);
		
		session.delete(author);
		
	}

	@Transactional
	@Override
	public void enableAuthor(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Author author = session.get(Author.class, username);
		
		author.setEnabled(!author.getEnabled());
		
		session.saveOrUpdate(author);
		
	}

	@Transactional
	@Override
	public List<Role> getRoleList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Role> query = session.createQuery("from roles", Role.class);
		
		List<Role> list = query.getResultList();
		
		return list;
	}

}
