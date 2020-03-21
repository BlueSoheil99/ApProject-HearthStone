package project01.Players;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import  project01.Log.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public abstract class PlayerManagement {
    static Player currentPlayer = null;


    //////getter and setter
    public static void setCurrentPlayer(Player player){
        currentPlayer = player;
        CardManagement.loadPlayerTotalCards();
        CardManagement.loadPlayerHeroesAllDeckCards();
        try {
            CardManagement.setCurrentHero(player.getCurrentHero());
        }catch (Exception e){
            e.printStackTrace();
        }


        try {
            CardManagement.setCurrentHero(player.getCurrentHero());
        }catch (Exception e){
            e.printStackTrace();
        }

        Logger.initLogger(player);
        Logger.log("player" , currentPlayer.getUserName()+" signed in");
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
           // , because this method is designed to give us true or false and "exception" is not expected from allPlayersContain

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

   public static void deletePlayer(String enteredPassword) throws IOException{
        if (currentPlayer.getPassword().equals(enteredPassword)){
            Logger.log("player" , currentPlayer.getUserName()+" got deleted");
            Logger.closeLogfile();

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
        Logger.log("player" , currentPlayer.getUserName()+" signed out");
        Logger.closeLogfile();
        currentPlayer.saveData();
        currentPlayer=null;
   }

}
