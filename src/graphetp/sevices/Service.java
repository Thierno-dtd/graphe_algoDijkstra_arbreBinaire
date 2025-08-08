/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphetp.sevices;

import graphetp.models.Arete;
import graphetp.models.Graphe;
import graphetp.models.GraphePondere;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author thierno
 */
public class Service {

    public static List<Integer> triTopologique(Graphe graphe) {
        Map<Integer, List<Integer>> adj = graphe.getAdjacences();
        int[] degreEntrant = new int[graphe.getNombreSommets()];

        // Calculer les degrés entrants
        for (int sommet : adj.keySet()) {
            for (int voisin : adj.get(sommet)) {
                degreEntrant[voisin]++;
            }
        }

        // Queue pour les sommets de degré entrant 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graphe.getNombreSommets(); i++) {
            if (degreEntrant[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> resultat = new ArrayList<>();

        while (!queue.isEmpty()) {
            int sommet = queue.poll();
            resultat.add(sommet);

            // Réduire le degré entrant des voisins
            for (int voisin : adj.get(sommet)) {
                degreEntrant[voisin]--;
                if (degreEntrant[voisin] == 0) {
                    queue.offer(voisin);
                }
            }
        }

        // Vérifier s'il y a un cycle
        if (resultat.size() != graphe.getNombreSommets()) {
            throw new RuntimeException("Le graphe contient un cycle !");
        }

        return resultat;
    }

    // ================================
// 3. ALGORITHME DE DIJKSTRA
// ================================

    static class NoeudDistance implements Comparable<NoeudDistance> {
        int sommet;
        int distance;

        public NoeudDistance(int sommet, int distance) {
            this.sommet = sommet;
            this.distance = distance;
        }

        @Override
        public int compareTo(NoeudDistance autre) {
            return Integer.compare(this.distance, autre.distance);
        }
    }

    public static Map<Integer, Integer> dijkstra(GraphePondere graphe, int source) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> precedents = new HashMap<>();
        PriorityQueue<NoeudDistance> pq = new PriorityQueue<>();
        Set<Integer> visites = new HashSet<>();

        // Initialisation
        for (int i = 0; i < graphe.getNombreSommets(); i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.offer(new NoeudDistance(source, 0));

        while (!pq.isEmpty()) {
            NoeudDistance noeudCourant = pq.poll();
            int sommetCourant = noeudCourant.sommet;

            if (visites.contains(sommetCourant)) {
                continue;
            }

            visites.add(sommetCourant);

            // Explorer les voisins
            for (Arete arete : graphe.getAdjacences().get(sommetCourant)) {
                int voisin = arete.getDestination();
                int nouvelleDistance = distances.get(sommetCourant) + arete.getPoids();

                if (nouvelleDistance < distances.get(voisin)) {
                    distances.put(voisin, nouvelleDistance);
                    precedents.put(voisin, sommetCourant);
                    pq.offer(new NoeudDistance(voisin, nouvelleDistance));
                }
            }
        }

        return distances;
    }

    public static List<Integer> obtenirChemin(GraphePondere graphe, int source, int destination) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> precedents = new HashMap<>();
        PriorityQueue<NoeudDistance> pq = new PriorityQueue<>();
        Set<Integer> visites = new HashSet<>();

        // Algorithme de Dijkstra avec suivi des prédécesseurs
        for (int i = 0; i < graphe.getNombreSommets(); i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.offer(new NoeudDistance(source, 0));

        while (!pq.isEmpty()) {
            NoeudDistance noeudCourant = pq.poll();
            int sommetCourant = noeudCourant.sommet;

            if (visites.contains(sommetCourant)) continue;
            visites.add(sommetCourant);

            for (Arete arete : graphe.getAdjacences().get(sommetCourant)) {
                int voisin = arete.getDestination();
                int nouvelleDistance = distances.get(sommetCourant) + arete.getPoids();

                if (nouvelleDistance < distances.get(voisin)) {
                    distances.put(voisin, nouvelleDistance);
                    precedents.put(voisin, sommetCourant);
                    pq.offer(new NoeudDistance(voisin, nouvelleDistance));
                }
            }
        }

        // Reconstituer le chemin
        List<Integer> chemin = new ArrayList<>();
        int current = destination;
        while (precedents.containsKey(current)) {
            chemin.add(0, current);
            current = precedents.get(current);
        }
        if (current == source) {
            chemin.add(0, source);
        }

        return chemin.isEmpty() || chemin.get(0) != source ? new ArrayList<>() : chemin;
    }

}
