/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.models;

/**
 *
 * @author thierno
 */
public class NoeudArbre {
    private int valeur;
    private NoeudArbre gauche;
    private NoeudArbre droit;

    public NoeudArbre(int valeur) {
        this.valeur = valeur;
        this.gauche = null;
        this.droit = null;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public NoeudArbre getGauche() {
        return gauche;
    }

    public void setGauche(NoeudArbre gauche) {
        this.gauche = gauche;
    }

    public NoeudArbre getDroit() {
        return droit;
    }

    public void setDroit(NoeudArbre droit) {
        this.droit = droit;
    }
    
}
