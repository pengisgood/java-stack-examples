package tech.codinglife.javastack;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/11
 * Time: 4:27 PM
 */
@Controller
public class GreetingController {

    @MessageMapping("/ping")
    @SendTo("/topic/pong")
    public GreetingMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GreetingMessage("Hello, " + message.getName() + "!");
    }

}
