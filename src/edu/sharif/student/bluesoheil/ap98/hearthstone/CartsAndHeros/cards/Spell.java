package edu.sharif.student.bluesoheil.ap98.hearthstone.CartsAndHeros.cards;

public class Spell extends Card {
    int value;

    Spell(String name, int manaCost, Rarity rarity, HeroClass heroClass, String description , int cost){
        super(name , manaCost , rarity , heroClass, description , cost);
        saveCard();

    }




}

