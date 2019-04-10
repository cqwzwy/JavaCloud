package encryption;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
 
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
 
 
/**
 * AES�ļ��ܺͽ���
 * @author libo
 */
public class aes {
    //��Կ (��Ҫǰ�˺ͺ�˱���һ��)
    private static final String KEY = "0102030405060708";  
    //�㷨
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    
    /** 
     * aes���� 
     * @param encrypt   ���� 
     * @return 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encrypt) {  
        try {
            return aesDecrypt(encrypt, KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }  
    }  
      
    /** 
     * aes���� 
     * @param content 
     * @return 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content) {  
        try {
            return aesEncrypt(content, KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }  
    }  
  
    /** 
     * ��byte[]תΪ���ֽ��Ƶ��ַ��� 
     * @param bytes byte[] 
     * @param radix ����ת�����Ƶķ�Χ����Character.MIN_RADIX��Character.MAX_RADIX��������Χ���Ϊ10���� 
     * @return ת������ַ��� 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// �����1��������  
    }  
  
    /** 
     * base 64 encode 
     * @param bytes �������byte[] 
     * @return ������base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return Base64.encodeBase64String(bytes);  
    }  
  
    /** 
     * base 64 decode 
     * @param base64Code �������base 64 code 
     * @return ������byte[] 
     * @throws Exception 
     */  
    public static byte[] base64Decode(String base64Code) throws Exception{  
        return StringUtils.isEmpty(base64Code) ? null : new Base64().decode(base64Code);  
    }  
  
      
    /** 
     * AES���� 
     * @param content �����ܵ����� 
     * @param encryptKey ������Կ 
     * @return ���ܺ��byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  
  
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
  
  
    /** 
     * AES����Ϊbase 64 code 
     * @param content �����ܵ����� 
     * @param encryptKey ������Կ 
     * @return ���ܺ��base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
  
    /** 
     * AES���� 
     * @param encryptBytes �����ܵ�byte[] 
     * @param decryptKey ������Կ 
     * @return ���ܺ��String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
        return new String(decryptBytes);  
    }  
  
  
    /** 
     * ��base 64 code AES���� 
     * @param encryptStr �����ܵ�base 64 code 
     * @param decryptKey ������Կ 
     * @return ���ܺ��string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    }  
    
    /**
     * ����
     */
    public static void main(String[] args) throws Exception {  
        String content = "{\"crsf_token\":\"\",\"limit\":\"20\",\"offset\":\"20\",\"rid\":\"R_SO_4_29343374\",\"total\":\"false\"}";  
        System.out.println("����ǰ��" + content);  
        System.out.println("������Կ�ͽ�����Կ��" + KEY);  
        String encrypt = aesEncrypt(content, KEY);  
        System.out.println("���ܺ�" + encrypt);  
        String decrypt = aesDecrypt(encrypt, KEY);  
        System.out.println("���ܺ�" + decrypt);  
    } 
}
