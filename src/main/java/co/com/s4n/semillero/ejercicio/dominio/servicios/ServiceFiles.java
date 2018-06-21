package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import io.vavr.Tuple;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceFiles {

    public static Try<Stream<String>> leerArchivo(String ruta){

        Try<Stream<String>> streamFile = Try.of(()->Files.lines(Paths.get(ruta)));
        return !streamFile.isEmpty() ? streamFile : Try.failure(new Exception("Archivo no encontrado"));
    }

    public static Option<List<String>> craerLista(Try<Stream<String>> stream) {

        List<String> list = new ArrayList<>();
        if (stream.isFailure()){
            return null;
        }else {
            list = stream.get().collect(Collectors.toList());
        }
        return Option.of(list);
    }

    public static io.vavr.collection.List<io.vavr.collection.List<String>> listaAPedidos(List<String> list){

        io.vavr.collection.List<String> pedidos = io.vavr.collection.List.of();
        io.vavr.collection.List<io.vavr.collection.List<String>> newList = io.vavr.collection.List.of();
        int k = 1;
        int limLunches = 3;
        int j=0;
        while (j<list.size() && j<=10){
            pedidos = pedidos.append(list.get(j));
            if(k%limLunches==0){
                newList = newList.append(pedidos);
                pedidos = io.vavr.collection.List.empty();
            }
            k++;
            j++;
        }
        if (pedidos.size()!=0){
            newList =  newList.append(pedidos);
        }
        return newList;
    }

    public static io.vavr.collection.List<Ruta> listARuta(io.vavr.collection.List<io.vavr.collection.List<String>> list){


        io.vavr.collection.List<Ruta> rutas = list.map(l -> new Ruta(l))
                .map(l2 -> ServiceRuta.validarRuta(l2));
        return rutas;
        //io.vavr.collection.List<String> vavrList = list.get(index);
        //Ruta rutas = new Ruta(vavrList);
        //Ruta rutaValidada = ServiceRuta.validarRuta(rutas);


    }

    /*String neo = "instruccion";
    io.vavr.collection.List<Tuple2<Future<Dron>, String>> tuplas = ruta.getRutas().map(r -> Tuple.of(Future.of(Dron::new), r));

    Tuple2<Future<Dron>, String> fold = tuplas.fold(Tuple.of(Future.of(() -> dron), neo), (tupacc, tupelem) -> {
        String ins = tupelem._2;
        Future<Dron> newDron = tupacc._1.flatMap(droncc -> ServiceDron.mover(ins, droncc));
        return Tuple.of(newDron, neo);
    });
        return fold._1;*/


}
