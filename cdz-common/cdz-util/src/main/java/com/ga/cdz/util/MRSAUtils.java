package com.ga.cdz.util;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MRSAUtils {
    /** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /** *//**
     * 签名算法
     */
    //public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /** */
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

//    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    /** */
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /** */
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = MBase64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return MBase64Util.encode(signature.sign());
    }

    /** */
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data      已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = MBase64Util.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(MBase64Util.decode(sign));
    }

    /** */
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = MBase64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey     公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = MBase64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = MBase64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data       源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = MBase64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return MBase64Util.encode(key.getEncoded());
    }

    /** */
    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return MBase64Util.encode(key.getEncoded());
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> map = genKeyPair();

            String publicKey = getPublicKey(map);
            String privateKey = getPrivateKey(map);
            System.out.println(publicKey);
            System.out.println(privateKey);
            //String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCypIZut1lu5w8/eZuoXKvQ0NcvkYH+EIM79hSE1SbVDIvf8OU6YsNrkyTVO4F3bRh7/prdInYdNjgveb3LC85hTfDxIKmBFnY6QEvc82YiDphM7e80jmOejgB0bH8Y7cDReZZ3UZOE5U1/g6K3RVsK8jegvfdnRbuJIa8nfYXKxQIDAQAB";
            //String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALKkhm63WW7nDz95m6hcq9DQ1y+Rgf4Qgzv2FITVJtUMi9/w5Tpiw2uTJNU7gXdtGHv+mt0idh02OC95vcsLzmFN8PEgqYEWdjpAS9zzZiIOmEzt7zSOY56OAHRsfxjtwNF5lndRk4TlTX+DordFWwryN6C992dFu4khryd9hcrFAgMBAAECgYAtsAg1nqy/wVZT/I0DxReoIxE1uDgPuzdlCW17/2ucB0Zqsphdk065X3uHEV3uJjeh1p7SuXKNzAUYl0EkAdP4/2uOlBs54fGOfoVOMECaCSLPzofQYoXfIHHvBjjccSAsNTqZ5hJjEeQTlKb1aIuXUMa10Oe1e5RlLgAPL4XPLQJBANc+6HFVxS4YX7kDpgxXDYVnCg8XcLOSrYdhC5n45RjQUJ+o88vP/BxOCOSMQZwFNlu/5zxHWU/v0T6Gdfpn3P8CQQDUd3HRX/P5a6OSscopYT9d8Kerso9aum8DNzyWeGpjnZfogG2YPY10YVQ+rD8U1J5I4KzpauumveJXfnOZfiQ7AkA3ubKl/OWirG7D0H0BvtviSK3jAFeONhA091vwje6KAQyOeUg84OjNTyqgOOuXgAX1tPYhtfsaZeUPQfJzVMZzAkEAq+dMhqwM+RQ3D3yb5ekR3Q7Wipv5Ut4e7comUlRxN9INU4d9pLzAk5YgFGlGzLLOsoZeQKlKquldlY7xNxKRewJBAKpN76ibYyoyFvBH6IT3gimZiT0yKMed3GkkxxmEKP7ZyMV3q0sdugoeWL2Ur39lmuwsxZsAyqj5mCuOKantDO4=";
            //{"userName": "dasdasdasd"}
            String dataString = "e-sure";
            byte[] data = dataString.getBytes("utf-8");
            String sign = sign(data, privateKey);
            boolean v = verify(data, publicKey, sign);

            byte[] privateData = encryptByPrivateKey(data, privateKey);
            byte[] publicData = encryptByPublicKey(data, publicKey);
            System.out.println("私钥：" + privateData.toString());
            System.out.println("公钥：" + publicData.toString());
            String base64PrivateData = MBase64Util.encode(privateData);
            System.out.println("私钥base64：" + base64PrivateData);
            String base64PublicData = MBase64Util.encode(publicData);
            System.out.printf("公钥base64：" + base64PublicData);

            byte[] ePrivateData = decryptByPrivateKey(MBase64Util.decode(base64PublicData), privateKey);
            byte[] ePublicData = decryptByPublicKey(MBase64Util.decode(base64PrivateData), publicKey);

            String raw = new String(ePrivateData, "utf-8");

            System.out.println("raw=" + raw);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //获取公钥
       /* try {
            String data="e-sure";
            String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCypIZut1lu5w8/eZuoXKvQ0NcvkYH+EIM79hSE1SbVDIvf8OU6YsNrkyTVO4F3bRh7/prdInYdNjgveb3LC85hTfDxIKmBFnY6QEvc82YiDphM7e80jmOejgB0bH8Y7cDReZZ3UZOE5U1/g6K3RVsK8jegvfdnRbuJIa8nfYXKxQIDAQAB";
            String privateKeyString="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALKkhm63WW7nDz95m6hcq9DQ1y+Rgf4Qgzv2FITVJtUMi9/w5Tpiw2uTJNU7gXdtGHv+mt0idh02OC95vcsLzmFN8PEgqYEWdjpAS9zzZiIOmEzt7zSOY56OAHRsfxjtwNF5lndRk4TlTX+DordFWwryN6C992dFu4khryd9hcrFAgMBAAECgYAtsAg1nqy/wVZT/I0DxReoIxE1uDgPuzdlCW17/2ucB0Zqsphdk065X3uHEV3uJjeh1p7SuXKNzAUYl0EkAdP4/2uOlBs54fGOfoVOMECaCSLPzofQYoXfIHHvBjjccSAsNTqZ5hJjEeQTlKb1aIuXUMa10Oe1e5RlLgAPL4XPLQJBANc+6HFVxS4YX7kDpgxXDYVnCg8XcLOSrYdhC5n45RjQUJ+o88vP/BxOCOSMQZwFNlu/5zxHWU/v0T6Gdfpn3P8CQQDUd3HRX/P5a6OSscopYT9d8Kerso9aum8DNzyWeGpjnZfogG2YPY10YVQ+rD8U1J5I4KzpauumveJXfnOZfiQ7AkA3ubKl/OWirG7D0H0BvtviSK3jAFeONhA091vwje6KAQyOeUg84OjNTyqgOOuXgAX1tPYhtfsaZeUPQfJzVMZzAkEAq+dMhqwM+RQ3D3yb5ekR3Q7Wipv5Ut4e7comUlRxN9INU4d9pLzAk5YgFGlGzLLOsoZeQKlKquldlY7xNxKRewJBAKpN76ibYyoyFvBH6IT3gimZiT0yKMed3GkkxxmEKP7ZyMV3q0sdugoeWL2Ur39lmuwsxZsAyqj5mCuOKantDO4=";

            PublicKey publicKey = getPublicKeys(publicKeyString);

            //获取私钥
            PrivateKey privateKey=getPrivateKeys(privateKeyString);

            //公钥加密
            byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
            System.out.println("加密后："+new String(encryptedBytes));

            //私钥解密
            byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
            System.out.println("解密后："+new String(decryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


    //将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey getPublicKeys(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKeys(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}
