package project01;

import com.google.gson.Gson;
import project01.Players.Player;
import project01.Players.PlayerManagement;

import java.io.IOException;
import java.util.Scanner;

public class CLI_Runner {

    static Scanner scanner = new Scanner(System.in);
    static Gson g = new Gson();

    public static void main(String[] args) throws Exception{
        //optional -- make a new player
        //create map out of all usernames and passwords
        //get username password and match them to the map
        // open userfile
        //create objects out of userfile
        //open logfile
        //do commands and log
        try {
            gameStarter();
            mainMenu();
        }catch (Exception e){System.out.println("errrrrrrror");}







    }

    private static void mainMenu() {
        System.out.println("PLEASE ENTER ONE OF THE OPTIONS BELOW:");
        System.out.println("-- game <<not available yet>>");
        System.out.println("-- collection");
        System.out.println("-- store");
        System.out.println("-- delete-player");
        System.out.println("-- exit");
        System.out.println("-- exit-a");

    }

    static void gameStarter(){
        boolean state = false;
        while (state == false ) {
            System.out.println("already have an account?(y/n)");
            String input = scanner.nextLine();
            try{
                if (input.equals("y")){
                    state = true;
                    signIn();

                }else if (input.equals("n")){
                    signUp();
                }
                else {
                    IOException e = new IOException();
                    throw e;
                }
            }catch (IOException e)  {
                System.out.println("enter a valid input");
            }
        }
    }

    private static void signUp(){
        System.out.println("Username:");
        String newUser = scanner.nextLine();
        System.out.println("Password:");
        String newPass = scanner.nextLine();
        Player player = new Player(newUser , newPass);
        System.out.println("CONGRATS! YOUR ACCOUNT HAS BEEN MADE.\n press enter to continue...");
        scanner.nextLine();
//        try
//        {
//            System.in.read();
//        }
//        catch(Exception e)
//        {}
        }

    private static void signIn(){
        boolean state = false;
        while (state == false){
            System.out.println("Username:");
            String username = scanner.next();
            System.out.println("Password:");
            String password = scanner.next();
            if (PlayerManagement.allPlayersContain(username , password)){
                state = true;
                PlayerManagement.setCurrentPlayer(  PlayerManagement.getPlayer(username,password)  );//g.fromJson("here must be your jason", Player.class ));
            }else{
                System.out.println("username or password is incorrect , try again... ");
            }
        }

    }



}

