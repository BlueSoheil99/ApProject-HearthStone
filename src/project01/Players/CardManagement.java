package project01.Players;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import project01.CartsAndHeros.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class CardManagement {

    private static Hero currentHero;
    private static ArrayList<Card> playerTotalCards ;//= new ArrayList<>();
    private static HashMap<String , ArrayList<Card> > heroesAllDeckCards; //the keys are heroe and values are their deck
    private static HashMap<String , Card > gameTotalCards; //the keys are card names with upperCase letters and values are cards


    public static Hero getCurrentHero() {
        return currentHero;
    }
    public static ArrayList<Card> getPlayerTotalCards() {
        playerTotalCards.remove(null);
        return CardManagement.playerTotalCards;
    }
    public static HashMap<String, ArrayList<Card>> getHeroesAllDeckCards() {
        return heroesAllDeckCards;
    }
    public static HashMap<String, Card> getGameTotalCards(){
        return gameTotalCards;
    }


    public static void setCurrentHero(String heroName) throws IOException {        //if you added a hero you should add another if statement to this code
        switch (heroName.toUpperCase()) {
            case ("MAGE") :    //by comparing with upperCase letters the player will make less mistakes
                currentHero = Mage.getInstance();
                break;
            case "ROGUE":
                currentHero = Rogue.getInstance();
                break;
            case "WARLOCK":
                currentHero = Warlock.getInstance();
                break;
            default:
                throw new IOException(" unavailable hero( " + heroName + " )");
            }
            currentHero.loadAllHeroCards();
            currentHero.loadAllNeutralCards();
            currentHero.loadDeckHeroCarts();
            currentHero.loadDeckNeutralCards();
    }



    ////methods

    public static void initGameTotalCards(){
        Card card ;
        gameTotalCards = new HashMap<String , Card >();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        String[] allCardNames = new File("src\\Data\\Cards").list();
        try {
            for( String cardName:allCardNames) {
                cardName=cardName.substring(0,cardName.length()-5);
                FileReader reader = new FileReader("src/Data/Cards/"+cardName+".json");
                card = gson.fromJson( jsonParser.parse(reader) , Card.class ); //here we make a jsonElement and then turn it into a Card object
                gameTotalCards.put(card.getName().toUpperCase() , card);
                reader.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    } //this method must run at the beginning of running cli-runner main method

    public static void loadPlayerTotalCards() {
        Card card;
        ArrayList<Card> list =new ArrayList<Card>();
        for(String cardName : PlayerManagement.getCurrentPlayer().getPlayerTotalCards()){
            card = gameTotalCards.get(cardName.toUpperCase());
            list.add(card);
        }
        playerTotalCards = list;
    }

    public static void loadPlayerHeroesAllDeckCards() {
        Card card;
        heroesAllDeckCards = new HashMap<>();
        HashMap<String , ArrayList<String>> savedHeroesDeckCards = PlayerManagement.getCurrentPlayer().getHeroesAllDeckCards();
        for (String cardClass : savedHeroesDeckCards.keySet()){
            ArrayList<Card> x = new ArrayList<>() ;
            for (String cardName : savedHeroesDeckCards.get(cardClass)){
                card = gameTotalCards.get(cardName.toUpperCase());
                x.add(card);
            }
            heroesAllDeckCards.put(cardClass , x);
        }

    }


    public static void buyCard(String cardName) throws IOException{
        cardName=cardName.toUpperCase();
        if( gameTotalCards.containsKey(cardName) ) {
            Player player = PlayerManagement.getCurrentPlayer();
            Card card = gameTotalCards.get(cardName);

            if (player.getCoins() >= card.getCost()) {
                playerTotalCards.add(card);
                player.setCoins(player.getCoins() - card.getCost());

//                if( card.getHeroClassName().equals("Neutral") ){   //we apply changes in cards of heroes
//                    Mage.allNeutralCards.add(card) ;
//                    Warlock.allNeutralCards.add(card);
//                    Rogue.allNeutralCards.add(card);
//                }else {
//                    currentHero.allHeroCards.add(card);
//                }
                currentHero.loadAllHeroCards();
                currentHero.loadAllNeutralCards();

            }else {
                throw new RuntimeException("not enough money to buy : "+cardName);
            }
        }else {
            throw new IOException("card( "+cardName + " ) doesn't exist");
        }
    }

    public static void sellCard(String cardName) throws Exception {
        cardName=cardName.toUpperCase();

        if( gameTotalCards.containsKey(cardName) ) {
            Card card = gameTotalCards.get(cardName);

            if (playerTotalCards.contains(card)){
                boolean deckContainsThisCard = false;
                ArrayList<String> heroesWithThisCard=new ArrayList<>();

                for (String hero : heroesAllDeckCards.keySet()) {
                    if(heroesAllDeckCards.get(hero).contains(card)){
                        deckContainsThisCard = true;
                        heroesWithThisCard.add(hero);
                    }
                }

                if ( ! deckContainsThisCard ){
                    playerTotalCards.remove(card);
                    PlayerManagement.getCurrentPlayer().setCoins( PlayerManagement.getCurrentPlayer().getCoins() + card.getCost() );

//                    if( card.getHeroClassName().equals("Neutral") ){   //we apply changes in cards of heroes
//                        Mage.allNeutralCards.remove(card) ;
//                        Warlock.allNeutralCards.remove(card);
//                        Rogue.allNeutralCards.remove(card);
//                    }else {
//                        currentHero.allHeroCards.remove(card);
//                    }
                    currentHero.loadAllHeroCards();
                    currentHero.loadAllNeutralCards();

                }else {
                    throw new Exception("unsalable card "+card.getName() +" is in these heroes deck:\t"+heroesWithThisCard);
                }
            }else{
                throw new Exception("players hasn't "+card.getName().toUpperCase()+" card");
            }
        }else {
            throw new IOException("card( "+cardName + " ) doesn't exist");
        }

    }


//    public static void setPlayerTotalCards(ArrayList<Card> playerTotalCards) {
//        CardManagement.playerTotalCards = playerTotalCards;
//    }
}
