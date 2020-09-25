package triangulos;

public class Triangulo {

    // Retorna el tipo de triangulo segun las medidas de sus lados
    public String tipoTriangulo(double l1, double l2, double l3) {
        if (l1 == l2 && l2 == l3) {
            return "Equilátero";
        } else if (l1 == l2 || l1 == l3 || l2 == l3) {
            return "Isóceles";
        } //else if (l1 != l2 || l1 != l3 || l2 != l3) {
            return "Escaleno";
        //}
    }
}
