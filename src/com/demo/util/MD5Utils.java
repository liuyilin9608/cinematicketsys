package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class MD5Utils {
	
	/**
	 * 获取经过MD5加密后的字符串
	 * @param plainText 要加密的字符串
	 * @param iterations 迭代加密的次数，0表示不加密，1表示md5(plainText), 2表示md5(md5(plainText))...
	 * @return 经过MD5算法加密的字符串形式的32位16进制,如果参数表示的字符串为空，返回null
	 */
	public static String getMD5Code(String plainText, int iterations) {
		if (iterations > 0 && plainText != null) {
			iterations--;
			String result = getMD5Code(plainText);			
			if (iterations > 0) {
				result = getMD5Code(result, iterations);
			} 
			return result;					
		}
		return null;
	}
	
	/**
	 * 获取经过MD5加密后的字符串
	 * @param plainText 要加密的字符串
	 * @return 经过MD5算法加密的字符串形式的32位16进制,如果参数表示的字符串为空，返回null
	 */
	public static String getMD5Code(String plainText) {
		if (plainText != null) {
			try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(plainText.getBytes());
				return new BigInteger(1, messageDigest.digest()).toString(16);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将MD5码一段字符串替换成随机16进制字符
	 * @param md5Code 需要替换的MD5码
	 * @param offset 偏移量，即从第几个字符开始替换
	 * @param len 要替换的字符数
	 * @return 替换后的新字符串，如果参数表示的MD5码为空，则返回null
	 */
	public static String replaceCharacter(String md5Code, int offset, int len) {
		if (md5Code!=null) {
			char[] charArr = "1234567890abcdef".toCharArray();
			char[] md5Arr = md5Code.toCharArray();
			int n = charArr.length;
			Random random = new Random();
			for (int i = offset; i < offset+len; i++) {
				char randomChar = charArr[random.nextInt(n)];
				md5Arr[i] = randomChar;
			}
			return new String(md5Arr, 0, md5Arr.length);
		}		
		return null;
	}
	
	/**
	 * 获取文件的md5值
	 * @param path 文件的路径
	 * @return md5值，文件不存在返回null
	 */
	public static String getFileMd5Code(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
            return getMd5Code(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

    /**
     * 获取文件的md5值
     * @param file 文件
     * @return md5值，文件不存在返回null
     */
    public static String getFileMd5Code(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            return getMd5Code(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getMd5Code(InputStream in) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                messageDigest.update(buf, 0, len);
            }
            in.close();
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}