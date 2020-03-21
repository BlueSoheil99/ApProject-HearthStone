package project01.Players;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project01.CartsAndHeros.Card;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Player {

    private String   userName;
    private String   password; //or make a inner class named password
    private int userId;
    private int coins =50;
    private String profilePath;
    private String logPath;
    private String currentHero;
    private ArrayList<String> playerTotalCards = new ArrayList<>();
    private HashMap<String , ArrayList<String>> heroesAllDeckCards;


//    private static String[] defaultTotalCards = {"Gorehowl" , "SuperCollider"  , "Friendly Smith" , "Polymorph" , "Concrete Shield" ,
//            "Humility" , "Innervate" , "Omega Medic" , "Waterboy" , "Voodoo doctor" , "Slam" , "Mole","Arcane explosion","Dreadscale",
//            "Shadow Madness", "zilliax", "Abomination" , "Murloc Tidehunter", "Consecration","Stormhammer"    };

    private static String[] defaultPlayerTotalCards = { "SuperCollider"  , "Friendly Smith" , "Polymorph" ,
        "Humility" , "Innervate" , "Omega Medic" , "Waterboy" , "Voodoo doctor" , "Slam" ,"Dreadscale"
        , "Abomination" , "Murloc Tidehunter"  };
    private static HashMap<String , ArrayList<String>>  defaultDeckCards = new HashMap<String , ArrayList<String>>() ;
    {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList( "SuperCollider","Murloc Tidehunter","Abomination","Humility", "Innervate", "Omega Medic", "Waterboy", "Voodoo doctor", "Slam"));
        defaultDeckCards.put("Neutral", list);
        list = new ArrayList<>();
        list.addAll(Arrays.asList("Polymorph"));
        defaultDeckCards.put("Mage", list);
        list = new ArrayList<>();
        list.addAll(Collections.singletonList("Dreadscale"));
        defaultDeckCards.put("Warlock", list);
        list = new ArrayList<>();
        list.addAll(Collections.singletonList("Friendly Smith"));
        defaultDeckCards.put("Rogue", list);
    }

    public Player(String newUser , String newPass ) {
        userId = new File("src\\Data\\Logs").list().length + 1 ;
        this.userName = newUser;
        this.password = newPass ;  // try to make password as a hashcode( by an inner class or a method)
        currentHero = "Mage";

        playerTotalCards.addAll(Arrays.asList(defaultPlayerTotalCards));
        heroesAllDeckCards = defaultDeckCards;
        profilePath = "src/Data/Profiles/"+ this.getUserName()+".json";
        logPath     = "src/Data/Logs/"+ this.getUserName()+"-"+this.getUserId()+".log";
        this.logInit();
    }

    ////getter
    public int getUserId() {
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public int getCoins(){
        return coins;
    }
    public ArrayList<String> getPlayerTotalCards() {
        return playerTotalCards;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public String getLogPath() {
        return logPath;
    }
    public String getCurrentHero() {
        return currentHero;
    }
    public HashMap<String, ArrayList<String>> getHeroesAllDeckCards() {
        return heroesAllDeckCards;
    }


    ////setter
    public void setCoins(int coins) {
        this.coins = coins;
    }
    private void setPlayerTotalCards(ArrayList<Card> playerTotalCards){
        this.playerTotalCards =new ArrayList<>(); //this makes playerTotalCards empty
        for (Card card: playerTotalCards) {
            this.playerTotalCards.add(card.getName());
        }
    }
    public void setHeroesAllDeckCards(HashMap<String , ArrayList<Card>> heroesAllDeckCards) {
        for (String hero : heroesAllDeckCards.keySet()) {
            ArrayList<String> deck = new ArrayList<>();
            for (Card card: heroesAllDeckCards.get(hero)) {
                deck.add(card.getName());
            }
            this.heroesAllDeckCards.put(hero , deck);
        }
    }
    public void setCurrentHero(String currentHero) {
        this.currentHero = currentHero;
    }


    ////methods
    private void logInit(){
        try {
            FileWriter writer = new FileWriter(this.getLogPath());
            PrintWriter printer =new PrintWriter(writer);
            printer.println("USER: "+this.getUserName());
            printer.println("CREATED_AT: " + LocalDateTime.now().format( DateTimeFormatter.ofPattern(" yyyy/MM/dd  HH:mm:ss") ));
            printer.println("PASSWORD: "+this.getPassword());
            printer.println(" ");

            printer.close();
            //or writer.close() ??????
        }catch (Exception e ) {
            System.err.println(e.getMessage());
        }
    }
    void logFinalize(){  // in this method we used the link in README + the way we made logInit method
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(logPath));
            StringBuffer inputBuffer = new StringBuffer();
            String line ;

            while ( (line= file.readLine()) != null) {
                if(line.equals(" ")) {
                    inputBuffer.append("DELETED_AT: " +
                                       LocalDateTime.now().format( DateTimeFormatter.ofPattern(" yyyy/MM/dd  HH:mm:ss"))+"\n \r\n" );
                                        // \r\n to make a distance between header and body
                }else{
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            file.close();
            // i tried using FileInputStreamer but it didn't work well so i did the same as i did in logInit method
            String inputStr = inputBuffer.toString();
            FileWriter writer = new FileWriter(this.getLogPath());
            PrintWriter printer =new PrintWriter(writer);
            for (String x:inputStr.split("\n")) {
                printer.println(x);
            }
            printer.close();

        }catch (Exception e){
          System.err.println(e.getMessage());
        }

    }

    /**
     * rewrites the player's information on its profile
     */
    public void saveDataForTheFirstTime(){
        try{
            FileWriter writer = new FileWriter(this.getProfilePath());
            PrintWriter printer = new PrintWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            printer.println( json );
            printer.close(); //or flush() ?
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }
    public void saveData(){
        setPlayerTotalCards(CardManagement.getPlayerTotalCards() );
        setHeroesAllDeckCards(CardManagement.getHeroesAllDeckCards() );
        saveDataForTheFirstTime();
    }

}
