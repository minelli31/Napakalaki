
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

    private void initMonsterCardDeck() {

        /**
         * VARIABLES
         */
        String         nameMonster;
        String         badCons;
        boolean        death;
        Prize          p;
        BadConsequence b;
        Monster        m;

        // **************************El Rey de Rosado***************************
        nameMonster = "El Rey de Rosado";
        badCons     = "Pierdes 5 niveles y 3 tesoros visibles.";
        death       = false;
        p           = new Prize(3, 2);
        b           = new BadConsequence(badCons, 5, 3, 0);
        m           = new Monster(nameMonster, 11, b, p);
        unusedMonsters.add(m);

        // ************************3 Byakhees de Bonanza************************
        nameMonster = "3 Byakhees de Bonanza";
        badCons     = "Pierdes tu armadura visible y otra oculta.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        m = new Monster(nameMonster, 8, b, p);
        unusedMonsters.add(m);

        // ************************Tenochtitlan*********************************
        nameMonster = "Tenochtitlan";
        badCons     = "Embobados con el lindo primigenio te descartas de tu casco visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ************************El Sopor de Dunwich**************************
        nameMonster = "El Sopor de Dunwich";
        badCons     = "El primordial bostezo contagioso. Pierdes el calzado visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.SHOE)), new ArrayList());
        m           = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ************************Demonios de Magaluf**************************
        nameMonster = "Demonios de Magaluf";
        badCons     =
            "Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.";
        death = false;
        p     = new Prize(4, 1);
        b     = new BadConsequence(badCons,
                                   0,
                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        m = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ************************El Gorrón en el Umbral***********************
        nameMonster = "El Gorrón en el Umbral";
        badCons     = "Pierdes todos tus tesoros visibles.";
        death       = false;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, 0, Integer.MAX_VALUE, 0);
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // ***************************H.P. Munchcraft***************************
        nameMonster = "H.P. Munchcraft";
        badCons     = "Pierdes la armadura visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // *****************************Necrófago*******************************
        nameMonster = "Necrófago";
        badCons     = "Sientes bichos bajo la ropa. Descarta la armadura visible.";
        death       = false;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // ******************************Flecher********************************
        nameMonster = "Flecher";
        badCons     = "Toses los pulmones y pierdes 2 niveles.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons, 2, 0, 0);
        m           = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ****************************Los Hondos*******************************
        nameMonster = "Los Hondos";
        badCons     = "Estos unusedMonsters resultan bastante superficiales y te aburren mortalmente. Estas muerto.";
        death       = true;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 8, b, p);
        unusedMonsters.add(m);

        // ***************************Semillas Cthulhu**************************
        nameMonster = "Semillas Cthulhu";
        badCons     = "Pierdes 2 niveles y 2 tesoros ocultos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 2, 0, 2);
        m           = new Monster(nameMonster, 4, b, p);
        unusedMonsters.add(m);

        // ******************************Dameargo*******************************
        nameMonster = "Dameargo";
        badCons     = "Te intentas escaquear. Pierdes una mano visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         0,
                                         new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 1, b, p);
        unusedMonsters.add(m);

        // ************************Polllipólipo Volante*************************
        nameMonster = "Polllipólipo Volante";
        badCons     = "Da mucho asquito. Pierdes 3 niveles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 3, 0, 0);
        m           = new Monster(nameMonster, 3, b, p);
        unusedMonsters.add(m);

        // ***************************Yskhtihyssg-Goth**************************
        nameMonster = "Yskhtihyssg-Goth";
        badCons     = "No le hace gracia que pronuncien mal su nombre. Estas muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 14, b, p);
        unusedMonsters.add(m);

        // ****************************Familia Feliz****************************
        nameMonster = "Familia Feliz";
        badCons     = "La familia te atrapa. Estáss muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = new BadConsequence(badCons, death);
        m           = new Monster(nameMonster, 1, b, p);
        unusedMonsters.add(m);

        // *****************************Roboggoth*******************************
        nameMonster = "Roboggoth";
        badCons     = "La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         2,
                                         new ArrayList(Arrays.asList(TreasureKind.BOTHHAND)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 8, b, p);
        unusedMonsters.add(m);

        // ****************************El Espía Sordo***************************
        nameMonster = "El Espía Sordo";
        badCons     = "Te asusta en la noche. Pierdes un casco visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = new BadConsequence(badCons,
                                         2,
                                         new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                         new ArrayList());
        m           = new Monster(nameMonster, 5, b, p);
        unusedMonsters.add(m);

        // ********************************Tongue*******************************
        nameMonster = "Tongue";
        badCons     = "Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons, 2, 5, 0);
        m           = new Monster(nameMonster, 19, b, p);
        unusedMonsters.add(m);

        // ****************************Bicéfalo*********************************
        nameMonster = "Bicéfalo";
        badCons     = "Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = new BadConsequence(badCons,
                                         3,
                                         new ArrayList(Arrays.asList(TreasureKind.ONEHAND,
                                                                     TreasureKind.ONEHAND,
                                                                     TreasureKind.BOTHHAND)),
                                         new ArrayList());
        m = new Monster(nameMonster, 21, b, p);
        unusedMonsters.add(m);
        shuffleMonsters();
    }

    private void initTreasureCardDeck() {
        unusedTreasures.add(new Treasure("¡Sí mi amo!", 0, 4, 7, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación", 600, 3, 4, TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 500, 3, 5, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 400, 2, 5, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 800, 1, 1, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Casco minero", 400, 2, 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora ACME", 600, 4, 8, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo", 700, 3, 5, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 500, 2, 5, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 500, 4, 5, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Insecticida", 300, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de tres cañones", 700, 4, 6, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Garabato místico", 300, 2, 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón", 800, 5, 7, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("La fuerza de Mr. T", 1000, -1, -1, TreasureKind.NECKLACE));
        unusedTreasures.add(new Treasure("La rebeca metálica", 400, 2, 3, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 200, 3, 4, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboycón", 300, 3, 5, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Lanzallamas", 800, 4, 8, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrocomicón", 100, 1, 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Linterna a dos manos", 400, 3, 6, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrognomicón", 200, 2, 4, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega", 200, 0, 1, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 500, 0, 1, TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Shogulador", 600, 1, 1, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.ONEHAND));
        shuffleTreasures();
    }

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
