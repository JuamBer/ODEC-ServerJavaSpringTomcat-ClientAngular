package es.odec.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.odec.spring.model.User;
import es.odec.spring.dao.UserDao;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Transactional(readOnly = true)
	public List<User> list() {
		return userDao.list();
	}

	@Transactional
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

	@Transactional
	public void updateUser(Long id, String name, String email) {
		userDao.updateUser(id,name,email);
		
	}
}
