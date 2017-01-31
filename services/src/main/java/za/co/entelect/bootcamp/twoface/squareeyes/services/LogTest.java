package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by mpho.mahase on 2017/01/31.
 */
public class LogTest {
    private final static Logger logger = LoggerFactory.getLogger(LogTest.class);

    private String name;
    private String surname;
    public LogTest(String name, String surname){
        this.name = name;
        logger.info("name holds {}", name.getClass().getSimpleName());
        this.surname = surname;
        logger.info("name holds {}", surname.getClass().getSimpleName());
    }

    public void simple(){
        logger.info("outputting data");
    }
}
