package triangulos;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TrianguloTest {

    Triangulo triangulo = new Triangulo();

    @Test
    @junitparams.Parameters({
        "5,5,5, Equilátero",
        "1,1,1, Equilátero",
        "42,42,42, Equilátero",
        "10,10,10, Equilátero",
        "7,7,3, Isóceles",
        "9,4,4, Isóceles",
        "1,1,6, Isóceles",
        "8,2,8, Isóceles",
        "1,2,3, Escaleno",
        "4,7,5, Escaleno",
        "22,11,55, Escaleno",
        "128,64,32, Escaleno"})
    public void testTipoTriangulo(int l1, int l2, int l3, String tipo) {
        assertEquals(triangulo.tipoTriangulo(l1, l2, l3), tipo);
    }

}
