package app;
import electronique.*;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class CircuitBuilder {

    public CircuitBuilder(){

    }

    public Composant construireCircuit(String composant){

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
