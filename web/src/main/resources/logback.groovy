package za.co.entelect.bootcamp.twoface.squareeyes.web.logback

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
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
