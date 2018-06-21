package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceDron {

    private final static int limInf = -10;
    private final static int limSup = 10;

    public static Future<Dron> avanzar(Dron dron){
        int nX = dron.getX();
        int nY = dron.getY();
        Orientacion d = dron.getD();
        switch (d){
            case norte:{ nY+=1; break;
            }
            case sur: { nY-=1; break;
            }
            case este: { nX+=1; break;
            }
            case oeste: { nX-=1; break;
            }
        }

        Dron nDron = new Dron(nX,nY,d);
        return enLimite(nX,nY) ? Future.of(()->nDron) : Future.of(() -> {throw new Error("Dron sale del límite");});
    }

    public static Future<Dron> rotarIzq(Dron dron){
        Orientacion d = dron.getD();
        if(d.equals(Orientacion.norte)){
            d = Orientacion.oeste;
        }else if (d.equals(Orientacion.sur)){
            d = Orientacion.este;
        }else if (d.equals(Orientacion.este)){
            d = Orientacion.norte;
        }else if (d.equals(Orientacion.oeste)){
            d = Orientacion.sur;
        }

        Dron nDron = new Dron(dron.getX(),dron.getY(),d);
        return Future.of(()->nDron);
    }

    public static Future<Dron> rotarDer(Dron dron){
        Orientacion d = dron.getD();
        if(d.equals(Orientacion.norte)){
            d = Orientacion.este;
        }else if (d.equals(Orientacion.sur)){
            d = Orientacion.oeste;
        }else if (d.equals(Orientacion.este)){
            d = Orientacion.sur;
        }else if (d.equals(Orientacion.oeste)){
            d = Orientacion.norte;
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
        return drones.get(index[0]);
    }

    public static boolean enLimite(int x, int y){
        return x <= limSup && x >= limInf && y <= limSup && y >= limInf;
    }

    public static String aString(Dron dron){
        String x = String.valueOf(dron.getX());
        String y = String.valueOf(dron.getY());
        String o = "";
        switch (dron.getD()){
            case norte: o = "norte"; break;
            case sur: o = "sur";break;
            case este: o = "este";break;
            case oeste: o = "oeste";break;
        }
        String stringDron = x + "," + y + "," + o;
        return stringDron;
    }


    public static Future<Dron> cargarRuta(Ruta ruta){

        String neo = "instruccion";
        Dron drontest = new Dron();
        io.vavr.collection.List<Tuple2<Future<Dron>, String>> tuplas = ruta.getRutas().map(r -> Tuple.of(Future.of(Dron::new), r));

        Tuple2<Future<Dron>, String> fold = tuplas.fold(Tuple.of(Future.of(() -> new Dron()), neo), (tupacc, tupelem) -> {
            String ins = tupelem._2;
            Future<Dron> newDron = tupacc._1.flatMap(droncc -> ServiceDron.mover(ins, droncc));
            return Tuple.of(newDron, neo);
        });
        return fold._1;


    }




}