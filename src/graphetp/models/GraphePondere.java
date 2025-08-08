/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thierno
 */
public class GraphePondere {
    private Map<Integer, List<Arete>> adjacences;
    private int nombreSommets;

    public GraphePondere(int nombreSommets) {
        this.nombreSommets = nombreSommets;
        this.adjacences = new HashMap<>();
        for (int i = 0; i < nombreSommets; i++) {
            adjacences.put(i, new ArrayList<>());
        }
    }

    public void ajouterArete(int source, int destination, int poids) {
        adjacences.get(source).add(new Arete(destination, poids));
    }

    public Map<Integer, List<Arete>> getAdjacences() {
        return adjacences;
    }

    public int getNombreSommets() {
        return nombreSommets;
    }

    
}
