package cubes.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;
import cubes.main.entity.Post;

@Controller
public class FrontController {

	@Autowired
	ContactDAO contactDAO;
	
	@Autowired
	SliderDAO sliderDAO;
	
	@Autowired
	PostDAO postDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	TagDAO tagDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	AuthorDAO authorDAO;
	
	
	@RequestMapping({"/", "/index-page"})
	public String getIndexPage(Model model) {
		
		model.addAttribute("sliderMainPage", sliderDAO.getSlidesForMainPage());
		
		model.addAttribute("lastThreeImportantPost", postDAO.getThreeImportantPostForMainPage());
		
		model.addAttribute("latestPost", postDAO.getLatestPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());
		
		return "front/index-page";
	}
	
	@RequestMapping("/contact-page")
	public String getContactPage(Model model) {
		
		model.addAttribute("contact", new Contact());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());
		

		return "front/contact-page";
	}
	
	@RequestMapping("/blog-page")
	public String getBlogPage(Model model) {
		
		model.addAttribute("postList", postDAO.getListPostForBlogPage());
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		return "front/blog-page";
	}
	
	@RequestMapping("/blog-post-page")
	public String getBlogPostPage(@RequestParam int id,Model model) {
		
		Post post = postDAO.getPostWithTag(id);
		
		
		List<Post> list = postDAO.getPreviousPost(id);
		
//		for(Post p: list) {
//			if(p.getAuthor() == null && p.getCategory() == null && p.getText() == null)
//				list.remove(p);
//		}
		System.out.println(list.toString());
		
		model.addAttribute("post", post);
		
		model.addAttribute("post", postDAO.getPostWithTag(id));
		
		model.addAttribute("postList", postDAO.getListPost());
		model.addAttribute("prevNext", list);
		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		model.addAttribute("comment", new Comment());
		
		model.addAttribute("commentList", commentDAO.getCommentsForPost(id));
		
		if(post.getNumViews()==0) {
			post.setNumViews(1);
			System.out.println("prvi pregled");
		}
		else {
			post.setNumViews(post.getNumViews()+1);
			System.out.println("drugi pregled");
		}
		model.addAttribute("commentCount", commentDAO.getCommentsCounter(id));
		post.setNumComments((int) commentDAO.getCommentsCounter(id));
		
		postDAO.savePost(post);
		
		return "front/blog-post-page";
	}
	
	@RequestMapping("/post-comment")
	public String getPostComment(@RequestParam(name = "idPost") int id, @ModelAttribute Comment comment, Model model) {
		
		Post post = postDAO.getPostWithTag(id);
		
		comment.setPost(post);
		model.addAttribute("id", id);
		model.addAttribute("post", post);
		model.addAttribute("id", post.getId());
		model.addAttribute("title", post.getTitle());
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm");  
	    Date date = new Date();  
	    comment.setDate(formatter.format(date));
	    comment.setIsEnabled(true);
	    postDAO.savePost(post);
		commentDAO.saveComment(comment);
		
		return "redirect:blog-post-page?id={id}&title={title}";
	}
	
	@RequestMapping("/message-save")
	public String getSaveMessage(@Valid @ModelAttribute Contact contact, BindingResult results, Model model) {
		
		if(results.hasErrors()) {
			model.addAttribute("lastThreePost", postDAO.getLastThreePost());
			model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());
			
			return "front/contact-page";
		}
		
		contactDAO.saveContact(contact);
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());
		
		return "redirect:index-page";
	}
	
	//==================================================================
	
	@RequestMapping("/blog-category-page")
	public String getBlogCategoryP(@RequestParam int id, Model model) {
		
		model.addAttribute("postList", postDAO.getPostListByCategory(id));
		model.addAttribute("category", categoryDAO.getCategoryById(id));
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		return "front/blog-category-page";
	}
	
	@RequestMapping("/blog-tag-page")
	public String getBlogTagP(@RequestParam int id, Model model) {
		
		model.addAttribute("postList", postDAO.getPostListByTag(id));
		model.addAttribute("category", categoryDAO.getCategoryById(id));
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tag", tagDAO.getTagById(id));
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		return "front/blog-tag-page";
	}
	
	@RequestMapping("/blog-author-page")
	public String getBlogAuthorP(@RequestParam String username,Model model) {
		
		model.addAttribute("author", authorDAO.getAuthorByUsername(username));
		model.addAttribute("postList", postDAO.getPostListByAuthor(username)); //PROBA!!!
		//model.addAttribute("category", categoryDAO.getCategoryById(id));
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		return "front/blog-author-page";
	}
	
	@RequestMapping("/blog-search-page")
	public String getBlogSearchP(Model model, @RequestParam String search) {
		
		model.addAttribute("search", search);
		model.addAttribute("postList", postDAO.getSearchPost(search));
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());

		return "front/blog-search-page";
	}
	
	@RequestMapping("/post-order")
	public String getPostOrderList(@RequestParam int orderBy,Model model) {
		System.out.println("sortirano");
		model.addAttribute("postList", postDAO.getPostsListForBlogPage(orderBy));
		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		model.addAttribute("tagList", tagDAO.getTagListByPost());
		model.addAttribute("lastThreePost", postDAO.getLastThreePost());
		model.addAttribute("priorityCategories", categoryDAO.getPriorityCategories());
		return "front/blog-page";
	}
	
}
