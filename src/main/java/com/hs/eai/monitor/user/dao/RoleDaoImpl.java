package com.hs.eai.monitor.user.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.eai.monitor.user.model.Role;


@Repository
public class RoleDaoImpl implements RoleDao {

	Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public Role findByName(String name) {
		
		Role role = null;
		logger.debug("Find Role by name, {}",name);
		try{
			
			Query query = getSession().getNamedQuery("Role.findByName");
			query.setParameter("name", name);
			if(query.list().isEmpty()){
				logger.debug("No roles found!");
			}
			else if(query.list().size() == 1){
				role = (Role) query.list().get(0);
				 
			 }else{
				 throw new Exception("Found more than role for role: "+name);
			 }
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return role;
	}

	
}
