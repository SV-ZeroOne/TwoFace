
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mpho.mahase on 2017/01/19.
 */
public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static void main(String[] args){
        logger.info("Here is some working that I'm testing");

    }

}
