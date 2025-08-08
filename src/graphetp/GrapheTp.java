/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graphetp;

import graphetp.models.*;
import graphetp.sevices.*;
import java.util.*;

/**
 *
 * @author thierno
 */
public class GrapheTp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("    TP GRAPHES ET ALGORITHMES      ");
        System.out.println("====================================");
        
        boolean continuer = true;
        
        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    testTriTopologique();
                    break;
                case 2:
                    testAlgorithmeDijkstra();
                    break;
                case 3:
                    testParcoursArbreBinaire();
                    break;
                case 4:
                    testArbreBinaireRecherche();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide ! Veuillez réessayer.");
            }
            
            if (continuer) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                scanner.nextLine();
            }
        }
    }
    
    private static void afficherMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Tri Topologique (Graphe orienté sans circuit)");
        System.out.println("2. Algorithme de Dijkstra (Plus court chemin)");
        System.out.println("3. Parcours d'Arbre Binaire");
        System.out.println("4. Arbre Binaire de Recherche (ABR)");
        System.out.println("0. Quitter");
        System.out.println("=".repeat(50));
    }

    // ================================
    // 1. TEST TRI TOPOLOGIQUE
    // ================================
    
    private static void testTriTopologique() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           TRI TOPOLOGIQUE - GRAPHE ORIENTÉ");
        System.out.println("=".repeat(60));
        
        int nombreSommets = lireEntier("Nombre de sommets du graphe: ");
        Graphe graphe = new Graphe(nombreSommets);
        
        System.out.println("\nSommets disponibles: 0 à " + (nombreSommets - 1));
        System.out.println("Ajoutez les arêtes (source -> destination):");
        System.out.println("(Entrez -1 pour source pour terminer)");
        
        boolean ajouterAretes = true;
        int compteurAretes = 0;
        
        while (ajouterAretes) {
            System.out.println("\n--- Arête " + (compteurAretes + 1) + " ---");
            int source = lireEntier("Sommet source (-1 pour terminer): ");
            
            if (source == -1) {
                ajouterAretes = false;
            } else if (source >= 0 && source < nombreSommets) {
                int destination = lireEntier("Sommet destination: ");
                
                if (destination >= 0 && destination < nombreSommets) {
                    graphe.ajouterArete(source, destination);
                    System.out.println("✓ Arête ajoutée: " + source + " → " + destination);
                    compteurAretes++;
                } else {
                    System.out.println("❌ Destination invalide ! Doit être entre 0 et " + (nombreSommets - 1));
                }
            } else {
                System.out.println("❌ Source invalide ! Doit être entre 0 et " + (nombreSommets - 1));
            }
        }
        
        if (compteurAretes == 0) {
            System.out.println("\n⚠️  Aucune arête ajoutée. Tri topologique d'un graphe vide:");
            for (int i = 0; i < nombreSommets; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        
        // Afficher le graphe créé
        System.out.println("\n📊 Graphe créé:");
        afficherGraphe(graphe);
        
        // Exécuter le tri topologique
        try {
            List<Integer> ordreTopologique = Service.triTopologique(graphe);
            System.out.println("\n✅ TRI TOPOLOGIQUE RÉUSSI !");
            System.out.println("Ordre topologique: " + ordreTopologique);
            System.out.println("\nInterprétation: Les tâches peuvent être exécutées dans cet ordre");
            System.out.println("sans violer les dépendances.");
            
        } catch (RuntimeException e) {
            System.out.println("\n❌ ERREUR: " + e.getMessage());
            System.out.println("Le graphe contient un cycle, le tri topologique est impossible.");
        }
    }
    
    // ================================
    // 2. TEST ALGORITHME DE DIJKSTRA
    // ================================
    
    private static void testAlgorithmeDijkstra() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        ALGORITHME DE DIJKSTRA - PLUS COURT CHEMIN");
        System.out.println("=".repeat(60));
        
        int nombreSommets = lireEntier("Nombre de sommets du graphe: ");
        GraphePondere graphe = new GraphePondere(nombreSommets);
        
        System.out.println("\nSommets disponibles: 0 à " + (nombreSommets - 1));
        System.out.println("Ajoutez les arêtes pondérées (source -> destination, poids):");
        System.out.println("(Entrez -1 pour source pour terminer)");
        
        boolean ajouterAretes = true;
        int compteurAretes = 0;
        
        while (ajouterAretes) {
            System.out.println("\n--- Arête pondérée " + (compteurAretes + 1) + " ---");
            int source = lireEntier("Sommet source (-1 pour terminer): ");
            
            if (source == -1) {
                ajouterAretes = false;
            } else if (source >= 0 && source < nombreSommets) {
                int destination = lireEntier("Sommet destination: ");
                
                if (destination >= 0 && destination < nombreSommets) {
                    int poids = lireEntier("Poids de l'arête (positif): ");
                    
                    if (poids >= 0) {
                        graphe.ajouterArete(source, destination, poids);
                        System.out.println("✓ Arête ajoutée: " + source + " → " + destination + " (poids: " + poids + ")");
                        compteurAretes++;
                    } else {
                        System.out.println("❌ Le poids doit être positif ou nul pour Dijkstra !");
                    }
                } else {
                    System.out.println("❌ Destination invalide !");
                }
            } else {
                System.out.println("❌ Source invalide !");
            }
        }
        
        if (compteurAretes == 0) {
            System.out.println("\n⚠️  Aucune arête ajoutée. Impossible d'exécuter Dijkstra.");
            return;
        }
        
        // Afficher le graphe pondéré
        System.out.println("\n📊 Graphe pondéré créé:");
        afficherGraphePondere(graphe);
        
        // Choisir le sommet source pour Dijkstra
        int sommetSource = lireEntier("\nChoisissez le sommet source pour Dijkstra (0-" + (nombreSommets-1) + "): ");
        
        if (sommetSource < 0 || sommetSource >= nombreSommets) {
            System.out.println("❌ Sommet source invalide !");
            return;
        }
        
        // Exécuter Dijkstra
        Map<Integer, Integer> distances = Service.dijkstra(graphe, sommetSource);
        
        System.out.println("\n✅ RÉSULTATS DE DIJKSTRA:");
        System.out.println("Distances minimales depuis le sommet " + sommetSource + ":");
        System.out.println("-".repeat(40));
        
        for (int i = 0; i < nombreSommets; i++) {
            int distance = distances.get(i);
            if (distance == Integer.MAX_VALUE) {
                System.out.println("Sommet " + i + ": INACCESSIBLE");
            } else {
                System.out.println("Sommet " + i + ": " + distance);
            }
        }
        
        // Test du chemin vers une destination spécifique
        System.out.print("\nVoulez-vous voir le chemin vers un sommet spécifique ? (o/n): ");
        String reponse = scanner.nextLine().trim().toLowerCase();
        
        if (reponse.equals("o") || reponse.equals("oui")) {
            int destination = lireEntier("Sommet destination: ");
            if (destination >= 0 && destination < nombreSommets) {
                List<Integer> chemin = Service.obtenirChemin(graphe, sommetSource, destination);
                
                if (chemin.isEmpty()) {
                    System.out.println("❌ Aucun chemin trouvé de " + sommetSource + " vers " + destination);
                } else {
                    System.out.println("✅ Chemin optimal de " + sommetSource + " vers " + destination + ":");
                    System.out.print("   ");
                    for (int i = 0; i < chemin.size(); i++) {
                        System.out.print(chemin.get(i));
                        if (i < chemin.size() - 1) System.out.print(" → ");
                    }
                    System.out.println("\n   Distance totale: " + distances.get(destination));
                }
            }
        }
    }
    
    // ================================
    // 3. TEST PARCOURS ARBRE BINAIRE
    // ================================
    
    private static void testParcoursArbreBinaire() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           PARCOURS D'ARBRE BINAIRE");
        System.out.println("=".repeat(60));
        
        System.out.println("Comment voulez-vous créer l'arbre ?");
        System.out.println("1. Création manuelle nœud par nœud");
        System.out.println("2. Arbre d'exemple prédéfini");
        
        int choix = lireEntier("Votre choix: ");
        
        ArbreBinaire arbre = null;
        
        switch (choix) {
            case 1:
                arbre = creerArbreManuel();
                break;
            case 2:
                arbre = creerArbreExemple();
                System.out.println("✅ Arbre d'exemple créé:");
                System.out.println("       1");
                System.out.println("      / \\");
                System.out.println("     2   3");
                System.out.println("    / \\   \\");
                System.out.println("   4   5   6");
                break;
            default:
                System.out.println("❌ Choix invalide !");
                return;
        }
        
        if (arbre == null || arbre.getRacine() == null) {
            System.out.println("❌ Arbre vide ou non créé !");
            return;
        }
        
        // Effectuer les parcours
        System.out.println("\n🌳 RÉSULTATS DES PARCOURS:");
        System.out.println("-".repeat(50));
        
        List<Integer> preordre = arbre.parcoursPreordre();
        List<Integer> inordre = arbre.parcoursInordre();
        List<Integer> postordre = arbre.parcoursPostordre();
        
        System.out.println("Parcours PRÉFIXÉ  (Racine → Gauche → Droit): " + preordre);
        System.out.println("Parcours INFIXE   (Gauche → Racine → Droit): " + inordre);
        System.out.println("Parcours POSTFIXÉ (Gauche → Droit → Racine): " + postordre);
        
        System.out.println("\n📋 EXPLICATIONS:");
        System.out.println("• Préfixé: Utilisé pour copier/sauvegarder l'arbre");
        System.out.println("• Infixe: Pour un ABR, donne les éléments triés");
        System.out.println("• Postfixé: Utilisé pour supprimer l'arbre ou calculer l'espace");
    }
    
    private static ArbreBinaire creerArbreManuel() {
        System.out.println("\n🏗️  CRÉATION MANUELLE D'ARBRE BINAIRE");
        System.out.println("Vous allez créer un arbre en spécifiant chaque nœud.");
        
        int valeurRacine = lireEntier("Valeur de la racine: ");
        NoeudArbre racine = new NoeudArbre(valeurRacine);
        
        Map<Integer, NoeudArbre> noeuds = new HashMap<>();
        noeuds.put(valeurRacine, racine);
        
        boolean continuer = true;
        
        while (continuer) {
            System.out.println("\nNœuds existants: " + noeuds.keySet());
            System.out.print("Voulez-vous ajouter un nœud ? (o/n): ");
            String reponse = scanner.nextLine().trim().toLowerCase();
            
            if (!reponse.equals("o") && !reponse.equals("oui")) {
                continuer = false;
                continue;
            }
            
            int valeurParent = lireEntier("Valeur du nœud parent: ");
            
            if (!noeuds.containsKey(valeurParent)) {
                System.out.println("❌ Nœud parent inexistant !");
                continue;
            }
            
            NoeudArbre parent = noeuds.get(valeurParent);
            
            System.out.println("Où placer le nouveau nœud ?");
            System.out.println("1. Enfant gauche de " + valeurParent);
            System.out.println("2. Enfant droit de " + valeurParent);
            
            int position = lireEntier("Position: ");
            
            if (position == 1) {
                if (parent.getGauche() != null) {
                    System.out.println("❌ L'enfant gauche existe déjà (valeur: " + parent.getGauche().getValeur() + ") !");
                    continue;
                }
                
                int valeurNoeud = lireEntier("Valeur du nouveau nœud: ");
                NoeudArbre nouveauNoeud = new NoeudArbre(valeurNoeud);
                parent.setGauche(nouveauNoeud);
                noeuds.put(valeurNoeud, nouveauNoeud);
                System.out.println("✅ Nœud " + valeurNoeud + " ajouté à gauche de " + valeurParent);
                
            } else if (position == 2) {
                if (parent.getDroit() != null) {
                    System.out.println("❌ L'enfant droit existe déjà (valeur: " + parent.getDroit().getValeur() + ") !");
                    continue;
                }
                
                int valeurNoeud = lireEntier("Valeur du nouveau nœud: ");
                NoeudArbre nouveauNoeud = new NoeudArbre(valeurNoeud);
                parent.setDroit(nouveauNoeud);
                noeuds.put(valeurNoeud, nouveauNoeud);
                System.out.println("✅ Nœud " + valeurNoeud + " ajouté à droite de " + valeurParent);
                
            } else {
                System.out.println("❌ Position invalide !");
            }
        }
        
        return new ArbreBinaire(racine);
    }
    
    private static ArbreBinaire creerArbreExemple() {
        NoeudArbre racine = new NoeudArbre(1);
        racine.setGauche(new NoeudArbre(2));
        racine.setDroit(new NoeudArbre(3));
        racine.getGauche().setGauche(new NoeudArbre(4));
        racine.getGauche().setDroit(new NoeudArbre(5));
        racine.getDroit().setDroit(new NoeudArbre(6));
        
        return new ArbreBinaire(racine);
    }
    
    // ================================
    // 4. TEST ARBRE BINAIRE DE RECHERCHE
    // ================================
    
    private static void testArbreBinaireRecherche() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        ARBRE BINAIRE DE RECHERCHE (ABR)");
        System.out.println("=".repeat(60));
        
        ArbreBinaireRecherche abr = new ArbreBinaireRecherche();
        
        boolean continuer = true;
        
        while (continuer) {
            afficherMenuABR();
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    // Insertion
                    int valeurInsert = lireEntier("Valeur à insérer: ");
                    abr.inserer(valeurInsert);
                    System.out.println("✅ Valeur " + valeurInsert + " insérée !");
                    break;
                    
                case 2:
                    // Recherche
                    int valeurRecherche = lireEntier("Valeur à rechercher: ");
                    boolean trouve = abr.rechercher(valeurRecherche);
                    if (trouve) {
                        System.out.println("✅ Valeur " + valeurRecherche + " trouvée dans l'ABR !");
                    } else {
                        System.out.println("❌ Valeur " + valeurRecherche + " non trouvée dans l'ABR !");
                    }
                    break;
                    
                case 3:
                    // Suppression
                    int valeurSuppr = lireEntier("Valeur à supprimer: ");
                    if (abr.rechercher(valeurSuppr)) {
                        abr.supprimer(valeurSuppr);
                        System.out.println("✅ Valeur " + valeurSuppr + " supprimée !");
                    } else {
                        System.out.println("❌ Valeur " + valeurSuppr + " n'existe pas dans l'ABR !");
                    }
                    break;
                    
                case 4:
                    // Affichage
                    List<Integer> elements = abr.parcoursInordre();
                    if (elements.isEmpty()) {
                        System.out.println("🔴 ABR vide !");
                    } else {
                        System.out.println("🌳 Éléments de l'ABR (triés): " + elements);
                        System.out.println("📏 Hauteur de l'arbre: " + abr.hauteur());
                        System.out.println("📊 Nombre d'éléments: " + elements.size());
                        
                        Integer min = abr.minimum();
                        Integer max = abr.maximum();
                        if (min != null) System.out.println("⬇️  Minimum: " + min);
                        if (max != null) System.out.println("⬆️  Maximum: " + max);
                    }
                    break;
                    
                case 5:
                    // Construction à partir d'un tableau
                    System.out.print("Entrez les valeurs séparées par des espaces: ");
                    String ligne = scanner.nextLine().trim();
                    
                    if (!ligne.isEmpty()) {
                        try {
                            String[] parties = ligne.split("\\s+");
                            int[] valeurs = new int[parties.length];
                            
                            for (int i = 0; i < parties.length; i++) {
                                valeurs[i] = Integer.parseInt(parties[i]);
                            }
                            
                            abr = ArbreBinaireRecherche.construireABR(valeurs);
                            System.out.println("✅ ABR construit avec les valeurs: " + Arrays.toString(valeurs));
                            System.out.println("🌳 Ordre trié: " + abr.parcoursInordre());
                            
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Format invalide ! Utilisez des nombres entiers séparés par des espaces.");
                        }
                    }
                    break;
                    
                case 0:
                    continuer = false;
                    break;
                    
                default:
                    System.out.println("❌ Choix invalide !");
            }
            
            if (continuer) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                scanner.nextLine();
            }
        }
    }
    
    private static void afficherMenuABR() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("             MENU ABR");
        System.out.println("-".repeat(50));
        System.out.println("1. Insérer une valeur");
        System.out.println("2. Rechercher une valeur");
        System.out.println("3. Supprimer une valeur");
        System.out.println("4. Afficher l'ABR (parcours inordre)");
        System.out.println("5. Construire ABR à partir de valeurs");
        System.out.println("0. Retour au menu principal");
        System.out.println("-".repeat(50));
    }
    
    // ================================
    // MÉTHODES UTILITAIRES
    // ================================
    
    private static int lireEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                String ligne = scanner.nextLine().trim();
                return Integer.parseInt(ligne);
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un nombre entier valide !");
            }
        }
    }
    
    private static void afficherGraphe(Graphe graphe) {
        System.out.println("Adjacences:");
        for (int sommet = 0; sommet < graphe.getNombreSommets(); sommet++) {
            List<Integer> voisins = graphe.getAdjacences().get(sommet);
            System.out.print("  " + sommet + " → ");
            if (voisins.isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(voisins);
            }
        }
    }
    
    private static void afficherGraphePondere(GraphePondere graphe) {
        System.out.println("Adjacences pondérées:");
        for (int sommet = 0; sommet < graphe.getNombreSommets(); sommet++) {
            List<Arete> aretes = graphe.getAdjacences().get(sommet);
            System.out.print("  " + sommet + " → ");
            if (aretes.isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.print("[");
                for (int i = 0; i < aretes.size(); i++) {
                    Arete arete = aretes.get(i);
                    System.out.print(arete.getDestination() + "(poids:" + arete.getPoids() + ")");
                    if (i < aretes.size() - 1) System.out.print(", ");
                }
                System.out.println("]");
            }
        }
    }
}