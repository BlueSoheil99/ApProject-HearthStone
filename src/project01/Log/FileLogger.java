package project01.Log;


import project01.Players.Player;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private FileLogger(){}

    public static void openLogFile(Player player){
        try {
            FileHandler fh = new FileHandler( player.getLogPath() , true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        }catch (IOException e){}
    }
    public static void closeLogfile(){

    }

}
