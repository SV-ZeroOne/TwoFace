//import ch.qos.logback.core.ConsoleAppender
//import ch.qos.logback.core.FileAppender
///**
// * Created by kevin.heritage on 1/19/2017.
// */
//appender("STDOUT", ConsoleAppender) {
//    append = true
//    encoder(PatternLayoutEncoder) {
//        pattern = "%level %logger - %msg%n"
//    }
//}
//def USER_HOME = System.getProperty("user.home")
//appender("FILE", FileAppender){
//    file = "${USER_HOME}/logger.log"
//    append = true
//    encoder(PatternLayoutEncoder){
//        pattern = "%level %logger - %msg%n"
//    }
//}
//root(INFO, ["STDOUT"])

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import static ch.qos.logback.classic.Level.DEBUG

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        //pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %class{36}.%M %L - %msg%n"
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
root(DEBUG, ["STDOUT", "FILE"])

