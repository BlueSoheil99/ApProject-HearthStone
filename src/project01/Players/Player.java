package project01.Players;

import com.google.gson.Gson;
import project01.CartsAndHeros.Cart;
import project01.CartsAndHeros.Hero;
import project01.Log.Logger;

//dont forget to make log and hashed pass+ gson + management

public class Player {
    private static Gson g = new Gson();
    private static Logger logFile;
    private static int totalSignUps = 0;

    private String   userName;
    private String   password; //or make a inner class named password
    private int userId;
    private int  numberOfCoins =50;
    private Cart[]  totalCarts;
    private Hero[]  heroes;


    public Player(String newUser , String newPass ){
        totalSignUps += 1;
        userId = totalSignUps;
        this.userName = newUser;
        this.password = newPass ;// try to make password as a hashcode( by an inner class or a method)
        //set carts
        //set heroes
        //create profile
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
    String getUserName(){
        return userName;
    }
    String getPassword(){
        return password;
    }

}
