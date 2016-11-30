
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author minelli
 */
public class CardDealer {
    private static final CardDealer instance = new CardDealer();
    private ArrayList<Monster>      unusedMonsters;
    private ArrayList<Monster>      usedMonsters;
    private ArrayList<Treasure>     unusedTreasures;
    private ArrayList<Treasure>     usedTreasures;;

    private CardDealer() {
        this.unusedMonsters  = new ArrayList<Monster>();
        this.unusedTreasures = new ArrayList<Treasure>();
        this.usedMonsters    = new ArrayList<Monster>();
        this.usedTreasures   = new ArrayList<Treasure>();
    }

    public void giveMonsterBack(Monster m) {
        if (!usedMonsters.contains(m)) {
            usedMonsters.add(m);
        }
    }

    public void giveTreasureBack(Treasure t) {
        if (!usedTreasures.contains(t)) {
            usedTreasures.add(t);
        }
    }

    public void initCards() {
        this.initTreasureCardDeck();
        this.initMonsterCardDeck();
    }

    private void initMonsterCardDeck() {}

    private void initTreasureCardDeck() {}

    public Monster nextMonster() {
        if (this.unusedMonsters.isEmpty()) {
            for (Monster m : this.usedMonsters) {
                this.unusedMonsters.add(m);
            }

            this.shuffleMonsters();
            this.usedMonsters.clear();
        }

        Monster nextM = this.unusedMonsters.get(0);

        this.unusedMonsters.remove(nextM);
        this.usedMonsters.add(nextM);

        return nextM;
    }

    public Treasure nextTreasure() {
        if (this.unusedTreasures.isEmpty()) {
            for (Treasure t : this.usedTreasures) {
                this.unusedTreasures.add(t);
            }

            this.shuffleTreasures();
            this.usedTreasures.clear();
        }

        Treasure nextT = this.unusedTreasures.get(0);

        this.unusedTreasures.remove(nextT);
        this.usedTreasures.add(nextT);

        return nextT;
    }

    private void shuffleMonsters() {
        Collections.shuffle(unusedMonsters);
    }

    private void shuffleTreasures() {
        Collections.shuffle(unusedTreasures);
    }

    public static CardDealer getInstance() {
        return instance;
    }
}
