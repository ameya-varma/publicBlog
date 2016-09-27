package com.userinformation.entities;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.userinformation.common.UserInfo;

public class UserInformationEntity {
	
	private DatastoreService datastore  = DatastoreServiceFactory.getDatastoreService( );
	private TransactionOptions options = TransactionOptions.Builder.withXG(true);
	
	
	public UserInformationEntity()
	{
		
	}
	
	public UserInfo updateUserInformation(UserInfo userInfo)
	{
		Entity userInfoEntity = new Entity("UserInfo", userInfo.getGoogleUserId( ) );
		Transaction txn = datastore.beginTransaction(options);

		Key userIdKey = KeyFactory.createKey("UserInfo", userInfo.getGoogleUserId( ) );
		
		try {
			userInfoEntity = datastore.get(txn, userIdKey);
		} 
		catch (EntityNotFoundException e) 
		{
			userInfoEntity.setProperty("googleUserEmail", userInfo.getGoogleUserEmail( ) );
			try{
				datastore.put(txn, userInfoEntity);
				txn.commit();
			}
			catch(Exception putException)
			{
				userInfoEntity = null;
			}
			
		}
				if(userInfoEntity == null)
		{
			return null;
		}
		else
		{
			userInfo.setGoogleUserEmail((String) userInfoEntity.getProperty("googleUserEmail"));
			return userInfo;
		}
		
		
	}

}
