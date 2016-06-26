package com.sports.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class GetHash {
	public static String getMD5(String str){
		String hashedStr=null;
		try{
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte ss[]=md.digest();
			hashedStr=bytes2String(ss);
		}catch(NoSuchAlgorithmException e){
			
		}
		return hashedStr;
	}
	
	private static String bytes2String(byte[] aa){
		String hash="";
		for(int i=0;i<aa.length;i++){
			int temp;
			if(aa[i]<0){
				temp=256+aa[i];
			}else{
				temp=aa[i];
			}
			if(temp<16){
				hash+="0";
			}
			hash+=Integer.toString(temp,16);
		}
		hash=hash.toUpperCase();
		return hash;
	}
	
//	public static void main(String[] args) {
//		String a="123456";
//		String b="1234567";
//		String c="fast";
//		String hashA=GetHash.getMD5(a);
//		String hashB=GetHash.getMD5(b);
//		String hashC=GetHash.getMD5(c);
//		System.out.println(hashA);
//		System.out.println(hashB);
//		System.out.println(hashC);
//		//E10ADC3949BA59ABBE56E057F20F883E
//		//FCEA920F7412B5DA7BE0CF42B8C93759
//		//31D4541B8E926A24F0C9B835B68CFDF3
//	}
}
