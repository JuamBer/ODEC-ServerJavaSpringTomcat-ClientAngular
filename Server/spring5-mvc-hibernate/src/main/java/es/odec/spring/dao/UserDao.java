package es.odec.spring.dao;


import java.util.List;

import es.odec.spring.model.User;

public interface UserDao {
	void save(User user);

	List<User> list();
	
	void deleteUser(Long id);

	void updateUser(Long id, String name, String email);
}
