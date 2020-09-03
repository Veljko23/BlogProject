package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Post;

public interface PostDAO {
	
	List<Post> getListPost();
	
	public void savePost(Post post);
	
	public Post getPost(int id);
	
	public void deletePost(int id);
	
	public void enablePost(int id);
	
	public void makeImportantPost(int id);
	
	public List<Post> getPostsList(int orderBy);
	
	public List<Post> getLastThreePost();
	
	public List<Post> getLatestPost();
	
	public List<Post> getThreeImportantPostForMainPage();
	
	public List<Post> getPostListByCategory(int id);
	
	public Post getPostWithTag(int id);
	
	public List<Post> getPreviousPost(int id);
	
	public List<Post> getPostListByTag(int id);
	
	public List<Post> getPostListByAuthor(String username);
	
	public List<Post> getSearchPost(String txt);
	
	public List<Post> getListPostForBlogPage();
	
	public List<Post> getPostsListForBlogPage(int orderBy);

}
