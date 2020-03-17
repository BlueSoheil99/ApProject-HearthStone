package project01.Players;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project01.CartsAndHeros.Cart;
import project01.CartsAndHeros.Hero;
import project01.Log.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//dont forget to make log and hashed pass+ gson + management

public class Player {
    private static Gson g = new GsonBuilder().setPrettyPrinting().create();
    private static Logger logFile;
    private static int totalSignUps = 0;

    private String   userName;
    private String   password; //or make a inner class named password
    private int userId;
    private int  numberOfCoins =50;
    private Cart[]  totalCarts;
    private Hero[]  heroes;
    private String profilePath;
    private String logPath;


    public Player(String newUser , String newPass ) {
        totalSignUps += 1;
        userId = totalSignUps;
        this.userName = newUser;
        this.password = newPass ;// try to make password as a hashcode( by an inner class or a method)
        profilePath = "src/Data/Profiles/"+ this.getUserName()+".json";
        logPath     = "src/Data/Logs/"+ this.getUserName()+"_"+this.getUserId()+".log";
        //set carts
        //set heroes

                //create log
//        Logger logFile = new Logger( this.userName , this.password.toString());

    }


//setter

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public void setHeroes(Hero[] heroes) {
        this.heroes = heroes;
    }

    public void setTotalCarts(Cart[] totalCarts) {
        this.totalCarts = totalCarts;
    }

//getter

    public int getUserId() {
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public String getLogPath() {
        return logPath;
    }


    public void saveData(){
        try{
            FileWriter writer = new FileWriter(this.getProfilePath());
            PrintWriter printer = new PrintWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            printer.println( json );
            printer.close();
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

    public void createLog(){

    }


    //    public static void main(String[] args) {
//        Player player = new Player("newone" , "123456");
//        String json = g.toJson(player);
//        System.out.println(json);
//        System.out.println(g.fromJson(json , Player.class));
//
////        JsonWriter writer = new JsonWriter( new FileWriter("n,m"));
//        JsonReader reader = new JsonReader(new StringReader("myFile"+".txt"));
//
//
//    }
}
