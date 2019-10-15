package tech.codinglife.javastack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/11
 * Time: 4:33 PM
 */
@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        String serverPort = env.getProperty("local.server.port");
        log.info("Server is started at: http://localhost:" + serverPort);
    }
}
