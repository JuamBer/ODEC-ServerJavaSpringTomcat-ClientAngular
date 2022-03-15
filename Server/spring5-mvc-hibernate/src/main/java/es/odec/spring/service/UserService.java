package es.odec.spring.service;

import java.util.List;
import es.odec.spring.model.User;

public interface UserService {
	void save(User user);

	List<User> list();
	
	void deleteUser(Long id);

	void updateUser(Long id, String name, String email);
}
