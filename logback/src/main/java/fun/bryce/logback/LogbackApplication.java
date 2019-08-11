package fun.bryce.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LogbackApplication
{

    public static void main(String[] args) throws NoSuchFieldException
    {

        SpringApplication.run(LogbackApplication.class, args);

        log.info("logback,真简单");
        log.error(">>>");
        log.info(Log.builder().user("bryce").pwd("www").index("index").build().toString());

    }

}
