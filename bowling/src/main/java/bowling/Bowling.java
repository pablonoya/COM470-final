package bowling;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Bowling {

    private int puntajeTotal = 0;
    ArrayList<Integer> puntajes = new ArrayList<>();

    public void puntaje(int derribados) {
        puntajes.add(derribados);
    }

    public int puntajeFinal() {
        for (int i = 0; i < puntajes.size(); i += 1) {
            // strike suma 2 lanzamientos siguientes
            if (puntajes.get(i) == 10) {
                if (i < puntajes.size() - 2) {
                    puntajeTotal += puntajes.get(i + 1) + puntajes.get(i + 2);
                }
                // En caso de strike al turno final
                // los 2 puntajes siguientes ya se han sumado
                if (i == 18) {
                    return puntajeTotal + 10;
                }
            }

            // spare 
            // controlamos no desbordarnos
            if (i < puntajes.size() - 1) {
                if (puntajes.get(i) + puntajes.get(i + 1) == 10) {
                    puntajeTotal += puntajes.get(i + 2);

                    // si hay spare al ultimo ya se sumó el punto extra
                    if (i == 18) {
                        return puntajeTotal + 9;
                    }
                }
            }
            
            // sumamos los pinos derribados
            puntajeTotal += puntajes.get(i);
            
            // si hay strike como último lanzamiento después de un spare
            // ya se ha sumado
            if (i ==  puntajes.size() - 1 && 
                puntajes.get(puntajes.size() - 1) == 10) {
                puntajeTotal -= 10;
            }
        }
        return puntajeTotal;
    }
}
