
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
    private ArrayList<Cultist>      unusedCultists;
    private ArrayList<Cultist>      usedCultists;
    
    private Treasure jokerCard = new Treasure("El Joker", 0,TreasureKind.JOKER);

    private CardDealer() {
        this.unusedMonsters  = new ArrayList<Monster>();
        this.unusedTreasures = new ArrayList<Treasure>();
        this.unusedCultists  = new ArrayList<Cultist>();
        this.usedMonsters    = new ArrayList<Monster>();
        this.usedTreasures   = new ArrayList<Treasure>();
        this.usedCultists    = new ArrayList<Cultist>();
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
        this.initCultistCardDeck();
    }

    private void initCultistCardDeck() {

        // cartas sectarios
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       1));
        unusedCultists.add(new Cultist("Sectario: +2 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       2));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       1));
        unusedCultists.add(new Cultist("Sectario: +2 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       2));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       1));
        unusedCultists.add(new Cultist("Sectario: +1 por cada sectari en juego\n" + "No puedes dejar de ser sectario",
                                       1));
        shuffleCultists();
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
        int            cult;

        // **************************El Rey de Rosado***************************
        nameMonster = "El Rey de Rosado";
        badCons     = "Pierdes 5 niveles y 3 tesoros visibles.";
        death       = false;
        p           = new Prize(3, 2);
        b           = BadConsequenceFactory.create(badCons, 5, 3, 0);
        m           = new Monster(nameMonster, 11, b, p);
        unusedMonsters.add(m);

        // ************************3 Byakhees de Bonanza************************
        nameMonster = "3 Byakhees de Bonanza";
        badCons     = "Pierdes tu armadura visible y otra oculta.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons,
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
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                                   new ArrayList());
        m = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ************************El Sopor de Dunwich**************************
        nameMonster = "El Sopor de Dunwich";
        badCons     = "El primordial bostezo contagioso. Pierdes el calzado visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.SHOE)),
                                                   new ArrayList());
        m           = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ************************Demonios de Magaluf**************************
        nameMonster = "Demonios de Magaluf";
        badCons     =
            "Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.";
        death = false;
        p     = new Prize(4, 1);
        b     = BadConsequenceFactory.create(badCons,
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
        b           = BadConsequenceFactory.create(badCons, 0, Integer.MAX_VALUE, 0);
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // ***************************H.P. Munchcraft***************************
        nameMonster = "H.P. Munchcraft";
        badCons     = "Pierdes la armadura visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                                                   new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // *****************************Necrófago*******************************
        nameMonster = "Necrófago";
        badCons     = "Sientes bichos bajo la ropa. Descarta la armadura visible.";
        death       = false;
        p           = new Prize(3, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                                                   new ArrayList());
        m           = new Monster(nameMonster, 13, b, p);
        unusedMonsters.add(m);

        // ******************************Flecher********************************
        nameMonster = "Flecher";
        badCons     = "Toses los pulmones y pierdes 2 niveles.";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons, 2, 0, 0);
        m           = new Monster(nameMonster, 2, b, p);
        unusedMonsters.add(m);

        // ****************************Los Hondos*******************************
        nameMonster = "Los Hondos";
        badCons     = "Estos unusedMonsters resultan bastante superficiales y te aburren mortalmente. Estas muerto.";
        death       = true;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 0, 0, 0, death);
        m           = new Monster(nameMonster, 8, b, p);
        unusedMonsters.add(m);

        // ***************************Semillas Cthulhu**************************
        nameMonster = "Semillas Cthulhu";
        badCons     = "Pierdes 2 niveles y 2 tesoros ocultos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 2, 0, 2);
        m           = new Monster(nameMonster, 4, b, p);
        unusedMonsters.add(m);

        // ******************************Dameargo*******************************
        nameMonster = "Dameargo";
        badCons     = "Te intentas escaquear. Pierdes una mano visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                                   new ArrayList());
        m = new Monster(nameMonster, 1, b, p);
        unusedMonsters.add(m);

        // ************************Polllipólipo Volante*************************
        nameMonster = "Polllipólipo Volante";
        badCons     = "Da mucho asquito. Pierdes 3 niveles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 3, 0, 0);
        m           = new Monster(nameMonster, 3, b, p);
        unusedMonsters.add(m);

        // ***************************Yskhtihyssg-Goth**************************
        nameMonster = "Yskhtihyssg-Goth";
        badCons     = "No le hace gracia que pronuncien mal su nombre. Estas muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = BadConsequenceFactory.create(badCons, 0, 0, 0, death);
        m           = new Monster(nameMonster, 14, b, p);
        unusedMonsters.add(m);

        // ****************************Familia Feliz****************************
        nameMonster = "Familia Feliz";
        badCons     = "La familia te atrapa. Estáss muerto.";
        death       = true;
        p           = new Prize(3, 1);
        b           = BadConsequenceFactory.create(badCons, 0, 0, 0, death);
        m           = new Monster(nameMonster, 1, b, p);
        unusedMonsters.add(m);

        // *****************************Roboggoth*******************************
        nameMonster = "Roboggoth";
        badCons     = "La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   2,
                                                   new ArrayList(Arrays.asList(TreasureKind.BOTHHAND)),
                                                   new ArrayList());
        m = new Monster(nameMonster, 8, b, p);
        unusedMonsters.add(m);

        // ****************************El Espía Sordo***************************
        nameMonster = "El Espía Sordo";
        badCons     = "Te asusta en la noche. Pierdes un casco visible.";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   2,
                                                   new ArrayList(Arrays.asList(TreasureKind.HELMET)),
                                                   new ArrayList());
        m = new Monster(nameMonster, 5, b, p);
        unusedMonsters.add(m);

        // ********************************Tongue*******************************
        nameMonster = "Tongue";
        badCons     = "Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 2, 5, 0);
        m           = new Monster(nameMonster, 19, b, p);
        unusedMonsters.add(m);

        // ****************************Bicéfalo*********************************
        nameMonster = "Bicéfalo";
        badCons     = "Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   3,
                                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND,
                                                                               TreasureKind.ONEHAND,
                                                                               TreasureKind.BOTHHAND)),
                                                   new ArrayList());
        m = new Monster(nameMonster, 21, b, p);
        unusedMonsters.add(m);

        /** ******************************************************************************************* */

        /* MONSTRUOS CON SECTARIOS */

        /** ******************************************************************************************* */

        // ****************************El mal indecible impronunciable*********************************
        nameMonster = "El mal indecible impronunciable";
        badCons     = "Pierdes 1 mano visible";
        death       = false;
        p           = new Prize(3, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                                   new ArrayList());
        cult = -2;
        m    = new Monster(nameMonster, 21, b, p, cult);
        unusedMonsters.add(m);

        // ****************************Testigos Oculares*********************************
        nameMonster = "Testigos Oculares";
        badCons     = "Pierdes tus tesoros visible. MUA-HA-HA";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 0, Integer.MAX_VALUE, 0);
        cult        = 2;
        m           = new Monster(nameMonster, 21, b, p, cult);
        unusedMonsters.add(m);

        // ****************************El gran cthulhu*********************************
        nameMonster = "El gran cthulhu";
        badCons     = "Hoy no es tu día de suerte. Mueres.";
        death       = true;
        p           = new Prize(2, 5);
        b           = BadConsequenceFactory.create(badCons, 0, 0, 0, death);
        cult        = 4;
        m           = new Monster(nameMonster, 20, b, p, cult);
        unusedMonsters.add(m);

        // ******************************Serpiente Político********************************
        nameMonster = "Serpiente Político";
        badCons     = "Tu gobierno te recorta 2 niveles.";
        death       = false;
        p           = new Prize(2, 1);
        b           = BadConsequenceFactory.create(badCons, 2, 0, 0);
        cult        = -2;
        m           = new Monster(nameMonster, 8, b, p, cult);
        unusedMonsters.add(m);

        // ****************************Felpuggoth*********************************
        nameMonster = "Felpuggoth";
        badCons     = "Pierdes tucasco y tu armadura visible. Pierdes tus manos ocultas";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons,
                                                   0,
                                                   new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                                                   new ArrayList());
        cult = 5;
        m    = new Monster(nameMonster, 2, b, p, cult);
        unusedMonsters.add(m);

        // ******************************Shoggoth********************************
        nameMonster = "Shoggoth";
        badCons     = "Pierdes 2 niveles.";
        death       = false;
        p           = new Prize(4, 2);
        b           = BadConsequenceFactory.create(badCons, 2, 0, 0);
        cult        = -4;
        m           = new Monster(nameMonster, 16, b, p, cult);
        unusedMonsters.add(m);

        // ******************************Lolitagooth********************************
        nameMonster = "Lolitagooth";
        badCons     = "Pintalabios negro. Pierdes 2 niveles.";
        death       = false;
        p           = new Prize(1, 1);
        b           = BadConsequenceFactory.create(badCons, 2, 0, 0);
        cult        = 3;
        m           = new Monster(nameMonster, 2, b, p, cult);
        unusedMonsters.add(m);
        shuffleMonsters();
    }

    private void initTreasureCardDeck() {
        unusedTreasures.add(new Treasure("¡Sí mi amo!", 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación", 3, TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 1, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora ACME", 4, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Camiseta de la ETSIIT", 1, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de tres cañones", 4, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Garabato místico", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metálica", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necronomicón", 5, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrocomicón", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Linterna a dos manos", 3, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrognomicón", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboycón", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOE));
        
        shuffleTreasures();
        unusedTreasures.add(0, jokerCard);
    }
    
    public Treasure getJokerCard(){
        return this.jokerCard;
    }
    
    public Cultist nextCultist() {
        if (this.unusedCultists.isEmpty()) {
            for (Cultist c : this.usedCultists) {
                this.unusedCultists.add(c);
            }

            this.shuffleTreasures();
            this.usedCultists.clear();
        }

        Cultist nextC = this.unusedCultists.get(0);

        this.unusedCultists.remove(nextC);
        this.usedCultists.add(nextC);

        return nextC;
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

    private void shuffleCultists() {
        Collections.shuffle(unusedCultists);
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
