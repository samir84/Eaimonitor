package com.hs.eai.monitor.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hs.eai.monitor.user.model.User;
import com.hs.eai.monitor.user.util.UserNotFoundException;


@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return (User) getSession().get(User.class, id);
	}
	@Override
	public User findByUsername(String username) throws Exception {
		
		logger.debug("Find USER By username {} ", username, "..");
		Query query = getSession().getNamedQuery("User.findByUsername");
		query.setParameter("username", username);
		logger.debug("query {} ", query.toString(), "..");
		return (User) query.list().get(0);
	}

	@Override
	public User findByEmail(String email) {
		
		logger.debug("Find USER By email {} ", email, "..");
		User user = null;
		try{
			Query query = getSession().getNamedQuery("User.findByEmail");
			query.setParameter("email", email);
			logger.debug("query {} ", query.toString(), "..");
			user = (User) query.list().get(0);
			if (user == null) {
				throw new UserNotFoundException();
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		List<User> users = null;
		try{
			Query query = getSession().getNamedQuery("User.findByRolename");
			query.setParameter("roles", role);
			users = query.list();
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return users;
	}
  
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) getSession().createCriteria(User.class);
	}

	@Override
	public void save(User user) {
		getSession().save(user);
		
	}

	@Override
	public void update(User user) {
		getSession().saveOrUpdate(user);
		
	}

	@Override
	public void delete(Integer id) {
		User user = null ;
		try{
			user = findById(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		getSession().delete(user);
		
	}


}
