package project01;

import project01.CartsAndHeros.Card;
import project01.Log.Logger;
import project01.Players.CardManagement;
import project01.Players.Player;
import project01.Players.PlayerManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI_Runner {

    static Scanner scanner = new Scanner(System.in);
    public static int menuHolder = 1;       //
    public static int runHolder = 1;
    public static boolean collectionHolder = true;
    public static boolean storeHolder = true;
    public static boolean gameStarterState = false;

    public static void main(String[] args) {
        CardManagement.initGameTotalCards(); //initializing all cards of the game at the beginning

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

    static void continueByEnter(){
        System.out.print("  Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void gameStarter() {
        menuHolder = 1;
        gameStarterState = false;
        while ( !gameStarterState) {
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
        while ( ! state ) {
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
//                    CardManagement.initGameTotalCards(); //we'd better use this method at the beginning of whole RUN
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
                collection();
                break;

            case "store":
                Logger.log("MENU", "store selected");
                store();
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
                while (!rightInput) {
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

    private static void collection(){
        collectionHolder = true;
        while (collectionHolder) {
            System.out.println("\nCOLLECTION MENU :\n" +
                    "PLEASE ENTER ONE OF THE OPTIONS BELOW:\n"+
                    "... or \"hearthstone-help\" to get help ...");
            System.out.println("-- Heroes");
            System.out.println("-- View-cards");
            System.out.println("-- Edit-cards");
            System.out.println("-- back-menu");
            String choice = scanner.nextLine();
            switch (choice.toUpperCase()) {
                default:
                    System.err.println("...invalid input...");
                    break;
                case "HEARTHSTONE-HELP":
                    gameHelper();
                    break;
                case "HEROES":
                    heroHandler();
                    Logger.log("COLLECTION", "Heroes selected");
                    break;
                case "VIEW-CARDS":
                    cardViewerHandler();
                    Logger.log("COLLECTION", "viewCards selected");
                    break;
                case "EDIT-CARDS":
                    cardEditorHandler();
                    Logger.log("COLLECTION", "editCards selected");
                    break;
                case "BACK-MENU":
                    collectionHolder = false;
                    break;
            }
        }
    }
    private static void heroHandler(){
        boolean heroHandlerHolder=true;
        while (heroHandlerHolder) {
            System.out.println("\nPLEASE ENTER ONE OF THE HERO OPTIONS BELOW:\n" +
                    "you can enter \"select [hero name] \" to select a hero or \"hearthstone-help\" to get help ...");
            System.out.println("-- ls-a-heroes");
            System.out.println("-- ls-m-heroes");
            System.out.println("-- back-collection");
            String choice = scanner.nextLine();
            switch (choice.toUpperCase()) {

                case "HEARTHSTONE-HELP":
                    gameHelper();
                    break;
                case "BACK-COLLECTION":
                    heroHandlerHolder = false;
                    break;
                case "LS-A-HEROES":
                    Logger.log("HERO", "heroes displayed: All");
                    System.out.println("\n Your heroes are : (neutral is not a hero! it's just a card class ;) ) ");
                    for (Card.HeroClass hero: Card.HeroClass.values() ) {
                        System.out.println("-- "+hero);
                    }
                    continueByEnter();
                    break;
                case "LS-M-HEROES":
                    Logger.log("HERO", "heroes displayed: currentHero ("
                            + CardManagement.getCurrentHero().getClass().getSimpleName()+ " )");
                    System.out.println("\n Your current hero is : ");
                    System.out.println( CardManagement.getCurrentHero().getClass().getSimpleName() );
                    continueByEnter();
                    break;

                default:
                    if (choice.split(" ")[0].toUpperCase().equals("SELECT")){
                        String enteredHero = choice.split(" ")[1].toUpperCase();
                        try {
                            CardManagement.setCurrentHero(enteredHero);
                            PlayerManagement.getCurrentPlayer().setCurrentHero(enteredHero);
                            Logger.log("HERO" , "hero selected :" + enteredHero);
                            System.out.println(enteredHero+" selected");
                            continueByEnter();
                        }catch (IOException e){
                            e.printStackTrace();
                            Logger.logError( "HERO",e);
                        }
                    }else {
                        System.err.println("...invalid input...");
                    }
                    break;
            }
        }
    }
    private static void cardViewerHandler(){

        boolean cardViewerHandlerHolder=true;

        while (cardViewerHandlerHolder) {
            System.out.println("\nPLEASE ENTER ONE OF THE CARD VIEW OPTIONS BELOW:\n" +
                    "... or \"hearthstone-help\" to get help ...");
            System.out.println("-- ls-a-cards");
            System.out.println("-- ls-m-cards");
            System.out.println("-- ls-n-cards");
            System.out.println("-- back-collection");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                default:
                    System.err.println("...invalid input...");
                    break;
                case "BACK-COLLECTION":
                    cardViewerHandlerHolder = false;
                    break;
                case "HEARTHSTONE-HELP":
                    gameHelper();
                    break;

                case "LS-A-CARDS":
                    Logger.log("CARD",
                            CardManagement.getCurrentHero().getClass().getSimpleName()+"cards displayed: All");

                    int allCardsHeroHas = CardManagement.getCurrentHero().getAllCards().size() ;
                    System.out.println("\nyour hero has these " + allCardsHeroHas + " cards: ");

                    for (Card card : CardManagement.getCurrentHero().getAllCards() ) {
                        System.out.println(card.getName() + "   class: " + card.getHeroClassName());
                    }
                    continueByEnter();
                    break;

                case "LS-M-CARDS":
                    Logger.log("CARD",
                            CardManagement.getCurrentHero().getClass().getSimpleName()+"cards displayed: DECK");

                    int deckCardsHeroHas = CardManagement.getCurrentHero().getDeckCards().size() ;
                    System.out.println("\nyour hero has these " + deckCardsHeroHas + " cards in deck: ");

                    for (Card card : CardManagement.getCurrentHero().getDeckCards()) {
                        System.out.println(card.getName() + "   class: " + card.getHeroClassName());
                    }
                    continueByEnter();
                    break;

                case "LS-N-CARDS":
                    Logger.log("CARD",
                            CardManagement.getCurrentHero().getClass().getSimpleName()+"cards displayed: unUSED");

                    int unusedCardsHeroHas = CardManagement.getCurrentHero().getUnusedCards().size();
                    System.out.println("\nyour hero isn't using these " + unusedCardsHeroHas + " cards in deck: ");

                    for (Card card : CardManagement.getCurrentHero().getUnusedCards()) {
                        System.out.println(card.getName() + "   class: " + card.getHeroClassName());
                    }
                    continueByEnter();
                    break;
            }
        }
    }
    private static void cardEditorHandler(){

        boolean cardEditorHandlerHolder=true;

        while (cardEditorHandlerHolder) {
            System.out.println("\nPLEASE ENTER ONE OF THE DECK EDIT OPTIONS BELOW:\n" +
                    "... or \"hearthstone-help\" to get help ...");
            System.out.println("-- add [Card’s Name]");
            System.out.println("-- remove [Card’s Name] ");
            System.out.println("-- back-collection");
            String choice = scanner.nextLine();
            String[] choiceArray = choice.split(" ");

            if (choice.equals("back-collection")){
                cardEditorHandlerHolder=false;

            }else if (choiceArray[0].equals("add")){
                choice = choice.substring(4).strip();
                try {
                    CardManagement.getCurrentHero().addToDeck(choice);
                    Logger.log("CARD" , "card " + choice+ " added to "
                            +CardManagement.getCurrentHero().getClass().getSimpleName()+" deck .");
                }catch (Exception e){
                    System.out.println( e.getMessage() );
                    Logger.logError("CARD" , e);
                }

            }else if (choiceArray[0].equals("remove")){
                choice = choice.substring(7).strip();
                try {
                    CardManagement.getCurrentHero().removeFromDeck(choice);
                    Logger.log("CARD" , "card " + choice+ " removed from "
                            +CardManagement.getCurrentHero().getClass().getSimpleName()+" deck .");
                }catch (Exception e){
                    System.out.println( e.getMessage() );
                    Logger.logError("CARD" , e);
                }
            }else{
                System.err.println("...invalid input...");
            }
        }
    }

    private static void store(){
        storeHolder= true;
        while (storeHolder) {
            System.out.println("\nSTORE MENU :\n" +
                    "PLEASE ENTER ONE OF THE OPTIONS BELOW:\n"+
                    "you can enter \"buy [Card’s name] \" or \"sell [Card’s name] \" or \"hearthstone-help\" to get help ...");
            System.out.println("-- Wallet");
            System.out.println("-- ls-b      (buyable cards)");
            System.out.println("-- ls-s      (salable cards)");
            System.out.println("-- back-menu");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {

                case "HEARTHSTONE-HELP":
                    gameHelper();
                    break;

                case "BACK-MENU":
                    storeHolder = false;
                    break;

                case "WALLET":
                    System.out.println("\nyou have "+PlayerManagement.getCurrentPlayer().getCoins()+" coins");
                    continueByEnter();
                    break;

                case "LS_S":
                    System.out.println("\nAll cards you have are "+CardManagement.getPlayerTotalCards().size()+" cards below: ");
                    for (Card card : CardManagement.getPlayerTotalCards() ){
                        System.out.println("\tName: "+card.getName()+"\tClass: "+card.getHeroClassName()+"\tRarity: "+card.getRarity()+"\tcost: "+card.getCost());
                    }
                    Logger.log("STORE" , "cards displayed: salable cards");
                    continueByEnter();
                    break;

                case "LS-B":
                    ArrayList<Card> buyableCards=new ArrayList<>(CardManagement.getGameTotalCards().values());
                    buyableCards.removeAll(CardManagement.getPlayerTotalCards());
                    System.out.println("\nAll cards you can buy are "+CardManagement.getPlayerTotalCards().size()+" cards below: ");
                    for (Card card : buyableCards ){
                        System.out.println("\tName: "+card.getName()+"\tClass: "+card.getHeroClassName()+"\tRarity: "+card.getRarity()+"\tcost: "+card.getCost());
                    }
                    Logger.log("STORE" , "cards displayed: buyable cards");

                    continueByEnter();

                    break;

                default:
                    choice = choice.toUpperCase().strip();
                    String[] input  = choice.split(" ");

                    if ( input[0].equals("BUY") ){
                        try {
                            CardManagement.buyCard( choice.substring(4) );
                            Logger.log("STORE","buyCard: "+choice.substring(4));
                            System.out.println("...card is purchased successfully...");
                            continueByEnter();
                        }catch (IOException e){
                            System.err.println(e.getMessage());
                            Logger.logError("STORE",e);
                            continueByEnter();
                        }

                    }else if (input[0].equals("SELL")) {
                        try {
                            CardManagement.sellCard(choice.substring(5));
                            Logger.log("STORE", "SellCard: " + choice.substring(5));
                            System.out.println("...card is sold successfully...");
                            continueByEnter();
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                            Logger.logError("STORE", e);
                            continueByEnter();
                        }

                    }else {
                        System.err.println("...invalid input...");
                    }

                    break;
            }
        }
    }

    private static void gameHelper() {
        boolean helpHolder = true;
        while (helpHolder) {
            System.out.println("\n  How can we help you ?" +
                    "( enter \"exit-help\" to exit help menu)");
            System.out.println("-- game");
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
                    collectionHelp();
                    continueByEnter();
                    break;

                case "store":
                    storeHelp();
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
    private static void storeHelp(){
        boolean storeHelpHolder = true;
        while (storeHelpHolder) {
            System.out.println("\n STORE HELP MENU : Which part can we help you with?" +
                    "( enter \"back-menu\" to back to help menu)");
            System.out.println("-- Wallet");
            System.out.println("-- ls-b  ");
            System.out.println("-- ls-s  ");
            System.out.println("-- buy-cards ");
            System.out.println("-- sell-cards ");
            String choice = scanner.nextLine();
            switch(choice.toUpperCase()) {
                case "BACK-MENU":
                    storeHelpHolder = false;
                    break;
                default:
                    System.err.println("...invalid input...");
                    break;

                case "LS-B":
                    System.out.println("ls-b help not completed ");
                    continueByEnter();
                    break;
                case "LS_S":
                    System.out.println("ls-s help not completed ");
                    continueByEnter();
                    break;
                case "WALLET":
                    System.out.println("wallet help not completed ");
                    continueByEnter();
                    break;
                case "BUY-CARDS":
                    System.out.println("buyCards help not completed ");
                    continueByEnter();
                    break;
                case "SELL-CARDS":
                    System.out.println("sellCards help not completed ");
                    continueByEnter();
                    break;
            }
        }
    }
    private static void collectionHelp(){
        boolean collectionHelpHolder = true;
        while (collectionHelpHolder) {
            System.out.println("\n  Which part can we help you with?" +
                    "( enter \"back-menu\" to back to help menu)");
            System.out.println("-- Heroes");
            System.out.println("-- View-cards");
            System.out.println("-- Edit-cards");
            String choice = scanner.nextLine();
            switch(choice.toUpperCase()) {
                case "HEROES":
                    System.out.println("hero help not completed ");
                    continueByEnter();
                    break;
                case"VIEW-CARDS":
                    System.out.println("viewCards help not completed ");
                    continueByEnter();
                    break;
                case"EDIT-CARDS":
                    System.out.println("editCards help not completed ");
                    continueByEnter();
                    break;
                case"BACK-MENU":
                    collectionHelpHolder =false;
                    break;
                default:
                    System.err.println("...invalid input...");
                    break;
            }
        }
    }


}

