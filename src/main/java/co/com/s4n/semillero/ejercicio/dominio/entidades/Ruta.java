package co.com.s4n.semillero.ejercicio.dominio.entidades;

import io.vavr.collection.List;

public class Ruta {

    private String[] pedidos;
    private String pedido1;
    private String pedido2;
    private String pedido3;

    public String getPedido1() {
        return pedido1;
    }

    public String getPedido2() {
        return pedido2;
    }

    public String getPedido3() {
        return pedido3;
    }

    public Ruta(String pedido1, String pedido2, String pedido3) {
        this.pedido1 = pedido1;
        this.pedido2 = pedido2;
        this.pedido3 = pedido3;
    }

    public Ruta(List<String> pedidos) {
        this.pedido1 = pedidos.get(0);
        this.pedido1 = pedidos.get(1);
        this.pedido1 = pedidos.get(2);
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "pedido1='" + pedido1 + '\'' +
                ", pedido2='" + pedido2 + '\'' +
                ", pedido3='" + pedido3 + '\'' +
                '}';
    }

}
