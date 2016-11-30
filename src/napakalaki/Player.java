
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

    private void applyBadConsequence(Monster m) {
        BadConsequence badConsequence = m.getBadConsequence();
        int            nLevels        = badConsequence.getLevels();

        this.decrementLevels(nLevels);
    }

    private void applyPrize(Monster m) {
        int nLevels = m.getLevelsGained();

        this.incrementLevels(nLevels);

        int nTreasures = m.getTreasuresGained();

        if (nTreasures > 0) {
            CardDealer dealer = CardDealer.getInstance();

            // for (Treasure tr : this.hiddenTreasures) {
            // this.hiddenTreasures.add(tr);
            // }
        }
    }

    private void bringToLife() {
        this.dead = false;
    }

    public boolean canISteal() {
        return this.canISteal;
    }

    private boolean canMakeTreasureVisible(Treasure t) {
        boolean canI     = false;
        int     ARMOR    = 0,
                ONEHAND  = 0,
                BOTHHAND = 0,
                HELMET   = 0,
                SHOE     = 0,
                NECKLACE = 0;

        for (Treasure tr : this.visibleTreasures) {
            if (tr.getType() == TreasureKind.ARMOR) {
                ARMOR++;
            } else if (tr.getType() == TreasureKind.ONEHAND) {
                ONEHAND++;
            } else if (tr.getType() == TreasureKind.BOTHHAND) {
                BOTHHAND++;
            } else if (tr.getType() == TreasureKind.HELMET) {
                HELMET++;
            } else if (tr.getType() == TreasureKind.SHOE) {
                SHOE++;
            } else if (tr.getType() == TreasureKind.NECKLACE) {
                NECKLACE++;
            }
        }

        switch (t.getType()) {
        case ARMOR :
            if (ARMOR == 0) {
                canI = true;
            }

            break;

        case ONEHAND :
            if ((ONEHAND < 2) && (BOTHHAND == 0)) {
                canI = true;
            }

            break;

        case BOTHHAND :
            if ((BOTHHAND == 0) && (ONEHAND == 0)) {
                canI = true;
            }

            break;

        case HELMET :
            if (HELMET == 0) {
                canI = true;
            }

            break;

        case SHOE :
            if (SHOE == 0) {
                canI = true;
            }

            break;

        case NECKLACE :
            if (NECKLACE == 0) {
                canI = true;
            }

            break;
        }

        return canI;
    }

    public boolean canYouGiveMeATreasure() {
        if (this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public CombatResult combat(Monster m) {
        CombatResult comb         = null;
        int          myLevel      = this.getCombatLevel();
        int          monsterLevel = m.getLevel();

        if (!this.canISteal()) {
            Dice dice   = Dice.getInstance();
            int  number = dice.nextNumber();

            if (number < 3) {
                int enemyLevel = enemy.getCombatLevel();

                monsterLevel += enemyLevel;
            }
        }

        if (myLevel > monsterLevel) {
            this.applyPrize(m);

            if (this.level >= MAXLEVEL) {
                comb = CombatResult.WINGAME;
            } else {
                comb = CombatResult.WIN;
            }
        } else {
            this.applyBadConsequence(m);
            comb = CombatResult.LOSE;
        }

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

    public void discardHiddenTreasure(Treasure t) {
        this.hiddenTreasures.remove(t);

        if ((this.pendingBadConsequence != null) &&!this.pendingBadConsequence.isEmpty()) {
            this.pendingBadConsequence.substractVisibleTreasure(t);
        }

        this.dieIfNoTreasures();
    }

    public void discardVisibleTreasure(Treasure t) {
        this.visibleTreasures.remove(t);

        if ((this.pendingBadConsequence != null) &&!this.pendingBadConsequence.isEmpty()) {
            this.pendingBadConsequence.substractVisibleTreasure(t);
        }

        this.dieIfNoTreasures();
    }

    public Treasure giveMeATreasure() {
        Random rand = new Random();

        return this.hiddenTreasures.get(rand.nextInt(this.hiddenTreasures.size() + 1));
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

    public void initTreasures() {
        CardDealer dealer = CardDealer.getInstance();
        Dice       dice   = Dice.getInstance();
        Treasure   treasure;
        int        number;

        this.bringToLife();
        treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);
        number = dice.nextNumber();

        if (number > 1) {
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }

        if (number == 6) {
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
    }

    public void makeTreasureVisible(Treasure t) {
        boolean canI = false;

        canI = this.canMakeTreasureVisible(t);

        if (canI) {
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }

    public Treasure stealTreasure() {
        Treasure treasure = null;

        if (this.canISteal()) {}

        return treasure;
    }

    public void substractHiddenTreasure(Treasure t) {}

    public void substractVisibleTreasure(Treasure t) {}

    @Override
    public String toString() {
        return "Name: " + this.name + "\tLevel: " + this.level;
    }

    public boolean validState() {
        boolean stateOK = false;

        if (this.pendingBadConsequence.isEmpty() && (this.hiddenTreasures.size() <= 4)) {
            stateOK = true;
        }

        return stateOK;
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
