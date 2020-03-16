package project01.CartsAndHeros;

enum Rarity  {Common , Rare, Epic , Legendary}
enum CartClasses { Mage , Rogue , Warlock , Natural}

public abstract class Cart {
//properties
    protected int mana;
    protected String name;
    protected Rarity cartRarity;
    protected CartClasses cartClass;
    protected String description;
//setter
    protected void setName(String name) {
        this.name = name;
    }
    protected void setMana(int mana) {
        this.mana = mana;
    }
    protected void setCartRarity(Rarity cartRarity) {
        this.cartRarity = cartRarity;
    }
    protected void setCartClass(CartClasses cartClass) {
        this.cartClass = cartClass;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
//getter
    public int getMana() {
        return mana;
    }
    public String getName() {
        return name;
    }
    public CartClasses getCartClass() {
        return cartClass;
    }
    public Rarity getCartRarity() {
        return cartRarity;
    }
    public String getDescription() {
        return description;
    }
//methods
    public abstract void attack(Cart underAttackCart);
    public abstract void attack(Hero underAttackHero);

}




class Spell extends Cart {

    private Spell(int mana , Rarity cartRarity , CartClasses cartClass){
        setMana(mana);
        setCartClass(cartClass);
        setCartRarity(cartRarity);
    }
    private Spell(int mana , Rarity cartRarity , CartClasses cartClass , String description){
        this( mana,cartRarity,cartClass);
        setDescription(description);
    }



    @Override
    public void attack(Cart underAttackCart) {

    }

    @Override
    public void attack(Hero underAttackHero) {

    }
}

class Minion extends Cart {
    private int HP;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }


    @Override
    public void attack(Cart underAttackCart) {

    }

    @Override
    public void attack(Hero underAttackHero) {

    }
}

class Weapon extends Cart {


    @Override
    public void attack(Cart underAttackCart) {

    }

    @Override
    public void attack(Hero underAttackHero) {

    }
}

class Mission extends Cart {

    @Override
    public void attack(Cart underAttackCart) {

    }

    @Override
    public void attack(Hero underAttackHero) {

    }
}
