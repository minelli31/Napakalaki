
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author minelli
 */
public class Player {

    // Variables privadas
    public static final int     MAXLEVEL = 10;
    private boolean             dead;
    private boolean             canISteal;
    private String              name;
    private int                 level;
    private ArrayList<Treasure> visibleTreasures;
    private ArrayList<Treasure> hiddenTreasures;
    private BadConsequence      pendingBadConsequence;
    private Player              enemy;

    // Constructor de copia
    public Player(Player p) {
        this.name                  = p.name;
        this.dead                  = p.dead;
        this.level                 = p.level;
        this.hiddenTreasures       = p.hiddenTreasures;
        this.visibleTreasures      = p.visibleTreasures;
        this.pendingBadConsequence = p.pendingBadConsequence;
    }

    // Constructor
    public Player(String name) {
        this.name                  = name;
        this.dead                  = true;
        this.canISteal             = true;
        this.level                 = 1;
        this.hiddenTreasures       = new ArrayList<Treasure>();
        this.visibleTreasures      = new ArrayList<Treasure>();
        this.pendingBadConsequence = new BadConsequence("", 0, 0, 0);
    }

    private void applyBadConsequence(Monster m) {}

    private void applyPrize(Monster m) {}

    private void bringToLife() {
        this.dead = false;
    }

    public boolean canISteal() {
        return this.canISteal;
    }

    private boolean canMakeTreasureVisible(Treasure t) {
        return true;
    }

    public boolean canYouGiveMeATreasure() {
        if (this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public CombatResult combat(Monster m) {
        CombatResult comb = null;

        return comb;
    }

    private void decrementLevels(int l) {
        this.level -= l;

        if (this.level < 1) {
            this.level = 1;
        }
    }

    private void dieIfNoTreasures() {
        if (this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()) {
            this.dead = true;
        }
    }

    public void haveStolen() {
        if (canISteal()) {
            this.canISteal = false;
        }
    }

    private int howManyHiddenTreasures(TreasureKind tKind) {
        int howMany = 0;

        for (Treasure t : this.hiddenTreasures) {
            if (t.getType() == tKind) {
                howMany += 1;
            }
        }

        return howMany;
    }

    private int howManyVisibleTreasures(TreasureKind tKind) {
        int howMany = 0;

        for (Treasure t : this.visibleTreasures) {
            if (t.getType() == tKind) {
                howMany += 1;
            }
        }

        return howMany;
    }

    private void incrementLevels(int l) {
        this.level += l;

        if (this.level > 10) {
            this.level = 10;
        }
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tLevel: " + this.level;
    }

    public boolean validState() {
        boolean valid = false;

        if (this.pendingBadConsequence.isEmpty() && (this.hiddenTreasures.size() <= 4)) {
            valid = true;
        }

        return valid;
    }

    private int getCombatLevel() {
        int     combatLevel    = this.level;
        boolean collarEquipado = false;

        for (int i = 0; (i < this.visibleTreasures.size()) &&!collarEquipado; i++) {
            if (this.visibleTreasures.get(i).getType() == TreasureKind.NECKLACE) {
                collarEquipado = true;
            }
        }

        if (collarEquipado) {
            for (int i = 0; i < this.visibleTreasures.size(); i++) {
                combatLevel += this.visibleTreasures.get(i).getMaxBonus();
            }
        } else {
            for (int i = 0; i < this.visibleTreasures.size(); i++) {
                combatLevel += this.visibleTreasures.get(i).getMinBonus();
            }
        }

        return combatLevel;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return this.hiddenTreasures;
    }

    public int getLevels() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    private void setPendingBadConsequence(BadConsequence b) {
        this.pendingBadConsequence = b;
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }
}
