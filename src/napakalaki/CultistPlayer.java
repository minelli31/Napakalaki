
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author minelli
 */
public class CultistPlayer extends Player {
    private static int totalCultistPlayers = 0;
    private Cultist    myCultistCard;

    public CultistPlayer(Player p, Cultist c) {
        super(p);
        this.myCultistCard = c;
        this.totalCultistPlayers++;
    }

    @Override
    protected boolean canYouGiveMeATreasure() {
        if (this.enemy.getVisibleTreasures().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected Treasure giveMeATreasure() {
        Random rand = new Random();

        return this.getVisibleTreasures().get(rand.nextInt(this.getVisibleTreasures().size()));
    }

    protected boolean shouldConvert() {
        return false;
    }

    @Override
    public int getCombatLevel() {
        int combat = super.getCombatLevel() + (int) (super.getCombatLevel() * 0.7);

        combat += (int) (myCultistCard.getGainedLevels() * this.totalCultistPlayers);

        return combat;
    }

    public Cultist getCultilist() {
        return this.myCultistCard;
    }

    @Override
    public Player getEnemy() {
        return super.getEnemy();
    }

    protected int getOponentLevel(Monster m) {
        return m.getCombatLevelAgainstCultistPlayer();
    }

    static int getTotalCultistPlayers() {
        return CultistPlayer.totalCultistPlayers;
    }
}
