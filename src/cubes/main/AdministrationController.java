package cubes.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Author;
import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;
import cubes.main.entity.Post;
import cubes.main.entity.Role;
import cubes.main.entity.Slider;
import cubes.main.entity.Tag;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ContactDAO contactDAO;
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Autowired
	TagDAO tagDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	PostDAO postDAO;
	
	@Autowired
	SliderDAO sliderDAO;

	@RequestMapping({"/category-list", ""})
	public String getCategoryListPage(Model model) {
		
		List<Category> list = categoryDAO.getCategoryList();
		
		model.addAttribute("categoryList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		
		return "category-list";
	}
	
	@RequestMapping("/category-form")
	public String getCategoryForm(Model model) {
		
		Category category = new Category();
		category.setCounter(0);
		model.addAttribute("category", category);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "category-form";
	}
	
	@RequestMapping("/category-form-update")
	public String getCategoryUpdateForm(@RequestParam int id ,Model model) {
	
		Category category = categoryDAO.getCategory(id);
		
		model.addAttribute("category", category);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "category-form";
	}
	
	@RequestMapping("/category-save")
	public String getSaveCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model, @RequestParam ("file") MultipartFile file) throws IOException {
		System.out.println("ulaz u metodu");
		//String imageName = uploadFile(file);
		System.out.println("Ulaz u metodu!");
		if(result.hasErrors()) {
			System.out.println("Ima greska!");
			return "category-form";
		}
		
		category.setImage("C:\\apache-tomcat-9.0.30\\image" + File.separator + file.getOriginalFilename());
		System.out.println("slika je:" + file.getOriginalFilename());
		System.out.println("Postavljena slika!");
		categoryDAO.saveCategory(category);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "redirect:/administration/category-list";
	}
	
	@RequestMapping("/category-form-delete")
	public String getDeleteCategory(@RequestParam int id, Model model) {
		
		categoryDAO.deleteCategory(id);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "redirect:/administration/category-list";
	}
	
	//=========================================================
	
	@RequestMapping("/message-list")
	public String getMessageList(Model model) {
		
		List<Contact> list = contactDAO.getListContact();
		
		model.addAttribute("messageList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "message-list";
	}
	
	@RequestMapping("/message-seen")
	public String getMarkMessageSeen(@RequestParam int id) {
		
		Contact c = contactDAO.getContact(id);
		
		c.setIsSeen(true);
		
		contactDAO.saveContact(c);
		
		return "redirect:/administration/message-list";
	}
	
	@RequestMapping("/form-message-delete")
	public String deleteMessage(@RequestParam int id) {
		
		contactDAO.deleteContact(id);
		
		return "redirect:/administration/message-list";
	}
	
	//===========================================================
	
	
	@RequestMapping("/tag-list")
	public String getTagList(Model model) {
		
		List<Tag> list = tagDAO.getTagList();
		
		model.addAttribute("tagList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "tag-list";
	}
	
	@RequestMapping("/tag-form")
	public String getTagForm(Model model) {
		
		Tag tag = new Tag();
		
		model.addAttribute("tag", tag);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "tag-form";
	}
	
	
	@RequestMapping("/form-tag-update")
	public String getTagUpdateForm(@RequestParam int id, Model model) {
		
		Tag tag = tagDAO.getTag(id);
		
		model.addAttribute("tag", tag);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "tag-form";
	}
	
	@RequestMapping("/save-tag")
	public String saveTag(@Valid @ModelAttribute Tag tag, BindingResult result, Model model) {
		
		if(result.hasErrors())
			return "tag-form";
		
		tagDAO.saveTag(tag);
		
		return "redirect:/administration/tag-list";
	}
	
	@RequestMapping("/form-tag-delete")
	public String getDeleteTag(@RequestParam int id) {
		
		tagDAO.deleteTag(id);
		
		return "redirect:/administration/tag-list";
	}
	
	//===========================================================
	
	@RequestMapping("/author-list")
	public String getAuthorList(Model model) {
		
		List<Author> list = authorDAO.getListAuthor();
		
		model.addAttribute("authorList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "author-list";
	}
	
	@RequestMapping("/author-form")
	public String getAuthorForm(Model model) {
		
		Author author = new Author();
		
		model.addAttribute("author", author);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "author-form";
	}
	
	@RequestMapping("/form-author-enable")
	public String getEnableAuthor(@RequestParam String username) {
		
		authorDAO.enableAuthor(username);
		
		return "redirect:/administration/author-list";
	}
	
	@RequestMapping("/save-author")
	public String saveAuthor(@Valid @ModelAttribute Author author, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "author-form";
		}
		
		List<Role> list = authorDAO.getRoleList();
		
		String passwordEncoder = new BCryptPasswordEncoder().encode(author.getPassword());
		
		author.setEnabled(true);
		author.setPassword("{bcrypt}" + passwordEncoder);
		
		author.setAuthorities(list);
		
		authorDAO.saveAuthor(author);
		
		return "redirect:/administration/author-list";
	}
	
	@RequestMapping("/form-author-delete")
	public String getDeleteAuthor(@RequestParam String username) {
		
		authorDAO.deleteAuthor(username);
		
		return "redirect:/administration/author-list";
	}
	
	@RequestMapping("/author-edit")
	public String editAuthor(@ModelAttribute Author author, Model model) {
		
		authorDAO.saveAuthor(author);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "redirect:/administration/author-list";
	}
	
	@RequestMapping("/author-edit-profile")
	public String getAuthorEditPage(Principal principal, Model model) {
		
		Author author = authorDAO.getAuthorByUsername(principal.getName());
		
		model.addAttribute("author", author);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "author-edit-profile";
	}
	
	@RequestMapping("/author-change-password")
	public String getAuthorChangePassword(Principal principal, Model model) {
		
		model.addAttribute("changePassword", new ChangePassword()); 
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "author-change-password";
	}
	
	@RequestMapping("/author-change-password-action")
	public String getAuthorChangePasswordAction(@ModelAttribute ChangePassword changePassword, Principal principal, Model model) {
		
		if(changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {
			Author author = authorDAO.getAuthorByUsername(principal.getName());
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if(encoder.matches(changePassword.getOldPassword(), author.getPassword().replace("{bcrypt}", ""))) {
				author.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));
				
				authorDAO.saveAuthor(author);

			}
			else {
				//nije unet dobar stari password
				if(!encoder.matches(changePassword.getOldPassword(), author.getPassword().replace("{bcrypt}", "")))
					return "author-change-password";
			}
		}
		else {
			//ne poklapaju se passwordi
			if(!changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword()))
			return "author-change-password";
		}
	
		return "redirect:/administration/author-list";
		
	}
	
	//=====================================================================
	
	
	@RequestMapping("/comment-list")
	public String getCommentList(Model model) {
		
		List<Comment> list = commentDAO.getListComment();
		
		model.addAttribute("commentList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "comment-list";
	}
	
	@RequestMapping("/form-comment-delete")
	public String getDeleteComment(@RequestParam int id) {
		
		commentDAO.deleteComment(id);
		
		return "redirect:/administration/comment-list";
	}
	
	
	@RequestMapping("/form-comment-enable")
	public String getEnableComment(@RequestParam int id) {
		
		commentDAO.enableComment(id);
		
		return "redirect:/administration/comment-list";
	}
	
	
	//=====================================================================
	
	@RequestMapping("/post-list")
	public String getPostList(Model model) {
		
		List<Post> list = postDAO.getListPost();
		
		model.addAttribute("postList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		return "post-list";
	}
	
	@RequestMapping("/post-form")
	public String getPostForm(Model model) {
		
		Post post = new Post();
		
		List<Author> authorList = authorDAO.getListAuthor();
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("authorList", authorList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		
		model.addAttribute("post", post);
				
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());

		return "post-form";
	}
	
	@RequestMapping("/form-post-update")
	public String getUpdatePostForm(@RequestParam int id, Model model) {
		
		Post post = postDAO.getPost(id);
		
		List<Author> authorList = authorDAO.getListAuthor();
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("authorList", authorList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		
		
		model.addAttribute("post", post);
		post.setNumViews(post.getNumViews());
		//post.setDate(post.getDate());
		return "post-form";
	}
	
	@RequestMapping("/form-post-delete")
	public String getDeletePost(@RequestParam int id) {
		
		postDAO.deletePost(id);
		
		return "redirect:/administration/post-list";
	}
	
	
	@RequestMapping("/post-save")
	public String getSavePost(@Valid @ModelAttribute Post post, BindingResult result, Model model, Principal principal) {
		System.out.println("Ulaz u save");
		if(result.hasErrors()) {
			
			List<Category> categoryList = categoryDAO.getCategoryList();
			List<Tag> tagList = tagDAO.getTagList();
			List<Author> authorList = authorDAO.getListAuthor();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("tagList", tagList);
			model.addAttribute("authorList", authorList);
			
			
			return "post-form";
		}
		System.out.println("prolazi za sad");
		Author author = authorDAO.getAuthorByUsername(principal.getName());
		Category category = categoryDAO.getCategory(post.getCategory().getId());
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for(Tag tag: post.getTags()) {
			ids.add(Integer.parseInt(tag.getName()));
		}
		List<Tag> tags = tagDAO.getTagsById(ids);

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm");  
	    Date date = new Date();  
	    
		post.setAuthor(author);
		
		System.out.println("kategorije setovane");
		post.setCategory(category);
		post.setTags(tags);
		post.setIsEnabled(true);
		post.setIsImportant(true);
		post.setDate(formatter.format(date));
		post.setNumViews(post.getNumViews());
		category.setCounter(category.getCounter()+1);
		categoryDAO.saveCategory(category);
		postDAO.savePost(post);
		
		return "redirect: post-list";
	}
	
	@RequestMapping("/form-post-enable")
	public String getEnablePost(@RequestParam int id) {
		
		postDAO.enablePost(id);
		
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/form-post-important")
	public String getMakeImportantPost(@RequestParam int id) {
		
		postDAO.makeImportantPost(id);
		
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/post-order")
	public String getPostOrderList(@RequestParam int orderBy,Model model) {
		System.out.println("sortirano");
		model.addAttribute("postList", postDAO.getPostsList(orderBy));
		
		return "post-list";
	}
	
	
	@RequestMapping("/post-search")
	public String searchPost(@RequestParam String search, Model model) {
		
		model.addAttribute("search", search);
		
		model.addAttribute("postList", postDAO.getSearchPost(search));
		
		
		
		return "post-search";
	}
	
	//=====================================================================
	
	@RequestMapping("/slider-list")
	public String getSliderList(Model model) {
		
		List<Slider> list = sliderDAO.getSliderList();
		
		model.addAttribute("sliderList", list);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());

		return "slider-list";
	}
	
	@RequestMapping("/slider-form")
	public String getSliderForm(Model model) {
		
		Slider slider = new Slider();

		model.addAttribute("slider", slider);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());

		return "slider-form";
	}
	
	@RequestMapping("/slider-form-update")
	public String getUpdateSLiderForm(@RequestParam int id, Model model) {
		
		Slider slider = sliderDAO.getSlider(id);
		
		model.addAttribute("slider", slider);
		model.addAttribute("messageCount", contactDAO.getUnreadMessageCount());
		
		return "slider-form";
	}
	
	@RequestMapping("/save-slider")
	public String saveSlider(@Valid @ModelAttribute Slider slider, BindingResult result ,Model model) {
	
		if(result.hasErrors()) {
			return "slider-form";
		}
		
		if(slider.getLink().contains("http://")) {
			slider.setLink("" + slider.getLink());
		}
		else {
			slider.setLink("http://" + slider.getLink());
		}
		
		slider.setIsEnabled(true);
		sliderDAO.saveSlider(slider);
		
		return "redirect:/administration/slider-list";
	}
	
	@RequestMapping("/slider-form-delete")
	public String deleteSlider(@RequestParam int id) {
		
		sliderDAO.deleteSlider(id);
		
		return "redirect:/administration/slider-list";
	}
	
	
	@RequestMapping("slider-form-enable")
	public String getEnableSlider(@RequestParam int id) {
		
		sliderDAO.setSliderEnabled(id);
		
		return "redirect:/administration/slider-list";
	}
	
	//======================================================================
	
	
	public String uploadFile(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "image");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				String name = String.valueOf(new Date().getTime()) + ".jpg";
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				
			} catch (Exception e) {
				
			}
		} else {
			
		}
		return null;
	}
	
	
}
