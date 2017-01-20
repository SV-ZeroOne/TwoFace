import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
/**
 * Created by kevin.heritage on 1/19/2017.
 */
appender("STDOUT", ConsoleAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}
def USER_HOME = System.getProperty("user.home")
appender("FILE", FileAppender){
    file = "${USER_HOME}/logger.log"
    append = true
    encoder(PatternLayoutEncoder){
        pattern = "%level %logger - %msg%n"
    }
}
root(INFO, ["FILE", "STDOUT"])