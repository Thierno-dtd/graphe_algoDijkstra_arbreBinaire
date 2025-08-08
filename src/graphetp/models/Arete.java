/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.models;

/**
 *
 * @author thierno
 */
public class Arete {
    private int destination;
    private int poids;

    public Arete(int destination, int poids) {
        this.destination = destination;
        this.poids = poids;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }
    
}
