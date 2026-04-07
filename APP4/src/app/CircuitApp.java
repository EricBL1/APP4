package app;

import electronique.*;
import java.io.File;
import java.util.Scanner;

public class CircuitApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "donnees" + fSep + "fichiers_json" + fSep;

    public CircuitApp(){

    }

    private static File selectionnerFichier(){
        File dossier = new File(pathIn);

        File[] fichiers = dossier.listFiles(((dir, name) -> name.endsWith(".json")));

        if(fichiers == null || fichiers.length == 0){
            return null;
        }

        System.out.println("--- LISTE DES CIRCUITS ---");
        for (int i = 0; i < fichiers.length; i++) {
            System.out.println("[" + (i+1) + "] " + fichiers[i].getName());

        }

        System.out.println("Entrez le numéro du fichier à tester :");
        if(scanner.hasNextInt()){
            int fichierALire = scanner.nextInt();
            scanner.nextLine();

            if(fichierALire >= 1 && fichierALire <= fichiers.length){
                return fichiers[fichierALire - 1];
            }
        } else{
            scanner.nextLine();
        }
        System.out.println("Choix incorrect. Veuillez entrer un chiffre entre 1 et " + fichiers.length + ".");

        return null;
    }

    private static void afficherResultat(String nomFichier, double resultat){
        System.out.println("===============================================");
        System.out.println(" Résultat pour : " + nomFichier);
        System.out.println(" Résistance équivalente : " + String.format("%.2f",resultat) + "Ω");
        System.out.println("===============================================");
    }

    private static void afficherDebutProgramme(){
        System.out.println("===============================================");
        System.out.println("Début de programme");
        System.out.println("===============================================");
    }

    private static boolean demanderSiContinuer(){
        while(true){
            System.out.println("\n[R] Tester un autre fichier | [Q] Quitter : ");
            String continuer = scanner.nextLine().toUpperCase();

            if(continuer.equals("R")){
                return true;
            }
            else if (continuer.equals("Q")){
                return false;
            }
            System.out.println("Option non reconnue. Utilisez 'R' ou 'Q'.");
        }
    }

    public static void main(String[] args) {
        CircuitBuilder builder = new CircuitBuilder();
        boolean continuer = true;

        afficherDebutProgramme();

        while(continuer) {
            File fichier = selectionnerFichier();

            if (fichier != null) {
                Composant circuit = builder.construireCircuit(fichier.getName());

                if (circuit != null) {
                    double resultat = circuit.calculerResistance();
                    afficherResultat(fichier.getName(), resultat);
                }
            }
            continuer = demanderSiContinuer();
        }
        System.out.println("Programme arrêté");
    }
}
