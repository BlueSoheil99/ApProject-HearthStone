package project01.Players;

import project01.CartsAndHeros.Carts;

//dont forget to make log and hashed pass+ gson + management

public class Player {
    public static Player[] allPlayers;
    private static Player currentPlayer = null;
    private String   userName;
    private Password   password;
    private int      numberOfManas =50;
    private Carts[]  TotalCarts;

    private class Password{     // if
        Password(String newPass){
            //make a hashed pass.
        }

    }

    private Player(String newUser , String newPass ){
        this.userName = newUser;
        this.password = new Password(newPass); //or HashMaker(newPass)

    }

//    private void Hash HashMaker(String str){
//
//    }
    public static void signUp(String enteredUserName , String enteredPassword){
        //check if enteredUserName hasn't been used then initialize a new player
    }

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static void login(Player player){
        currentPlayer = player;

    } //must get username and password and then set currentPlayer

    public static void exit(){
        if (currentPlayer != null ){
            currentPlayer = null;
        }else{
            System.out.println("");
        }
    }

    public static void exitAll(){}

    public static void deletePlayer(){
        System.out.print( "password: ");
        if (){

            exit();
        }               //if password is correct we delete its file
    }


}
