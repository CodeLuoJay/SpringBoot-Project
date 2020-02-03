package com.luojay.sell;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
public class LoggerTest {
   /* private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void  test1(){
        logger.info("info....");
        logger.debug("debug....");
        logger.error("error....");
    }*/
   @Test
   public void  test1(){
       String name = "hello";
       String password = "luojay";
       log.info("info....");
       log.debug("debug....");
       log.error("error....");
       log.info("name:{} password:{}",name,password);
   }
}
