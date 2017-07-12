
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import GUI.Dice;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author minelli
 */
public class Player {

    // Variables privadas
    public static final int       MAXLEVEL = 10;
    private boolean               dead;
    private boolean               canISteal;
    private String                name;
    private int                   level;
    protected ArrayList<Treasure> visibleTreasures;
    protected ArrayList<Treasure> hiddenTreasures;
    protected BadConsequence      pendingBadConsequence;
    protected Player              enemy;

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
        this.pendingBadConsequence = new NumericBadConsequence("", 0, 0, 0);
    }

    /////////////////////////////////////////////////////////////////
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

            for (int i = 0; i < nTreasures; i++) {
                this.hiddenTreasures.add(dealer.nextTreasure());
            }
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
                NECKLACE = 0,
                JOKER    = 0,
                THIEF    = 0;

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
            } else if (tr.getType() == TreasureKind.JOKER) {
                JOKER++;
            } else if (tr.getType() == TreasureKind.THIEF) {
                THIEF++;
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

        case JOKER :
            if (JOKER == 0) {
                canI = true;
            }

            break;

        case THIEF :
            if (THIEF == 0) {
                canI = true;
            }

            break;
        }

        return canI;
    }

    protected boolean canYouGiveMeATreasure() {
        if (this.hiddenTreasures.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public CombatResult combat(Monster m) {
        CombatResult comb         = null;
        int          myLevel      = this.getCombatLevel();
        int          monsterLevel = m.getLevel();

        System.out.println("********----------****combat principio-------********----------");
        System.out.println(myLevel);
        System.out.println(monsterLevel);
        System.out.println("********----------*****canISteal()-------********----------");
        System.out.println(canISteal());

        if (!this.canISteal()) {
            Dice dice   = Dice.getInstance();
            int  number = dice.nextNumber();

            System.out.println("********----------number********----------");
            System.out.println(number);

            if (number < 3) {

                // int enemyLevel = this.enemy.getCombatLevel();
                int enemyLevel = this.getOponentLevel(m);

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

            return comb;
        } else {
            this.applyBadConsequence(m);

            if (this.shouldConvert()) {
                comb = CombatResult.LOSEANDCONVERT;
            } else {
                comb = CombatResult.LOSE;
            }

            return comb;
        }
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

    public void discardAllTreasures() {
        ArrayList<Treasure> visible = new ArrayList(this.visibleTreasures);

        for (Treasure t : visible) {
            this.discardVisibleTreasure(t);
        }

        ArrayList<Treasure> hidden = new ArrayList(this.hiddenTreasures);

        for (Treasure t : hidden) {
            this.discardHiddenTreasure(t);
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

    protected Treasure giveMeATreasure() {
        Random rand = new Random();

        return this.hiddenTreasures.get(rand.nextInt(this.hiddenTreasures.size()));
    }

    protected Treasure giveMeATreasureVisible() {
        Random rand = new Random();

        return this.visibleTreasures.get(rand.nextInt(this.visibleTreasures.size()));
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
        Treasure   treasure;
        Dice       dice = Dice.getInstance();

        this.bringToLife();
        treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);

        int number = dice.nextNumber();

        switch (number) {
        case 1 :
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);

            break;

        case 6 : {
            int i = 0;

            while (i < 3) {
                treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
                i++;
            }

            break;
        }

        default : {
            int i = 0;

            while (i < 2) {
                treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
                i++;
            }

            break;
        }
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

    void receiveTreasures(ArrayList<Treasure> extraTreasures) {
        for (Treasure t : extraTreasures) {
            if (this.hiddenTreasures.size() > 4) {
                extraTreasures.add(t);
                this.hiddenTreasures.remove(t);
            }
        }
    }

    protected boolean shouldConvert() {

        // Lanzamos los dados, si es 6 return true
        Dice    dice   = Dice.getInstance();
        boolean should = false;

        if (dice.nextNumber() == 6) {
            should = true;
        }

        return should;
    }

    /////////////////////////////////////////////////
    public Treasure stealTreasure() {
        Treasure treasure = null;

        if (this.canISteal()) {
            if (enemy.canYouGiveMeATreasure()) {
                treasure = enemy.giveMeATreasure();
                enemy.discardHiddenTreasure(treasure);
                this.hiddenTreasures.add(treasure);
                this.haveStolen();
            }
        }

        return treasure;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tLevel: " + this.level;
    }

    public void useJoker() {
        this.setPendingBadConsequence(null);

        CardDealer dealer = CardDealer.getInstance();

        discardVisibleTreasure(dealer.getJokerCard());
    }

    public boolean validState() {
        boolean stateOK = false;

        if (this.pendingBadConsequence.isEmpty() && (this.hiddenTreasures.size() <= 4)) {
            stateOK = true;
        }

        return stateOK;
    }

    public int getCombatLevel() {
        System.out.println("********----------****this.level-----********----------");
        System.out.println(this.level);

        int combatLevel = this.level;

        // SUMAR TESOROS VISIBLES
        for (int i = 0; i < this.visibleTreasures.size(); i++) {
            combatLevel += this.visibleTreasures.get(i).getGainedLevels();
            System.out.println("visibleTreasures--->" + this.visibleTreasures.get(i).getGainedLevels());
        }

        System.out.println("********----------****combatLevel-----********----------");
        System.out.println(combatLevel);

        return combatLevel;
    }

    public boolean isDead() {
        return this.dead;
    }

    public Player getEnemy() {
        return this.enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    ArrayList<Treasure> getExtraTreasures() {
        ArrayList<Treasure> extraTreasures = new ArrayList();

        for (int i = 0; i < this.hiddenTreasures.size(); i++) {
            if (i > 4) {
                extraTreasures.add(this.hiddenTreasures.get(i));
            }
        }

        return extraTreasures;
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

    protected int getOponentLevel(Monster m) {
        return this.enemy.getCombatLevel();
    }

    public BadConsequence getPendingBadConsequence() {
        return pendingBadConsequence;
    }

    private void setPendingBadConsequence(BadConsequence b) {
        this.pendingBadConsequence = b;

        if (b == null) {
            pendingBadConsequence = new NumericBadConsequence("", 0, 0, 0);
        }
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }

    public void useThief() {
        //descarta todos los tesoros pero gana 5 niveles
        CardDealer dealer = CardDealer.getInstance();
        this.discardAllTreasures();
        this.incrementLevels(5);
        discardVisibleTreasure(dealer.getJokerCard());
    }
}
