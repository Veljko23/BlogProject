package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Contact;

public interface ContactDAO {
	
	public List<Contact> getListContact();
	
	public void saveContact(Contact contact);
	
	public Contact getContact(int id);
	
	public void deleteContact(int id);
		
	public long getUnreadMessageCount();

}
