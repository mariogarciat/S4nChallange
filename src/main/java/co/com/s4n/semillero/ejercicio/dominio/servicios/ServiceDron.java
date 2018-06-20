package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import co.com.s4n.semillero.ejercicio.dominio.vo.Direccion;
import io.vavr.concurrent.Future;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceDron {

    public static Future<Dron> avanzar(Dron dron){
        int nX = dron.getX();
        int nY = dron.getY();
        Direccion d = dron.getD();
        switch (d){
            case norte:{
                nY+=1;
                break;
            }
            case sur: {
                nY-=1;
                break;
            }
            case este: {
                nX+=1;
                break;
            }
            case oeste: {
                nX-=1;
                break;
            }
        }

        Dron nDron = new Dron(nX,nY,d);
        return Future.of(()->nDron);
    }

    public static Future<Dron> rotarIzq(Dron dron){
        Direccion d = dron.getD();
        if(d.equals(Direccion.norte)){
            d = Direccion.oeste;
        }else if (d.equals(Direccion.sur)){
            d = Direccion.este;
        }else if (d.equals(Direccion.este)){
            d = Direccion.norte;
        }else if (d.equals(Direccion.oeste)){
            d = Direccion.sur;
        }

        Dron nDron = new Dron(dron.getX(),dron.getY(),d);
        return Future.of(()->nDron);
    }

    public static Future<Dron> rotarDer(Dron dron){
        Direccion d = dron.getD();
        if(d.equals(Direccion.norte)){
            d = Direccion.este;
        }else if (d.equals(Direccion.sur)){
            d = Direccion.oeste;
        }else if (d.equals(Direccion.este)){
            d = Direccion.sur;
        }else if (d.equals(Direccion.oeste)){
            d = Direccion.norte;
        }

        Dron nDron = new Dron(dron.getX(),dron.getY(),d);
        return Future.of(()->nDron);
    }

    public static Future<Dron> elegirAccion(String c, Future<Dron> dron){
        switch (c){
            case "A": return avanzar(dron.get());
            case "I": return rotarIzq(dron.get());
            case "D": return rotarDer(dron.get());
        }
        return dron;
    }

    public static Future<Dron> mover(String move, Dron dron){
        List<String> list = Arrays.asList(move.split(""));
        int[] index = {0};
        List<Future<Dron>> drones = new ArrayList<>();
        drones.add(Future.of(()->dron));
        list.stream().forEach(s -> {
            drones.add(elegirAccion(s,drones.get(index[0])));
            index[0]++;
        });
        System.out.println(drones.get(index[0]).get().toString());
        return drones.get(index[0]);
    }

    public static Future<Dron> cargarRuta(Dron dron, io.vavr.collection.List<Ruta> rutas){

        String a = String.valueOf(dron.getX());
        //rutas a string
        rutas.fold(dron.toString(), (x,y)-> transform(x,y));




    }

    public static String transform(String dron, String ruta){

        String[] array = dron.split(",");
        int xpos = Integer.parseInt(array[0]);
        int ypos = Integer.parseInt(array[1]);
        switch (array[2]){
            case "norte": ;
            case "sur":;
            case "este":;
            case "oeste":;

        }
        Dron dron1 = new Dron(array[0],array[1],array3[])
    }


}