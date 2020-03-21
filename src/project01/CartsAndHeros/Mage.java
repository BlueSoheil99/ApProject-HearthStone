package project01.CartsAndHeros;

import project01.Players.CardManagement;

import java.util.ArrayList;

public class Mage extends Hero {

    private static Mage mage;
    public static Hero getInstance() {
         mage = new Mage();
         mage.setHP(30);
        return mage;
    }

/////////////these work with player total cards....we use methods below to eventually load cards of this hero's deck out of player's file
    @Override
    public void loadAllHeroCards() {
         allHeroCards=new ArrayList<>();
         for(Card card : CardManagement.getPlayerTotalCards()){
             if (card.getHeroClassName().equals("Mage")){
                 allHeroCards.add(card);
             }
         }
    }
    @Override
    public void loadAllNeutralCards() {
        for(Card card : CardManagement.getPlayerTotalCards()){
            if (card.getHeroClassName().equals("Neutral")){
                allNeutralCards.add(card);
            }
        }
    }
    /////////these work with player deck cards
    @Override
    public void loadDeckHeroCarts() {
        for(Card card : CardManagement.getHeroesAllDeckCards().get("Mage")){  //we have a loop in all cards in mage's deck
            if (card.getHeroClassName().equals("Mage")){
                deckHeroCarts.add(card);
            }
        }
    }
    @Override
    public void loadDeckNeutralCards() {
        for(Card card : CardManagement.getHeroesAllDeckCards().get("Mage")){ //we have a loop in all cards in mage's deck
            if (card.getHeroClassName().equals("Neutral")){
                deckNeutralCards.add(card);
            }
        }
    }



    @Override
    public ArrayList<Card> getDeckHeroCarts() {
            return Mage.deckHeroCarts;
     }
    @Override
    public ArrayList<Card> getDeckNeutralCards() {
        return Mage.deckNeutralCards;
    }
    @Override
    public ArrayList<Card> getAllNeutralCards(){
        return Mage.allNeutralCards;
    }
    public ArrayList<Card> getAllHeroCards(){
        return Mage.allHeroCards;
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
                        }else {
                            deckHeroCarts.add(card);
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
