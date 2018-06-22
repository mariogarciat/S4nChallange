package co.com.s4n.semillero.ejercicio.dominio.entidades;

import io.vavr.collection.List;

public class Ruta {

    private List<String> rutas;

    public List<String> getRutas() {
        return rutas;
    }

    public Ruta(List<String> pedidos) {
        this.rutas = pedidos;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "rutas=" + rutas +
                '}';
    }
}
