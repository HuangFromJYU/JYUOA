package edu.jyu.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.domain.User;

@Service
public class TestService {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void saveTwoUsers() {
		Session session = sessionFactory.getCurrentSession();

		session.save(new User());
		// int a = 1 / 0; // 这行会抛异常
		session.save(new User());
	}

	@Transactional
	public void save25Users() {
		Session session = sessionFactory.getCurrentSession();

		for (int i = 0; i < 25; i++) {
			User user = new User();
			user.setName("test_" + (char) (65 + i));
			session.save(user);
		}
	}
}
