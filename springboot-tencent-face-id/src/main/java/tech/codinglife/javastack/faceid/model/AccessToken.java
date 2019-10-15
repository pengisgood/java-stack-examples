package tech.codinglife.javastack.faceid.model;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-18
 * Time: 17:34
 */
public class AccessToken extends Base {
    private String transactionTime;
    private String access_token;
    private String expire_time;
    private String expire_in;

    public AccessToken() {
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(String expire_in) {
        this.expire_in = expire_in;
    }
}
