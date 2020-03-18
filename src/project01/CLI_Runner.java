package project01;

import project01.Players.Player;
import project01.Players.PlayerManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static project01.Players.PlayerManagement.logger;

public class CLI_Runner {

    static Scanner scanner = new Scanner(System.in);
    public static int menuHolder = 1;
    public static int runHolder = 1;
    public static boolean gameStarterState = false;

    public static void main(String[] args) {

        //create objects out of userfile
        //open logfile
        //do commands and log


            while (runHolder == 1){
                try {
                    gameStarter();
                    while (menuHolder ==1 ){
                        mainMenu();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warning("ridi!");
//                    log()

                }
            }

    }

    private static void mainMenu() {
        System.out.println("\nPLEASE ENTER ONE OF THE OPTIONS BELOW:");
        System.out.println("-- game <<not available yet>>");
        System.out.println("-- collection");
        System.out.println("-- store");
        System.out.println("-- delete-player");
        System.out.println("-- exit");
        System.out.println("-- exit-a");
        String  choice =scanner.nextLine();

        switch (choice){

            case "game":
                System.out.println("not available");
                break;

            case "collection" :


                break;

            case "store":


                break;

            case "delete-player":
                System.out.println("Enter your password");
                String password = scanner.nextLine();
                try {
                    PlayerManagement.deletePlayer(password);
                    menuHolder=0;   //stop running the mainMenu
                    runHolder = 0;  //to exit the whole game
                    System.out.println("Your account has been deleted successfully.\nPress enter to exit the game...");
                    scanner.nextLine();
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
                break;

            case "exit":
                menuHolder = 0;
                PlayerManagement.dumpCurrentPlayer();
                break;

            case "exit-a":

                boolean rightInput = false;
                while (rightInput == false) {
                    System.out.println("Are you sure? (y/n)");
                    String ans = scanner.nextLine();
                    if (ans.equals("y")) {
                        menuHolder = 0;
                        runHolder = 0;
                        PlayerManagement.dumpCurrentPlayer();
                        rightInput = true;
                        //System.exit(0);
                    } else if (ans.equals("n")) {
                        rightInput = true;
                    } else {
                        System.err.println("Invalid input.");
                    }
                }
                break; //this break executes the final order for quiting the game

            default:
                System.err.println("...invalid input...");
                break;
        }
    }

    static void gameStarter(){
        menuHolder = 1;
        gameStarterState = false;
        while (gameStarterState == false ) {
            System.out.println("already have an account?(y/n)");
            String input = scanner.nextLine();
            try{
                if (input.equals("y")){
                    gameStarterState = true;
                    signIn();

                } else if (input.equals("n")){
                    try {
                        signUp();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
                else {
                    throw new IOException("Enter a valid input");
                }
            }catch (IOException e)  {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void signUp() {
        System.out.println("Username:");
        String newUser = scanner.nextLine();
        System.out.println("Password:");
        String newPass = scanner.nextLine();

        if ( new File("src/Data/Profiles/" + newUser+".json").exists() ) {
            System.err.println("USERNAME IS INVALID");
        }else {
            Player player = new Player(newUser , newPass);
///////////////////////////method 1 for saving data on file////////////////////
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();        //
//        FileWriter writer = new FileWriter(player.getProfilePath());       //
//        String json = gson.toJson(player);                                 //
//        System.out.println(json);                                          //
//        writer.write(json);                                                //
//        writer.close();                                                    //
///////////////////////////method 2 for saving data on file////////////////////
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();        //
//        FileWriter writer = new FileWriter(player.getProfilePath());       //
//        gson.toJson(player,writer);                                        //
//        writer.close();                                                    //
///////////////////////////method 3 for saving data on file////////////////////
            player.saveData();                                               //
            System.out.println("CONGRATS! YOUR ACCOUNT HAS BEEN MADE.\n press enter to continue...");
            scanner.nextLine();
        }

    }

    private static void signIn(){
        boolean state = false;
        while (state == false){
            System.out.println("Username:");
            String username = scanner.nextLine();
            if (username.equals("back")){
                gameStarterState = false;
                break;  //it starts gameStarter again
            }
            System.out.println("Password:");
            String password = scanner.nextLine();

            try {
                if(PlayerManagement.allPlayersContain( username , password)) {
                    state = true;
                    PlayerManagement.setCurrentPlayer( PlayerManagement.getPlayer(username) );
                    System.out.println("\n** welcome "+ PlayerManagement.getCurrentPlayer().getUserName().toUpperCase() + " **");
//                    FileHandler fh = new FileHandler(PlayerManagement.getCurrentPlayer().getLogPath(),true);
//                    fh.setFormatter(new SimpleFormatter());
//                    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//                    logger.addHandler(fh);
////                    logger.setLevel();
//                    logger.info("signed up " );
//                    fh.close();
                }else {
                    System.err.println("username or password is incorrect , try again or enter "
                            + "'back' to return to starter menu."); //this error is for when password is incorrect we could've th
                }
            }catch (FileNotFoundException e){
                System.err.println("player not found.");  //this error is for when username and pass are correct but username's profile is not existed. According to the condition above, this error never happens here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

