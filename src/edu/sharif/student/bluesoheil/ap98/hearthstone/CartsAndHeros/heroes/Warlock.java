package edu.sharif.student.bluesoheil.ap98.hearthstone.CartsAndHeros.heroes;

import edu.sharif.student.bluesoheil.ap98.hearthstone.CartsAndHeros.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Players.CardManagement;

import java.util.ArrayList;

public class Warlock extends Hero{

    private static Warlock warlock;
    public static Warlock getInstance(){
        warlock = new Warlock();
        warlock.setHP(35);
        return warlock;
    }

    /////////////these work with player total cards....we use methods below to eventually load cards of this hero's deck out of player's file
    @Override
    public void loadAllHeroCards() {
        allHeroCards=new ArrayList<>();
        for(Card card : CardManagement.getPlayerTotalCards()){
            if (card.getHeroClassName().equals("Warlock")){
                allHeroCards.add(card);
            }
        }
    }
    @Override
    public void loadAllNeutralCards() {
        allNeutralCards = new ArrayList<>();
        for(Card card : CardManagement.getPlayerTotalCards()){
            if (card.getHeroClassName().equals("Neutral")){
                allNeutralCards.add(card);
            }
        }
    }
    /////////these work with player deck cards
    @Override
    public void loadDeckHeroCarts() {
        deckHeroCarts = new ArrayList<>();
        for(Card card : CardManagement.getHeroesAllDeckCards().get("Warlock")){  //we have a loop in all cards in mage's deck
            if (card.getHeroClassName().equals("Warlock")){
                deckHeroCarts.add(card);
            }
        }
    }
    @Override
    public void loadDeckNeutralCards() {
        deckNeutralCards = new ArrayList<>();
        for(Card card : CardManagement.getHeroesAllDeckCards().get("Warlock")){ //we have a loop in all cards in mage's deck
            if (card.getHeroClassName().equals("Neutral")){
                deckNeutralCards.add(card);
            }
        }
    }



    @Override
    public ArrayList<Card> getDeckHeroCarts() {
        return deckHeroCarts;
    }
    @Override
    public ArrayList<Card> getDeckNeutralCards() {
        return deckNeutralCards;
    }
    @Override
    public ArrayList<Card> getAllNeutralCards(){
        return allNeutralCards;
    }
    public ArrayList<Card> getAllHeroCards(){
        return allHeroCards;
    }


    @Override
    public ArrayList<Card> getAllCards(){
        ArrayList<Card> allCards = new ArrayList<>(getAllHeroCards());
        allCards.addAll(getAllNeutralCards());
        return allCards;
    }
    @Override
    public ArrayList<Card> getDeckCards(){
        ArrayList<Card> deckCards = new ArrayList<>(getDeckHeroCarts());
        deckCards.addAll(getDeckNeutralCards());
        return deckCards;
    }
    @Override
    public ArrayList<Card> getUnusedCards(){

        ArrayList<Card> unusedHeroCards = new ArrayList<>();
        ArrayList<Card> unusedNeutralCards = new ArrayList<>();
        ArrayList<Card> copyList = new ArrayList<>();

        copyList = allHeroCards;      //we make a list of unused hero cards
        copyList.removeAll(deckHeroCarts);
        unusedHeroCards = copyList;

        copyList = allNeutralCards;    //we make a list of unused neutral cards
        copyList.removeAll(deckNeutralCards);
        unusedNeutralCards = copyList;

        ArrayList<Card> unusedCards = new ArrayList<>(unusedNeutralCards);  //we join two lists above as the unusedCards list
        unusedCards.addAll(unusedHeroCards);

        return unusedCards;
    }


    @Override
    public void addToDeck(String cardName) throws Exception {
        {
            cardName=cardName.toUpperCase();
            boolean heroHasThisCard = false;
            for (Card card : CardManagement.getCurrentHero().getAllCards()){
                if (card.getName().toUpperCase().equals(cardName)){
                    heroHasThisCard = true;
                    if (getDeckCards().contains(card)){
                        throw new Exception( CardManagement.getCurrentHero().getClass().getSimpleName()
                                +" already has "+cardName+" card");
                    }else {
                        if (card.getHeroClassName().equals("Neutral") ){
                            deckNeutralCards.add(card);
                            CardManagement.updateHeroesAllDeckCards("Neutral" , deckNeutralCards);

                        }else {
                            deckHeroCarts.add(card);
                            CardManagement.updateHeroesAllDeckCards("Warlock" , deckHeroCarts);

                        }
                    }
                }
            }
            if (! heroHasThisCard) { throw new Exception(CardManagement.getCurrentHero().getClass().getSimpleName()+
                    " hasn't this card"); }
        }
    }
    @Override
    public  void removeFromDeck(String cardName) throws Exception  {
        cardName=cardName.toUpperCase();
        boolean heroHasThisCard = false;
        for (Card card : CardManagement.getCurrentHero().getDeckCards()){
            if (card.getName().toUpperCase().equals(cardName)){
                heroHasThisCard = true;
                if (card.getHeroClassName().equals("Neutral") ){
                    deckNeutralCards.remove(card);
                }else {
                    deckHeroCarts.remove(card);
                }
            }
        }
        if (! heroHasThisCard) { throw new Exception(CardManagement.getCurrentHero().getClass().getSimpleName()+
                " hasn't this card in deck"); }

    }
}

