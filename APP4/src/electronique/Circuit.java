package electronique;

import java.util.List;

public abstract class Circuit extends Composant {
    protected List<Composant> composants;

    public Circuit(List<Composant> listComposant){
        this.composants = listComposant;
    }

}
