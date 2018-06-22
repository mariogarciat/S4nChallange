package co.com.s4n.semillero.ejercicio.dominio.entidades;

import co.com.s4n.semillero.ejercicio.dominio.vo.Accion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;

public class Pedido {

    private String direccion;
    private List<Accion> ins;

    public List<Accion> getIns() {
        return ins;
    }

    public Pedido(List<Accion> ins) {
        this.ins = ins;
    }

    public String getDireccion() {
        return direccion;
    }

    public Pedido(String direccion) {
        this.direccion = direccion;
    }
}
