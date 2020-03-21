package project01.CartsAndHeros;

import java.util.ArrayList;

public abstract class Hero {

      public static ArrayList<Card> allHeroCards;
      public static ArrayList<Card> allNeutralCards;
      public static ArrayList<Card> deckNeutralCards;
      public static ArrayList<Card> deckHeroCarts;
      private int HP;

      ////setter
      public void setHP(int HP) {
            this.HP = HP;
      }
      public int getHP() {
            return HP;
      }


      public abstract void loadAllHeroCards();  //this method runs after player and gameTotalCards are initialized
      public abstract void loadAllNeutralCards();  //this method runs after player and gameTotalCards are initialized
      public abstract void loadDeckHeroCarts();  //this method runs after setAllHeroCards
      public abstract void loadDeckNeutralCards(); //this method runs after setAllNeutralCards

      ////getter
      public abstract ArrayList<Card> getDeckHeroCarts();
      public abstract ArrayList<Card> getDeckNeutralCards() ;
      public abstract ArrayList<Card> getAllHeroCards();
      public abstract ArrayList<Card> getAllNeutralCards();
      public abstract ArrayList<Card> getAllCards();
      public abstract ArrayList<Card> getDeckCards();
      public abstract ArrayList<Card> getUnusedCards();


      ////methods
      public abstract void addToDeck(String cardName) throws Exception;
      public abstract void removeFromDeck(String cardName) throws Exception ;

}