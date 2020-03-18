package project01.CartsAndHeros;

enum Rarity  {Common , Rare, Epic , Legendary}
enum HeroClass { Mage , Rogue , Warlock , Natural}

public abstract class Cart {
//properties
    protected int mana;
    protected String name;
    protected Rarity rarity;
    protected HeroClass heroClass;
    protected String description;
//setter
    protected void setName(String name) {
        this.name = name;
    }
    protected void setMana(int mana) {
        this.mana = mana;
    }
    protected void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
    protected void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
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
    public HeroClass getHeroClass() {
        return heroClass;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public String getDescription() {
        return description;
    }

    Cart(String name , int mana , HeroClass heroClass, Rarity rarity){
        setName(name);
        setMana(mana);
        setRarity(rarity);
        setHeroClass(heroClass);


    }
//methods
    public abstract void attack(Cart underAttackCart);
    public abstract void attack(Hero underAttackHero);
}

interface attackable{
    void attack();
}

interface dsflksdf{

}
