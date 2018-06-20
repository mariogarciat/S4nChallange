package co.com.s4n.semillero.ejercicio.dominio.entidades;

import co.com.s4n.semillero.ejercicio.dominio.vo.Direccion;

public class Dron {

    private int x = 0;
    private int y = 0;
    private Direccion d = Direccion.norte;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direccion getD() {
        return d;
    }

    public Dron() {
        this.x = 0;
        this.y = 0;
        this.d = Direccion.norte;
    }

    public Dron(int x, int y, Direccion d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Dron{" +
                "x=" + x +
                ", y=" + y +
                ", d=" + d +
                '}';
    }

    public String toString2() {
        return ""+x +
                ", y=" + y +
                ", d=" + d +
                '}';
    }
}
