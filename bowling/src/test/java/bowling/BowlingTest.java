package bowling;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BowlingTest {

    Bowling bowling;

    @Before
    public void setUp() {
        bowling = new Bowling();
    }

    @Test
    public void todosLanzamientosMalos() {
        for (int i = 0; i < 20; i++) {
            bowling.puntaje(0);
        }
        assertEquals(0, bowling.puntajeFinal());
    }

    @Test
    public void todosLanzamientosUnoTotal20() {
        for (int i = 0; i < 20; i++) {
            bowling.puntaje(1);
        }
        assertEquals(20, bowling.puntajeFinal());
    }

    @Test
    public void todosLanzamientosCuatroTotal80() {
        for (int i = 0; i < 20; i++) {
            bowling.puntaje(4);
        }
        assertEquals(80, bowling.puntajeFinal());
    }

    @Test
    public void primerLanzamientoSpareRestantesUno() {
        // spare todos derribados en 2 lanzamientos
        bowling.puntaje(9);
        bowling.puntaje(1);

        for (int i = 2; i < 20; i++) {
            bowling.puntaje(1);
        }
        
        assertEquals(29, bowling.puntajeFinal());
    }
    
    @Test
    public void primerLanzamientoStrikeRestantesUno() {
        // strike sólo un lanzamiento
        bowling.puntaje(10);

        for (int i = 2; i < 20; i++) {
            bowling.puntaje(1);
        }
        
        assertEquals(30, bowling.puntajeFinal());
    }
    
    @Test
    public void ultimoLanzamientoSpareRestantesUno() {
        for (int i = 0; i < 18; i++) {
            bowling.puntaje(1);
        }
        
        // spare
        bowling.puntaje(9);
        bowling.puntaje(1);
        
        // para calcular spare se realiza un lanzamiento más
        bowling.puntaje(1);
        
        assertEquals(29, bowling.puntajeFinal());
    }
    
    @Test
    public void ultimoLanzamientoStrikeRestantesUno() {
        for (int i = 0; i < 18; i++) {
            bowling.puntaje(1);
        }
        
        // strike
        bowling.puntaje(10);
        
        // para calcular strike se realizan dos lanzamientos más
        bowling.puntaje(1);
        bowling.puntaje(1);
        
        assertEquals(30, bowling.puntajeFinal());
    }
    
    @Test
    public void lanzamientosDelEnunciado() {
        bowling.puntaje(10);
        bowling.puntaje(9);
        bowling.puntaje(1);
        bowling.puntaje(5);
        bowling.puntaje(5);
        bowling.puntaje(7);
        bowling.puntaje(2);
        bowling.puntaje(10);
        bowling.puntaje(10);
        bowling.puntaje(10);
        bowling.puntaje(9);
        bowling.puntaje(0);
        bowling.puntaje(8);
        bowling.puntaje(2);
        bowling.puntaje(9);
        bowling.puntaje(1);
        bowling.puntaje(10);
        
        assertEquals(187, bowling.puntajeFinal());
    }

}
