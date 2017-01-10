
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author minelli
 */

/**
 * Variable de instancia
 *
 * En programación orientada a objetos, una variable de instancia o miembro de dato
 * es una variable que se relaciona con una única instancia de una clase.
 *
 * Cada vez que se crea un objeto, el sistema crea una copia de todas las variables
 * que están vinculadas con dicha clase, haciéndolas propias de esa instancia.
 * Solamente se puede acceder a ellas a través del identificador del objeto.
 *
 * Estas variables son declaradas fuera del cuerpo de los métodos y dentro de la
 * clase, por lo tanto son de tipo global. Por ende, pueden ser utilizadas por
 * cualquier método no estático de dicha clase.
 */

/**
 * Variable de clase
 *
 * En programación orientada a objetos, una variable de clase o miembro de dato
 * estático es una variable, al contrario que las variable de instancia, propia
 * de la clase que la contiene, y no de instancias de la misma, esto quiere decir
 * que todos los objetos que se creen de esta clase comparten su valor, son
 * llamadas variables estáticas.
 */
public class Napakalaki {

    // Variables privadas
    private static final Napakalaki instance = new Napakalaki();
    private CardDealer              dealer   = CardDealer.getInstance();
    private Dice                    dice;

    // Atributos de referencia
    private Player            currentPlayer;
    private ArrayList<Player> players;
    private Monster           currentMonster;
    private int               nextPlayerIndex;

    private Napakalaki() {
        this.nextPlayerIndex = -1;
        dice                 = Dice.getInstance();
    }

    public CombatResult developCombat() {
        System.out.println("********----------****developCombat----********----------");

        CombatResult comb = this.currentPlayer.combat(currentMonster);

        System.out.println("comb " + comb);
        this.dealer.giveMonsterBack(this.currentMonster);

        if (comb == CombatResult.LOSEANDCONVERT) {
            CultistPlayer c = new CultistPlayer(this.currentPlayer, this.dealer.nextCultist());

            this.currentPlayer = c;
            this.players.set(this.nextPlayerIndex, c);
        }

        return comb;
    }

    public void discardAllTreasures() {}

    public void discardHiddenTreasures(ArrayList<Treasure> treasure) {
        for (Treasure tr : treasure) {
            this.currentPlayer.discardHiddenTreasure(tr);
            this.dealer.giveTreasureBack(tr);
        }
    }

    public void discardVisibleTreasures(ArrayList<Treasure> treasure) {
        for (Treasure tr : treasure) {
            this.currentPlayer.discardVisibleTreasure(tr);
            this.dealer.giveTreasureBack(tr);
        }
    }

    public static boolean endOfGame(CombatResult result) {
        return (result == CombatResult.WINGAME);
    }

    public void initGame(ArrayList<String> players) {
        this.initPlayers(players);
        this.setEnemies();
        dealer.initCards();
        this.nextTurn();
    }

    private void initPlayers(ArrayList<String> names) {
        this.players = new ArrayList<Player>();

        for (String name : names) {
            Player p = new Player(name);

            this.players.add(p);
        }
    }

    public void makeTreasuresVisible(ArrayList<Treasure> treasure) {
        for (Treasure tr : treasure) {
            this.currentPlayer.makeTreasureVisible(tr);
        }
    }

    private Player nextPlayer() {
        if (this.nextPlayerIndex == -1) {
            Random rand = new Random();

            this.nextPlayerIndex = rand.nextInt(this.players.size());
        } else {
            this.nextPlayerIndex = (this.nextPlayerIndex + 1) % this.players.size();
        }

        return this.currentPlayer = this.players.get(this.nextPlayerIndex);
    }

    public boolean nextTurn() {
        boolean stateOK = this.nextTurnAllowed();

        if (stateOK) {
            this.currentMonster = dealer.nextMonster();
            this.currentPlayer  = this.nextPlayer();

            if (this.currentPlayer.isDead()) {
                this.currentPlayer.initTreasures();
            }
        }

        return stateOK;
    }

    private boolean nextTurnAllowed() {
        return (this.currentPlayer == null)
               ? true
               : this.currentPlayer.validState();
    }

    public Monster getCurrentMonster() {
        return this.currentMonster;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    private void setEnemies() {
        Player enemy;

        for (Player p : this.players) {
            do {
                Random rand = new Random();

                enemy = this.players.get(rand.nextInt(this.players.size()));
            } while (enemy == p);

            p.setEnemy(enemy);
        }
    }

    public static Napakalaki getInstance() {
        return instance;
    }
}
