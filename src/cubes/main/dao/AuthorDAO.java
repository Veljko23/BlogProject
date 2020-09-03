package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Author;
import cubes.main.entity.Role;

public interface AuthorDAO {
	
	public List<Author> getListAuthor();
	
	public void saveAuthor(Author author);
	
	public Author getAuthorByUsername(String username);
	
	public void deleteAuthor(String username);
	
	public void enableAuthor(String username);
	
	public List<Role> getRoleList();

}
