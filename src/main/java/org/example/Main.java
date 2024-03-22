package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**SpringBootApplication introduces autoconfiguration:
 *  - component scan so that we don't have to define beans with @Bean or XML
 *  - define this class as a configuration file
 *  - autoconfiguration for some defaults (eg set up a integrated tomcat server
 *  and attempt to form a DB connection)*/
@SpringBootApplication
public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
    }
}