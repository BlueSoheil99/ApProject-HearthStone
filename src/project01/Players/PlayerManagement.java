package project01.Players;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class PlayerManagement {
    private static Player currentPlayer = null;

    private static FileHandler fh;
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    //////getter and setter
    public static void setCurrentPlayer(Player player){
        currentPlayer = player;

        try {
            fh = new FileHandler(player.getLogPath() , true);
            fh.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false);
            logger.addHandler(fh);
            logger.info("signed in");



        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

/////////
   public static boolean allPlayersContain(String username , String password) {
       boolean ans = false;

       try {
           JsonParser jsonParser = new JsonParser();

           FileReader reader = new FileReader("src/Data/Profiles/" + username + ".json");
           // if this file doesn't exist, we handle the exception with no message and we print an error in CLIRunner instead
           // , because this method is designed to give us true or false and exception is not expected from allPlayersContain
           JsonObject json = (JsonObject) jsonParser.parse(reader);
           reader.close();

           String playerRealPassword = json.get("password").toString();   //it gives us a string with additional quotation marks
           playerRealPassword = playerRealPassword.substring(1, playerRealPassword.length() - 1);  //we remove extra quotation marks

           if (playerRealPassword.equals(password)) {
               ans = true;
           }
           // if passwords don't match, we handle the exception with no message and we print an error in CLIRunner instead
           // , because this method is designed to give us true or false and exception is not expected from allPlayersContain

       }catch(FileNotFoundException e){       } //we display a message instead in CLI_Runner
       catch (IOException e) {
           e.printStackTrace();
       }

       return ans;
   }

   public static Player getPlayer(String username) throws IOException {
        Player player ;
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        FileReader reader = new FileReader("src/Data/Profiles/" + username+".json");

        player = gson.fromJson(jsonParser.parse(reader ), Player.class); //here we make a json element and then turn it into a Player object
        reader.close();
       return player;
   }

   public static void log(String level , String message){
        logger.log(Level.parse(level) , message);
//       Level.
    }

   public static void deletePlayer(String enteredPassword) throws IOException{
        if (currentPlayer.getPassword().equals(enteredPassword)){
            logger.warning("player deleted");
            fh.close();

            File file= new File(currentPlayer.getProfilePath());
            if ( ! file.delete()){
                throw new IOException("Deleting failed");
            }else {     //when deleting is done successfully
                currentPlayer.logFinalize();
            }
        }
        else{
            throw new IOException("Wrong password");
        }
   }

   public static void dumpCurrentPlayer(){
       Level level=Level.parse("sdf");
       logger.log(level , "sign outiindnfjkdad");
       ///make a signout log
        fh.close();
        currentPlayer.saveData();
        currentPlayer=null;
   }

}
