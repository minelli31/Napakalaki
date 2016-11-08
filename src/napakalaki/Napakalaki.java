
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

    // Atributos de referencia
    private Player            currentPlayer;
    private ArrayList<Player> players;
    private CardDealer        dealer;
    private Monster           currentMonster;

    private Napakalaki() {}

    public static CombatResult developCombat() {
        CombatResult comb = null;

        return comb;
    }

    public static void discardHiddenTreasures(ArrayList<Treasure> treasure) {}

    public static void discardVisibleTreasures(ArrayList<Treasure> treasure) {}

    public static boolean endOfGamen(CombatResult result) {
        return true;
    }

    private void initGame(ArrayList<String> players) {}

    private void initPlayers(ArrayList<String> names) {}

    public static void makeTreasuresVisible(ArrayList<Treasure> treasure) {}

    private Player nextPlayer() {
        Player next = null;

        this.currentPlayer = next;

        return this.currentPlayer;
    }

    public static boolean nextTurn() {
        return true;
    }

    private boolean nextTurnAllowed() {
        return true;
    }

    public static Monster getCurrentMonster(ArrayList<String> names) {
        Monster current = null;

        return current;
    }

    public static Player getCurrentPLayer(ArrayList<String> names) {
        Player current = null;

        return current;
    }

    private void setEnemies() {}

    public static Napakalaki getInstance() {
        return instance;
    }
}
