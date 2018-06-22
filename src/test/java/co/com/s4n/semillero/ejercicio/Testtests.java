package co.com.s4n.semillero.ejercicio;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceRuta;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitPlatform.class)
public class Testtests {

    @Test
    public void test(){
        List<String> list = List.of("AAAIDIA","IDAADAAI", "AAI");
        List<String> listToRuta = List.of("AAAIDIA", "AFAI", "IDAADAAI", "I*DA", "AAI");
        Ruta ruta = new Ruta(list);
        Ruta ruta1 = ServiceRuta.validarRuta(ruta);
        assertEquals(ruta.getRutas().size(),ruta1.getRutas().size());
    }

    @Test
    public void asd(){
        boolean b = ServiceDron.enLimite(1,-8);
        assertTrue(b);
    }
}
