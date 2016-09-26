package com.hs.eai.monitor.user.dao;

import java.util.Date;
import java.util.stream.Stream;

import com.hs.eai.monitor.user.model.PasswordResetToken;
import com.hs.eai.monitor.user.model.User;

public interface PasswordResetTokenDao {

	PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

   
   // @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

	void save(PasswordResetToken myToken);

    
}
