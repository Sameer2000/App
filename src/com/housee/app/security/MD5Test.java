package com.housee.app.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Test {

	/*public static void main(String[] args) {
		System.out.println(MD5Test.MD5HashingPassword("p")); 
	}*/
	
	public static String MD5HashingPassword(String password)
	{
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			System.out.println("Digest(in hex format):: " + sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
