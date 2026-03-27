package electronique;

import java.util.List;

public class CircuitParallele extends Circuit {
    public CircuitParallele(List<Composant> listComposant) {
        super(listComposant);
    }

    @Override
    public double calculerResistance() {
        double resistanceParallele = 0;

        for (Composant composant : composants){
            double r = composant.calculerResistance();

            if(r != 0){
                resistanceParallele += 1/r;
            }
        }

        if(resistanceParallele == 0){
            return 0;
        } else{
            return 1/ resistanceParallele;
        }
    }
}
