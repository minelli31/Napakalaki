
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
    private int          lv;
    private TreasureKind type;

    public Treasure(String n, int lv, TreasureKind tKind) {
        this.name = n;
        this.lv   = lv;
        this.type = tKind;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tTreasure kind: " + this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getGainedLevels() {
        return this.lv;
    }

    @Override
    public int getCombatLevelAgainstCultistPlayer() {
        return this.getGainedLevels() * CultistPlayer.getTotalCultistPlayers();
    }

    public TreasureKind getType() {
        return this.type;
    }
}
