package app;
import electronique.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class CircuitBuilder {

    private static final char fSep = File.separatorChar;

    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4" + fSep +"src" + fSep + "donnees" + fSep + "fichiers_json" + fSep;

    public CircuitBuilder(){

    }

    public Composant construireCircuit(String fichierALire){
        ObjectMapper mapper = new ObjectMapper();
        String cheminComplet = (pathIn + fichierALire);
        try{
            JsonNode donneesCircuit = mapper.readTree(new File(cheminComplet));

            return lireComposant(donneesCircuit.get("circuit"));

        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
        return null;
    }

    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if("resistance".equals(type)){
            return new Resistance(node.get("valeur").asDouble());
        } else if("parallele".equals(type)){
            List<Composant> composantes = new ArrayList<>();
            for(JsonNode composantNode : node.get("composants")) {
                composantes.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composantes);
        } else if("serie".equals(type)){
            List<Composant> composantes = new ArrayList<>();
            for(JsonNode composantNode : node.get("composants")) {
                composantes.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composantes);

        }
        throw new IllegalArgumentException("Type de composantes inconnu : " + type);

    }


}
