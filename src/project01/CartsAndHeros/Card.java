package project01.CartsAndHeros;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.PrintWriter;



public  class Card {
    enum Rarity  {Common , Rare, Epic , Legendary}
    public enum HeroClass { Mage , Rogue , Warlock , Neutral}

//properties
    private String name;
    private  int manaCost;
    private String type ;
    private HeroClass heroClass;
    private Rarity rarity;
    private String description;
    private int cost;

    Card(String name , int manaCost , Rarity rarity, HeroClass heroClass , String description, int cost){
        setName(name);
        setManaCost(manaCost);
        type = getClass().getSimpleName();
        setHeroClass(heroClass);
        setRarity(rarity);
        setDescription(description);
        setCost(cost);
        saveCard();
    }


    //setter
    protected void setName(String name) {
        this.name = name;
    }
    protected void setManaCost(int mana) {
        this.manaCost = mana;
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
    private void setCost(int cost) {
        this.cost = cost;
    }


    //getter
    public int getManaCost() {
        return manaCost;
    }
    public String getName() {
        return name;
    }
    public String getHeroClassName() {
        return heroClass.name();
    }
    public Rarity getRarity() {
        return rarity;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public int getCost() {
        return cost;
    }

//methods
    void saveCard() {
        try {
            FileWriter writer = new FileWriter("src/Data/Cards/"+name+".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintWriter printer = new PrintWriter(writer);
            printer.println(gson.toJson(this));
            printer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
}

//    public abstract void attack(Card underAttackCard);
//    public abstract void attack(Hero underAttackHero);

}




//class CartMaker{
//    public static void main(String[] args) {
//        Card FriendlySmith= new Weapon("Friendly Smith" , 1 , Card.Rarity.Common , Card.HeroClass.Rogue, "N/A",3 );
//        Card mole= new Weapon("Mole" , 5 , Card.Rarity.Legendary , Card.HeroClass.Rogue, "N/A",4 );
//        Card Polymorph= new Spell("Polymorph" , 4 , Card.Rarity.Rare , Card.HeroClass.Mage, "N/A" ,3);
//        Card ArcaneExplosion= new Weapon("Arcane explosion" , 2 , Card.Rarity.Common , Card.HeroClass.Mage, "N/A"  , 3);
//        Card Dreadscale= new Minion("Dreadscale" , 3 , Card.Rarity.Legendary , Card.HeroClass.Warlock, "N/A",4 , 2,3);
//        Card concreteShield= new Spell("Concrete Shield" , 3 , Card.Rarity.Epic , Card.HeroClass.Warlock, "N/A",3 );
//
//        Card ShadowMadness = new Weapon("Shadow Madness" , 1 , Card.Rarity.Common , Card.HeroClass.Neutral, "N/A",2 );
//        Card Supercollider  = new Weapon("SuperCollider" , 5 , Card.Rarity.Epic , Card.HeroClass.Neutral, "N/A",2 );
//        Card Gorehowl  = new Weapon("Gorehowl" , 7 , Card.Rarity.Epic , Card.HeroClass.Neutral, "N/A",4 );
//
//        Card zilliax = new Minion("zilliax" , 5 , Card.Rarity.Legendary , Card.HeroClass.Neutral, "N/A",3 , 2,2);
//        Card Abomination = new Minion("Abomination" , 4 , Card.Rarity.Rare , Card.HeroClass.Neutral, "N/A",4,4,3 );
//        Card OmegaMedic  = new Minion("Omega Medic" , 3 , Card.Rarity.Rare , Card.HeroClass.Neutral, "N/A",3 , 4,2);
//        Card MurlocTidehunter= new Minion("Murloc Tidehunter" , 2 , Card.Rarity.Common , Card.HeroClass.Neutral, "N/A" ,2,1,1);
//        Card Waterboy = new Minion("Waterboy" , 2 , Card.Rarity.Rare , Card.HeroClass.Neutral, "N/A" ,2,1,2);
//        Card VoodooDoctor = new Minion("Voodoo doctor" , 1 , Card.Rarity.Common , Card.HeroClass.Neutral, "N/A",2,1,2 );
//
//        Card Consecration = new Spell("Consecration" , 4 , Card.Rarity.Rare , Card.HeroClass.Neutral, "N/A",2 );
//        Card Stormhammer = new Spell("Stormhammer" , 3 , Card.Rarity.Epic , Card.HeroClass.Neutral, "N/A",4 );
//        Card Slam  = new Spell("Slam" , 2 , Card.Rarity.Common , Card.HeroClass.Neutral, "N/A",1);
//        Card Humility   = new Spell("Humility" , 1 , Card.Rarity.Common , Card.HeroClass.Neutral, "N/A",2);
//        Card Innervate = new Spell("Innervate" , 0 , Card.Rarity.Rare , Card.HeroClass.Neutral, "N/A"  , 2);
//
//
//
//
//
//    }
//
//}
