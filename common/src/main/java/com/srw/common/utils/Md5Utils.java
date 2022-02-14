package com.srw.common.utils;

import cn.hutool.crypto.SecureUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密等
 *
 * @author renwei.song
 */
public class Md5Utils {

    public static String encode(String input) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] bb = md.digest();
            int i;

            for (byte b : bb) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException ignored) {
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(Md5Utils.encode("renwei.song"));
        System.out.println(Md5Utils.md5("renwei.song"));
        System.out.println(Md5Utils.md5To16("renwei.song"));

        System.out.println(SecureUtil.md5("renwei.song"));

    }

    /**
     * 32位MD5加密
     *
     * @param paramString
     * @return
     */
    public static String md5(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            return toHexString(localMessageDigest.digest());
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return paramString;
    }

    /**
     * 16位MD5加密
     *
     * @param paramString
     * @return
     */
    public static String md5To16(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            return toHexString(localMessageDigest.digest()).substring(8, 24);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return paramString;
    }

    private static final char[] HEX_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    private static String toHexString(byte[] paramArrayOfByte) {
        int length = paramArrayOfByte.length;
        StringBuilder localStringBuilder = new StringBuilder(2 * length);
        for (int i = 0; ; ++i) {
            if (i >= length) {
                return localStringBuilder.toString().toUpperCase();
            }
            localStringBuilder.append(HEX_DIGITS[((0xF0 & paramArrayOfByte[i]) >>> 4)]);
            localStringBuilder.append(HEX_DIGITS[(0xF & paramArrayOfByte[i])]);
        }
    }


    /**
     * 对文件全文生成MD5摘要
     *
     * @param file 要加密的文件
     * @return MD5摘要码
     */
    public static String fileMD5(File file) {

        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            // 32位加密
            byte[] b = md.digest();
            return toHexString(b);
            // 16位加密
            // return buf.toString().substring(8, 24);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static String MD5Encode(String source, String encoding, boolean uppercase) {
        String result = null;
        try {
            result = source;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息-
            messageDigest.update(result.getBytes(encoding));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uppercase ? result.toUpperCase() : result;
    }
/*
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }*/
    /**
     * 转换字节数组为16进制字符串
     *
     * @param bytes 字节数组
     * @return
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     * 16进制的字符数组
     */
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};
}
