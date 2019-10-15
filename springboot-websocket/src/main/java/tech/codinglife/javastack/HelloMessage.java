package tech.codinglife.javastack;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/11
 * Time: 4:24 PM
 */
public class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
