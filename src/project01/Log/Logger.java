package project01.Log;

import project01.Players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Logger {
    private static Logger logger;
    FileWriter writer;
    PrintWriter printer;
    private Logger(String logPath){
       try {
           writer = new FileWriter(logPath,true);
           printer  = new PrintWriter(writer);
       }catch (IOException e){
           logError("player" , e);
       }
    }

    public static void initLogger(Player player){
        logger = new Logger(player.getLogPath());

    }

    public static void closeLogfile(){
        logger.printer.close();
    }

    public static void log(String event , String description){
        logger.printer.println(event+"\t"+
                LocalDateTime.now().format( DateTimeFormatter.ofPattern(" yyyy/MM/dd  HH:mm:ss"))+
                "\t"+description);

    }

    /**
     * also take a look at the link in README.md
     * @param event show the section of game that player is in.
     * @param exception the  thrown exception
     */
    public static void logError(String event , Exception exception){
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();

        log("ERROR: "+event , exceptionAsString);
    }

}
