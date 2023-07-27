/*
 *
 * This file is generated under this project, "kr.re.etri.gba.adt.web".
 *
 * Date  : 2018. 8. 31. 오전 10:58:50
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.sbp.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;

/**
 * 
 * @since 2018. 8. 31.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class EncryptUtils {

    private static final String CHARSET = "UTF-8";

    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    // prevent to create an instance.
    private EncryptUtils() {
    }

    /**
     * 암호화된 문자열을 키를 이용하여 복호화하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 8. 31.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            길이가 최소 16이상인 문자열
     * @param encText
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @since 2018. 8. 31.
     */
    public static String decrypt(String key, String encText) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec keySpec = keySpec(key);

        String iv = key.substring(0, 16);

        Cipher cihper = Cipher.getInstance(CIPHER_TRANSFORMATION);

        cihper.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encBytes = Base64Utils.decodeFromUrlSafeString(encText);

        return new String(cihper.doFinal(encBytes), CHARSET);
    }

    /**
     * 키를 이용하여 주어진 문자열을 암호화하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 8. 31.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            길이가 최소 16이상인 문자열
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @since 2018. 8. 31.
     */
    public static String encrypt(String key, String plainText) //
            throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec keySpec = keySpec(key);

        String iv = key.substring(0, 16);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = cipher.doFinal(plainText.getBytes(CHARSET));

        return Base64Utils.encodeToUrlSafeString(encrypted);
    }

    private static SecretKeySpec keySpec(String key) throws UnsupportedEncodingException {

        byte[] keyBytes = key.getBytes(CHARSET);

        byte[] keySpecBytes = new byte[16];

        System.arraycopy(keyBytes // source
                , 0 // start point
                , keySpecBytes // target
                , 0, keyBytes.length > keySpecBytes.length ? 16 : keyBytes.length // copy length
        );

        SecretKeySpec keySpec = new SecretKeySpec(keySpecBytes, "AES");

        return keySpec;
    }
}
