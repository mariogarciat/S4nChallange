package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;

public class ServiceRuta {

    public static Ruta validarRuta(Ruta ruta){

        final String REGEX_RUTA = "[AID]*";
        List<String> filter = ruta.getRutas().filter(r -> CharSeq.of(r).matches(REGEX_RUTA));
        Ruta newRuta = new Ruta(filter);
        return newRuta;

    }


}
