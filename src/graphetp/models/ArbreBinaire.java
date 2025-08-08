/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thierno
 */
public class ArbreBinaire {
    private NoeudArbre racine;
    
    public ArbreBinaire() {
        this.racine = null;
    }

    public NoeudArbre getRacine() {
        return racine;
    }

    public void setRacine(NoeudArbre racine) {
        this.racine = racine;
    }

    public ArbreBinaire(NoeudArbre racine) {
        this.racine = racine;
    }

    // Parcours préfixé (racine, gauche, droit)
    public void parcoursPreordre(NoeudArbre noeud, List<Integer> resultat) {
        if (noeud != null) {
            resultat.add(noeud.getValeur());
            parcoursPreordre(noeud.getGauche(), resultat);
            parcoursPreordre(noeud.getDroit(), resultat);
        }
    }

    public List<Integer> parcoursPreordre() {
        List<Integer> resultat = new ArrayList<>();
        parcoursPreordre(racine, resultat);
        return resultat;
    }

    // Parcours infixe (gauche, racine, droit)
    public void parcoursInordre(NoeudArbre noeud, List<Integer> resultat) {
        if (noeud != null) {
            parcoursInordre(noeud.getGauche(), resultat);
            resultat.add(noeud.getValeur());
            parcoursInordre(noeud.getDroit(), resultat);
        }
    }

    public List<Integer> parcoursInordre() {
        List<Integer> resultat = new ArrayList<>();
        parcoursInordre(racine, resultat);
        return resultat;
    }

    // Parcours postfixé (gauche, droit, racine)
    public void parcoursPostordre(NoeudArbre noeud, List<Integer> resultat) {
        if (noeud != null) {
            parcoursPostordre(noeud.getGauche(), resultat);
            parcoursPostordre(noeud.getDroit(), resultat);
            resultat.add(noeud.getValeur());
        }
    }

    public List<Integer> parcoursPostordre() {
        List<Integer> resultat = new ArrayList<>();
        parcoursPostordre(racine, resultat);
        return resultat;
    }
}

