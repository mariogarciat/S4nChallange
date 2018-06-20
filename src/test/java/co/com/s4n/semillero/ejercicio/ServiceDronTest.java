package co.com.s4n.semillero.ejercicio;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceFiles;
import co.com.s4n.semillero.ejercicio.dominio.vo.Direccion;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static io.vavr.API.None;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitPlatform.class)
public class ServiceDronTest {

    @Test
    public void testCrearDron(){
        //Dron mockedDron = mock(Dron.class);
        Dron dron = new Dron();
        assertEquals(0,dron.getX());
        assertEquals(0,dron.getY());
        assertEquals(Direccion.norte,dron.getD());
    }

    @Test
    public void testAvanzar(){
        Dron d = new Dron();
        Dron dronTest = new Dron(-2,4,Direccion.norte);
        Future<Dron> dron = ServiceDron.mover("AAAAIAAD", d);


        assertEquals(dronTest.getY(), dron.get().getY());
        assertEquals(dronTest.getX(), dron.get().getX());
        assertEquals(dronTest.getD(), dron.get().getD());

    }

    @Test
    public void testLeerArchivo(){
        String ruta = "./src/test/resources/in.txt";
        Stream<String> streamTeat = Stream.of("AAAAIAAD","DDAIAD", "AAIADAD");
        Try<Stream<String>> streams = ServiceFiles.leerArchivo(ruta);

        Option<List<String>> list = ServiceFiles.craerLista(streams);

        assertEquals("AAIADAD",list.getOrElse(new ArrayList<>()).get(2));


    }

    @Test
    public void testListToPedidos(){
        String[] array = {"AAAAIAAD","DDAIAD","AAIADAD","ADAI","AAIAD","IIADAD"
                ,"AAAAIAAD","DDAIAD","AAIADAD","AAAAIAAD", "DDAIAD", "AAIADAD", "HOLA"};
        List<String> list = Arrays.asList(array);
        io.vavr.collection.List<io.vavr.collection.List<String>> lists = ServiceFiles.listaAPedidos(list);

        System.out.println("tamaño de la lista "+ lists.get(3).size());
        System.out.println("Primer elemento de las listas"+lists.get(0).get(0));
        assertEquals("DDAIAD",lists.get(3).get(1));
        assertEquals("HOLA", lists.get(4).get(0));
    }

    @Test
    public void testPedidos(){
        String ruta = "./src/test/resources/in.txt";
        Stream<String> streamTeat = Stream.of("AAAAIAAD","DDAIAD", "AAIADAD");
        Try<Stream<String>> streams = ServiceFiles.leerArchivo(ruta);
        Dron dron = new Dron();
        Dron dronTest = new Dron(0,0,Direccion.oeste);
        Option<List<String>> list = ServiceFiles.craerLista(streams);
        io.vavr.collection.List<io.vavr.collection.List<String>> lists = ServiceFiles.listaAPedidos(list.get());

        lists.forEach(l -> l.forEach(p -> ServiceDron.mover(p,dron)));


        assertEquals(dronTest.getY(), dron.getY());
        assertEquals(dronTest.getX(), dron.getX());
        assertEquals(dronTest.getD(), dron.getD());
    }












}
