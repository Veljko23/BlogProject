package cubes.main.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;
import cubes.main.entity.Post;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Category> getCategoryList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Category> query = session.createQuery("from Category", Category.class);
		
		List<Category> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(category);
		
	}

	@Transactional
	@Override
	public Category getCategory(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Category category = session.get(Category.class, id);
		
		return category;
	}

	@Transactional
	@Override
	public void deleteCategory(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Category where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public List<Category> getCategoriesById(List<Integer> ids) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Category> query = session.createQuery("select c from Category c where c.id IN (:ids)", Category.class);
		
		query.setParameter("ids", ids);
		
		List<Category> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public List<Category> getCategoriesForFilter() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Category> query = session.createQuery("from Category");
		
		List<Category> list = query.getResultList();
		
		for(Category cat: list) {
			
			Query queryCount = session.createQuery("select count(post.id) from Post post where post.category.id=:id");
			queryCount.setParameter("id", cat.getId());
			cat.setCount((long) queryCount.uniqueResult());
		}
		
		return list;
	}

	@Transactional
	@Override
	public List<Category> getCategoryById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Category> query = session.createQuery("select c from Category c where c.id=:id");
		query.setParameter("id", id);
		
		List<Category> list = query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public List<Category> getPriorityCategories() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Category> query = session.createQuery("select c from Category c order by c.counter desc");
		query.setMaxResults(4);
		
		List<Category> list = query.getResultList();
				
		return list;
		
	}

}
