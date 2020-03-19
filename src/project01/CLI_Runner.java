package project01;

import project01.Log.Logger;
import project01.Players.Player;
import project01.Players.PlayerManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CLI_Runner {

    static Scanner scanner = new Scanner(System.in);
    public static int menuHolder = 1;       //
    public static int runHolder = 1;
    public static boolean gameStarterState = false;

    public static void main(String[] args) {

        while (runHolder == 1) {
            try {
                gameStarter();
                while (menuHolder == 1) {
                    mainMenu();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.logError("CLI", e);
            }
        }
    }

    private static void gameStarter() {
        menuHolder = 1;
        gameStarterState = false;
        while (gameStarterState == false) {
            System.out.println("already have an account?(y/n)");
            String input = scanner.nextLine();
            try {
                if (input.equals("y")) {
                    gameStarterState = true;
                    signIn();

                } else if (input.equals("n")) {
                    signUp();
                } else {
                    throw new IOException("Enter a valid input");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void signUp() {
        /*
        to make it better i can define some requirements for entered username and password
        and also i'd better make "confirming step" to information get confirmed before profile being created
        */
        System.out.println("Username:");
        String newUser = scanner.nextLine();
        System.out.println("Password:");
        String newPass = scanner.nextLine();

        if (new File("src/Data/Profiles/" + newUser + ".json").exists()) {
            System.err.println("USERNAME IS INVALID");
        } else {
            Player player = new Player(newUser, newPass);
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
            System.out.println("CONGRATS! YOUR ACCOUNT HAS BEEN MADE.");
            continueByEnter();
        }

    }

    private static void signIn() {
        boolean state = false;
        while (state == false) {
            System.out.println("Username:");
            String username = scanner.nextLine();
            if (username.equals("back")) {
                gameStarterState = false;
                break;  //it starts gameStarter again
            }
            System.out.println("Password:");
            String password = scanner.nextLine();

            try {
                if (PlayerManagement.allPlayersContain(username, password)) {
                    state = true;
                    PlayerManagement.setCurrentPlayer(PlayerManagement.getPlayer(username));
                    System.out.println("\n** welcome " + PlayerManagement.getCurrentPlayer().getUserName().toUpperCase() + " **");

                } else {
                    System.err.println("username or password is incorrect , try again or enter "
                            + "'back' to return to starter menu."); //this error is for when password is incorrect we could've th
                }
            } catch (FileNotFoundException e) {
                System.err.println("player not found.");  //this error is for when username and pass are correct but username's profile is not existed. According to the condition above, this error never happens here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void mainMenu() {
        System.out.println("\nPLEASE ENTER ONE OF THE OPTIONS BELOW:\n" +
                "... or \"hearthstone-help\" to get help ...");
        System.out.println("-- game <<not available yet>>");
        System.out.println("-- collection");
        System.out.println("-- store");
        System.out.println("-- delete-player");
        System.out.println("-- exit");
        System.out.println("-- exit-a");
        String choice = scanner.nextLine();

        switch (choice) {

            case "hearthstone-help":
                gameHelper();
                break;

            case "game":
                System.out.println("not available");
                break;

            case "collection":
                Logger.log("MENU", "collection selected");

                break;

            case "store":
                Logger.log("MENU", "store selected");


                break;

            case "delete-player":
                System.out.println("Enter your password");
                String password = scanner.nextLine();
                try {
                    PlayerManagement.deletePlayer(password);
                    menuHolder = 0;   //stop running the mainMenu
                    runHolder = 0;  //to exit the whole game
                    System.out.println("Your account has been deleted successfully.\nPress enter to EXIT the game...");
                    scanner.nextLine();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    Logger.logError("player", e);
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

    private static void gameHelper() {
        boolean helpHolder = true;
        while (helpHolder) {
            System.out.println("\n  How can we help you ?" +
                    "( enter \"exit-help\" to exit help menu)");
            System.out.println("-- game <<not available yet>>");
            System.out.println("-- collection");
            System.out.println("-- store");
            System.out.println("-- delete-player");
            System.out.println("-- exit");
            System.out.println("-- exit-a");
            String choice = scanner.nextLine();
            switch (choice) {
                case "game":
                    System.out.println("not available");
                    continueByEnter();
                    break;

                case "collection":

                    continueByEnter();
                    break;

                case "store":
                    continueByEnter();
                    break;

                case "delete-player":
                    System.out.println("\tBy choosing this, the current player logs out of the game \nAt the end HearthStone CLOSES.");
                    continueByEnter();
                    break;

                case "exit":
                    System.out.println("\tBy choosing this, the current player logs out of the game \nand HearthStone RESTARTS.");
                    continueByEnter();
                    break;

                case "exit-a":
                    System.out.println("\tBy choosing this, the current player logs out of the game \nand HearthStone CLOSES.");
                    continueByEnter();
                    break;

                case "exit-help":
                    helpHolder = false;
                    break;

                default:
                    System.err.println("...invalid input...");
                    break;

            }
        }
    }

    static void continueByEnter(){
        System.out.println("  Press ENTER to continue...");
        scanner.nextLine();
    }

}

