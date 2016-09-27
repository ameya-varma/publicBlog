package com.userinformation.Controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userinformation.common.UserInfo;
import com.userinformation.entities.UserInformationEntity;

@RestController
public class UserInformationController {
	
	@RequestMapping(value="/userDetails", method=RequestMethod.POST)
	public ResponseEntity<UserInfo> getUserDetails(HttpEntity<UserInfo> requestEntity) throws UnsupportedEncodingException
	{
		UserInfo userInfo = new UserInfo();
		userInfo = requestEntity.getBody();
		
		UserInformationEntity userEntity = new UserInformationEntity();
		
		userInfo = userEntity.updateUserInformation(userInfo);
		
		//userInfo.setGoogleUserEmail("test_change_email");
		//userInfo.setGoogleUserId("test_change_id");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_XML);
		
		return new ResponseEntity<UserInfo>(userInfo, headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getUser() throws UnsupportedEncodingException
	{
		//System.setProperty("proxyHost", "http://127.0.0.1:8888"); 
		//System.setProperty("proxyPort", "8888");
		return "ACCEPTED";
	}
	
}
