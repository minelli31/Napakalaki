
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author minelli
 */
public class Treasure implements Card {
    private String       name;
    private int          goldCoins;
    private int          minBonus;
    private int          maxBonus;
    private TreasureKind type;

    public Treasure(String n, int g, int min, int max, TreasureKind tKind) {
        this.name      = n;
        this.goldCoins = g;
        this.minBonus  = min;
        this.maxBonus  = max;
        this.type      = tKind;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tTreasure kind: " + this.type;
    }

    @Override
    public int getBasicValue() {
        return this.getMinBonus();
    }

    public int getGoldCoins() {
        return this.goldCoins;
    }

    public int getMaxBonus() {
        return this.maxBonus;
    }

    public int getMinBonus() {
        return this.minBonus;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getSpecialValue() {
        return this.getMaxBonus();
    }

    public TreasureKind getType() {
        return this.type;
    }
}
