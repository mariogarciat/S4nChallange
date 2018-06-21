package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.vo.Accion;
import io.vavr.collection.List;

import java.util.Arrays;

public class ServicePedido {



    public static List<Accion> newPedido(String ins){
        List<String> instruccion = Arrays.asList(ins.split("")).stream().collect(List.collector());
        List<Accion> acciones = List.empty();

        //List<String> list = instruccion.flatMap(a -> acciones.append(convertToAction(a)));


return null;

    }

    public static Accion convertToAction(String s){

        switch (s){
            case "A": return Accion.A;
            case "I": return Accion.I;
            case "D": return Accion.D;
        }
        return null;
    }

    public static boolean validarPedido(Pedido pedido){
        String regex = "[AID]*";
        boolean esPedido = pedido.toString().matches(regex);
        return esPedido;
    }
}
