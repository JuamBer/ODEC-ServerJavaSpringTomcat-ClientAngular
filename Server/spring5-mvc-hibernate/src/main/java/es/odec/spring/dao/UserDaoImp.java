package es.odec.spring.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.odec.spring.model.User;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		System.out.println("Save");
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<User> list() {

		System.out.println("List");
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.getResultList();
	}

	@Override
	public void deleteUser(Long id) {

		System.out.println("Delete");
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id="+id);
		query.executeUpdate();
	}

	@Override
	public void updateUser(Long id, String name, String email) {

		System.out.println("Update");
		sessionFactory.getCurrentSession().createQuery("UPDATE User SET name=:name, email=:email WHERE id=:id")
		.setParameter("name", name)
		.setParameter("email", email)
		.setParameter("id", id)
		.executeUpdate();
	}
}