package project01.CartsAndHeros;

public abstract class Carts {
    private int Mana;
    private String name;
    private Rarity cartRarity;
    private Classes cartClass;
    private String description;

    public abstract void attack();
}

enum Rarity{Common , Rare, Epic , Legendary}
enum Classes { Mage , Rogue , Warlock , Natural}


class Spell extends Carts{


    @Override
    public void attack() {

    }
}

class Minion extends Carts{
    private int HP;

    @Override
    public void attack() {

    }
}

class Weapon extends Carts{


    @Override
    public void attack() {

    }
}

class Mission extends Carts{

    @Override
    public void attack() {

    }
}
