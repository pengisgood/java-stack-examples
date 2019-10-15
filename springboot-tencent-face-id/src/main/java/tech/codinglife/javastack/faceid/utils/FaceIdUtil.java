package tech.codinglife.javastack.faceid.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-19
 * Time: 16:03
 */
public class FaceIdUtil {
    public static String calculateSign(String[] args) {
        Objects.requireNonNull(args);
        System.out.println("sign args=" + Arrays.toString(args));

        Arrays.sort(args);
        List<String> list = Arrays.asList(args);
        String str = String.join("", list);
        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new BigInteger(1, crypt.digest()).toString(16).toUpperCase();
    }

    public static String calculateNonce(int len) {
        String s = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm";
        Random rng = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = rng.nextInt(62);
            sb.append(s, index, index + 1);
        }
        String nonce = sb.toString();
        System.out.println("nonce=" + nonce);
        return nonce;
    }

    public static String encodeURIComponent(String uri) {
        try {
            return URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
