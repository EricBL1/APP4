package app;

import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class CircuitApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4" + fSep +"src" + fSep + "donnees" + fSep + "fichiers_json" + fSep;

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

    public static void main(String[] args) {

    }
}
