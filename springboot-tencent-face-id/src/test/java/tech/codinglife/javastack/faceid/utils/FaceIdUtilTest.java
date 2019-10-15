package tech.codinglife.javastack.faceid.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-19
 * Time: 17:14
 */
public class FaceIdUtilTest {

    @Test
    public void calculateSign() {
        String[] args = new String[]{"appId001", "orderNo19959248596551", "testName", "4300000000000", "userID19959248596551", "1.0.0", "duSz9ptwyW1Xn7r6gYItxz3feMdJ8Na5x7JZuoxurE7RcI5TdwCE4KT2eEeNNDoe"};
        String sign = FaceIdUtil.calculateSign(args);

        assertEquals("EE57F7C1EDDE7B6BB0DFB54CD902836B8EB0575B", sign);
    }

    @Test
    public void encodeURIComponent() {
        String s = FaceIdUtil.encodeURIComponent("https://cloud.tencent.com");
        assertEquals("https%3A%2F%2Fcloud.tencent.com", s);

        String s2 = FaceIdUtil.encodeURIComponent("http://localhost:8081");
        assertEquals("http%3A%2F%2Flocalhost%3A8081", s2);
    }
}
