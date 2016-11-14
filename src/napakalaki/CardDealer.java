
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
    private ArrayList <Monster> unusedMonsters;
    private ArrayList <Monster> usedMonsters;
    private ArrayList <Treasure> unusedTreasures;
    private ArrayList <Treasure> usedTreasures;;

    private CardDealer() {
        this.unusedMonsters = new ArrayList<Monster>();
        this.unusedTreasures = new ArrayList<Treasure>();
        this.usedMonsters = new ArrayList<Monster>();
        this.usedTreasures = new ArrayList<Treasure>();
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

    private void shuffleMonsters() {
        Collections.shuffle(unusedMonsters);
    }

    private void shuffleTreasures() {
        Collections.shuffle(unusedTreasures);
    }
}
