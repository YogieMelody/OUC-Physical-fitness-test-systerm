package com.sports.util;

import java.io.UnsupportedEncodingException;

public class ChangeISO {
	public static String parseGBK(String sIn) {
		if ((sIn == null) || (sIn.equals(""))) {
			return sIn;
		}
		try {
			return new String(sIn.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException usex) {
		}
		return sIn;
	}

	public static String parseUTF8(String sIn) {
		if ((sIn == null) || (sIn.equals(""))) {
			return sIn;
		}
		try {
			return new String(sIn.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException usex) {
		}
		return sIn;
	}
}
