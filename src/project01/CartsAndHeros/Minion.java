package project01.CartsAndHeros;

public class Minion extends Card {
    private int HP;
    private int attack;

    Minion(String name, int manaCost, Rarity rarity, HeroClass heroClass, String description , int attack , int hp , int cost) {
        super(name, manaCost, rarity, heroClass, description , cost);
        setHP(hp);
        setAttack(attack);
        saveCard();
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
