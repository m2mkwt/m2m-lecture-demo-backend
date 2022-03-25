package kr.co.m2m.framework.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class KeyGenUtility
	implements InitializingBean {
	
	private static final byte[]	CONFIRM_PREFIX	= "m2m-common-confirm-prefix.".getBytes();
	
	private class NewKeyCounter {
		private int cnt	= 0;
		
		private int getNext() {
			return this.cnt++;
		}
	}
	
	private static final long	TM_DEVIDE					= 1000 * 60 * 60 * 24;
	
	private Map<String, Integer>	categoryIndexMap;

	private byte[]					keyBaseMacAddr;

	private byte[]					keyBaseProcessUID;

	private byte[]					keyBaseTimestamp;

	private List<NewKeyCounter>		keyCntList;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		SecureRandom	srnd;
		String			hostName;
		
		srnd		= new SecureRandom();
		hostName	= null;
		try {
			InetAddress ip;
			NetworkInterface network;
			
			ip				= InetAddress.getLocalHost();
			try {
				hostName	= ManagementFactory.getRuntimeMXBean().getName();
			} catch (NullPointerException ignore) {}
			network 				= NetworkInterface.getByInetAddress(ip);
			this.keyBaseMacAddr 	= network.getHardwareAddress();
		} catch (IOException ignore) {
			if (hostName == null) {
				byte[]	rndName;
				
				rndName		= new byte[8];
				srnd.nextBytes(rndName);
				hostName	= DatatypeConverter.printBase64Binary(rndName);
			}
			if (this.keyBaseMacAddr == null) {
				this.keyBaseMacAddr	=  new byte[6];
				srnd.nextBytes(this.keyBaseMacAddr);
			}
		}
		this.keyBaseProcessUID	= ManagementFactory.getRuntimeMXBean().getName().getBytes();
	    this.keyBaseTimestamp	= String.valueOf(System.currentTimeMillis() % 100000000).getBytes();
	    this.categoryIndexMap	= new HashMap<>();
	    this.keyCntList			= new ArrayList<>();
	}
	
	public String getNewKey(String category) {
		Integer			categoryIndex;
		int				newCnt;
		MessageDigest	sha256Md;
		
		synchronized (this.categoryIndexMap) { 
			categoryIndex	= this.categoryIndexMap.get(category);
			if (categoryIndex == null) {
				categoryIndex	= this.categoryIndexMap.size();
				this.categoryIndexMap.put(category, categoryIndex);
				this.keyCntList.add(new NewKeyCounter());
			}
		}
		newCnt	= this.keyCntList.get(categoryIndex).getNext();
		
		try {
			sha256Md	= MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ignore) {
			throw new RuntimeException(ignore);
		}
		sha256Md.update(this.keyBaseMacAddr);
		sha256Md.update(this.keyBaseProcessUID);
		sha256Md.update(this.keyBaseTimestamp);
		sha256Md.update(String.valueOf(System.currentTimeMillis() / TM_DEVIDE).getBytes());
		sha256Md.update(category.getBytes());
		sha256Md.update(String.valueOf(newCnt).getBytes());
		return DatatypeConverter.printHexBinary(sha256Md.digest());
	}
	
	public static String getConfirmKey(String... srcTokens) {
		MessageDigest	sha512Md;
		
		if (srcTokens == null || srcTokens.length == 0) {
			return null;
		}
		
		try {
			sha512Md	= MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException ignore) {
			throw new RuntimeException(ignore);
		}
		sha512Md.update(CONFIRM_PREFIX);
		for (int i=0; i<srcTokens.length; i++) {
			byte[]	srcBuf;
			
			srcBuf	= srcTokens[i].getBytes();
			for (int j=0; j<srcBuf.length; j++) {
				srcBuf[j]	^= CONFIRM_PREFIX[j % CONFIRM_PREFIX.length];
			}
			sha512Md.update(srcBuf);
		}
		return DatatypeConverter.printBase64Binary(sha512Md.digest());
	}	
}
