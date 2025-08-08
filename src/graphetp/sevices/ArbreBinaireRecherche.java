/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.sevices;

import graphetp.models.NoeudArbre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thierno
 */
public class ArbreBinaireRecherche {
    NoeudArbre racine;

    public ArbreBinaireRecherche() {
        this.racine = null;
    }

    // Construction d'un ABR à partir d'un tableau
    public static ArbreBinaireRecherche construireABR(int[] valeurs) {
        ArbreBinaireRecherche abr = new ArbreBinaireRecherche();
        for (int valeur : valeurs) {
            abr.inserer(valeur);
        }
        return abr;
    }

    // Insertion d'un élément
    public void inserer(int valeur) {
        racine = insererRecursif(racine, valeur);
    }

    private NoeudArbre insererRecursif(NoeudArbre noeud, int valeur) {
        if (noeud == null) {
            return new NoeudArbre(valeur);
        }

        if (valeur < noeud.getValeur()) {
            noeud.setGauche(insererRecursif(noeud.getGauche(), valeur));
        } else if (valeur > noeud.getValeur()) {
            noeud.setDroit(insererRecursif(noeud.getDroit(), valeur));
        }

        return noeud;
    }

    // Recherche d'un élément
    public boolean rechercher(int valeur) {
        return rechercherRecursif(racine, valeur);
    }

    private boolean rechercherRecursif(NoeudArbre noeud, int valeur) {
        if (noeud == null) {
            return false;
        }

        if (valeur == noeud.getValeur()) {
            return true;
        }

        if (valeur < noeud.getValeur()) {
            return rechercherRecursif(noeud.getGauche(), valeur);
        } else {
            return rechercherRecursif(noeud.getDroit(), valeur);
        }
    }

    // Suppression d'un élément
    public void supprimer(int valeur) {
        racine = supprimerRecursif(racine, valeur);
    }

    private NoeudArbre supprimerRecursif(NoeudArbre noeud, int valeur) {
        if (noeud == null) {
            return noeud;
        }

        if (valeur < noeud.getValeur()) {
            noeud.setGauche(supprimerRecursif(noeud.getGauche(), valeur));
        } else if (valeur > noeud.getValeur()) {
            noeud.setDroit(supprimerRecursif(noeud.getDroit(), valeur));
        } else {
            // Noeud à supprimer trouvé
            if (noeud.getGauche() == null) {
                return noeud.getDroit();
            } else if (noeud.getDroit() == null) {
                return noeud.getGauche();
            }

            // Noeud avec deux enfants : trouver le successeur
            noeud.setValeur(trouverMin(noeud.getDroit()).getValeur());
            noeud.setDroit(supprimerRecursif(noeud.getDroit(), noeud.getValeur()));
        }

        return noeud;
    }

    private NoeudArbre trouverMin(NoeudArbre noeud) {
        while (noeud.getGauche() != null) {
            noeud = noeud.getGauche();
        }
        return noeud;
    }

    // Trouver la valeur minimum
    public Integer minimum() {
        NoeudArbre min = trouverMin(racine);
        return min != null ? min.getValeur() : null;
    }

    // Trouver la valeur maximum
    public Integer maximum() {
        NoeudArbre noeud = racine;
        if (noeud == null) return null;

        while (noeud.getDroit() != null) {
            noeud = noeud.getDroit();
        }
        return noeud.getValeur();
    }

    // Parcours inordre (donne les éléments triés)
    public List<Integer> parcoursInordre() {
        List<Integer> resultat = new ArrayList<>();
        parcoursInordreRecursif(racine, resultat);
        return resultat;
    }

    private void parcoursInordreRecursif(NoeudArbre noeud, List<Integer> resultat) {
        if (noeud != null) {
            parcoursInordreRecursif(noeud.getGauche(), resultat);
            resultat.add(noeud.getValeur());
            parcoursInordreRecursif(noeud.getDroit(), resultat);
        }
    }

    // Hauteur de l'arbre
    public int hauteur() {
        return hauteurRecursive(racine);
    }

    private int hauteurRecursive(NoeudArbre noeud) {
        if (noeud == null) {
            return -1;
        }
        return 1 + Math.max(hauteurRecursive(noeud.getGauche()), hauteurRecursive(noeud.getDroit()));
    }
}

