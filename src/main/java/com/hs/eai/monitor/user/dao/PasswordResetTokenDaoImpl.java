package com.hs.eai.monitor.user.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.eai.monitor.user.model.PasswordResetToken;
import com.hs.eai.monitor.user.model.User;

@Repository
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao{

	Logger logger = LoggerFactory.getLogger(PasswordResetTokenDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public PasswordResetToken findByToken(String token) {
		
		PasswordResetToken pwToken = null ;
		logger.debug("Find PasswordResetToken By token {} ", token, "..");
		try{
			
			Query query = getSession().getNamedQuery("PasswordResetToken.findByToken");
			query.setParameter("token", token);
			logger.debug("query {} ", query.toString(), "..");
			pwToken =  (PasswordResetToken) query.list().get(0);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return pwToken;
	}

	@Override
	public PasswordResetToken findByUser(User user) {
		PasswordResetToken pwToken = null ;
		logger.debug("Find PasswordResetToken By user {} ", user.getUsername(), "..");
		try{
			
			Query query = getSession().getNamedQuery("PasswordResetToken.findByUser");
			query.setParameter("user", user);
			logger.debug("query {} ", query.toString(), "..");
			pwToken =  (PasswordResetToken) query.list().get(0);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return pwToken;
	}

	@Override
	public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date date) {
		
		Stream<PasswordResetToken> listPwdTokens = null ;
		logger.debug("Find PasswordResetToken By date {} ", date, "..");
		try{
			
			Query query = getSession().getNamedQuery("PasswordResetToken.findAllByExpiryDateLessThan");
			query.setParameter("expiryDate", date);
			logger.debug("query {} ", query.toString(), "..");
			listPwdTokens =   (Stream<PasswordResetToken>) query.list();
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return listPwdTokens;
	}

	@Override
	public void deleteByExpiryDateLessThan(Date date) {
		
		logger.debug("delete PasswordResetToken less than date {} ", date, "..");
		try{
			
			Query query = getSession().getNamedQuery("PasswordResetToken.deleteByExpiryDateLessThan");
			query.setParameter("expiryDate", date);
			logger.debug("query {} ", query.toString(), "..");
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}

	}

	@Override
	public void deleteAllExpiredSince(Date date) {
		logger.debug("delete PasswordResetToken since date {} ", date, "..");
		try{
			
			Query query = getSession().getNamedQuery("PasswordResetToken.deleteAllExpiredSince");
			query.setParameter("expiryDate", date);
			logger.debug("query {} ", query.toString(), "..");
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		
	}

	@Override
	public void save(PasswordResetToken myToken) {
		
		logger.debug("Save PasswordResetToken ..");
		try{
			getSession().save(myToken);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		
		
	}


}
